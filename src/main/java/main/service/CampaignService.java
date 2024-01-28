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
                .filter(campaign -> campaign.isEnabled()
                                && campaign.getLevelMatchers().stream()
                                .anyMatch(levelMatcher -> levelMatcher.getMinLevel() <= player.getLevel()
                                        && levelMatcher.getMaxLevel() >= player.getLevel())
                )
                .collect(Collectors.toList());
    }
}
