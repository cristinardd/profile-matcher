package main.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.CampaignDto;
import main.dto.PlayerDto;
import main.mapper.CampaignMapper;
import main.mapper.PlayerMapper;
import main.model.Campaign;
import main.model.Player;
import main.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final CampaignMapper campaignMapper;
    private final CampaignService campaignService;
    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PlayerDto matchPlayerProfile(String playerId) {
        Player player = fetchPlayer(playerId);
        List<Campaign> matchingCampaigns = campaignService.findMatchingCampaigns(player);

        updatePlayerWithActiveCampaigns(player, matchingCampaigns);
        playerRepository.save(player);

        return buildPlayerDto(player, matchingCampaigns);
    }

    private Player fetchPlayer(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with ID: " + playerId));
    }

    private void updatePlayerWithActiveCampaigns(Player player, List<Campaign> matchingCampaigns) {
        String activeCampaigns = matchingCampaigns.stream()
                .map(Campaign::getName)
                .collect(Collectors.joining(", "));
        player.setActiveCampaign(activeCampaigns);
    }

    private PlayerDto buildPlayerDto(Player player, List<Campaign> matchingCampaigns) {
        PlayerDto playerDto = playerMapper.toDto(player);
        List<CampaignDto> campaignDtos = matchingCampaigns.stream()
                .map(campaignMapper::toCampaignDto)
                .collect(Collectors.toList());
        playerDto.setActiveCampaigns(campaignDtos);
        return playerDto;
    }
}
