package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.CampaignDto;
import main.mapper.CampaignMapper;
import main.model.Campaign;
import main.model.Player;
import main.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;
    @Transactional(readOnly = true)
    public List<CampaignDto> getAllCampaigns() {
        return campaignRepository.findAll()
                .stream()
                .map(campaignMapper::toCampaignDto)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<Campaign> findMatchingCampaigns(Player player) {
        List<Campaign> allCampaigns = campaignRepository.findAllByStartDateBeforeAndEndDateAfter(LocalDateTime.now(), LocalDateTime.now());

        return allCampaigns.stream()
                .filter(campaign -> campaign.isEnabled())
                .filter(campaign -> checkLevelMatch(player, campaign))
                .filter(campaign -> checkCountryMatch(player, campaign))
                .filter(campaign -> checkItemsHave(player, campaign))
                .filter(campaign -> checkItemsDoNotHave(player, campaign))
                .collect(Collectors.toList());
    }

    private boolean checkLevelMatch(Player player, Campaign campaign) {
        return campaign.getLevelMatchers().stream()
                .anyMatch(levelMatcher -> levelMatcher.getMinLevel() <= player.getLevel()
                        && levelMatcher.getMaxLevel() >= player.getLevel());
    }

    private boolean checkCountryMatch(Player player, Campaign campaign) {
        return campaign.getCountries().stream()
                .anyMatch(country -> country.getCountry().getCountryCode().equals(player.getCountry().getCountryCode()));
    }

    private boolean checkItemsHave(Player player, Campaign campaign) {
        Set<String> playerItems = player.getInventory().stream()
                .map(inventory -> inventory.getItem().getItemName())
                .collect(Collectors.toSet());
        return campaign.getItems().stream()
                .allMatch(campaignItem -> playerItems.contains(campaignItem.getItem().getItemName()));
    }

    private boolean checkItemsDoNotHave(Player player, Campaign campaign) {
        Set<String> playerItems = player.getInventory().stream()
                .map(inventory -> inventory.getItem().getItemName())
                .collect(Collectors.toSet());
        return campaign.getDoesNotHaveItems().stream()
                .noneMatch(campaignItem -> playerItems.contains(campaignItem.getItem().getItemName()));
    }
}
