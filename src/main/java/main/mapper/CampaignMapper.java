package main.mapper;

import lombok.AllArgsConstructor;
import main.dto.*;
import main.model.Campaign;
import main.model.CampaignLevel;
import main.model.Item;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CampaignMapper {
    public CampaignDto toCampaignDto(Campaign campaign) {

        return CampaignDto.builder()
                .name(campaign.getName())
                .game(campaign.getGame())
                .priority(campaign.getPriority())
                .startDate(campaign.getStartDate())
                .endDate(campaign.getEndDate())
                .enabled(campaign.isEnabled())
                .lastUpdated(campaign.getLastUpdated())
                .matchers(MatchersDto.builder()
                        .level(LevelDto.builder()
                                .min(campaign.getLevelMatchers().stream()
                                        .mapToInt(CampaignLevel::getMinLevel)
                                        .min()
                                        .orElse(0))
                                .max(campaign.getLevelMatchers().stream()
                                        .mapToInt(CampaignLevel::getMaxLevel)
                                        .max()
                                        .orElse(0))
                                .build())
                        .has(HasDto.builder()
                                .country(campaign.getCountries().stream()
                                        .map(country -> country.getCountry().getCountryCode())
                                        .collect(Collectors.toList()))
                                .items(campaign.getItems().stream()
                                        .filter(item -> ((Item.ItemType.ITEM) == item.getItem().getType()))
                                        .map(item -> item.getItem().getItemName())
                                        .collect(Collectors.toList()))
                                .build())
                        .doesNotHave(DoesNotHaveDto.builder()
                                .items(campaign.getDoesNotHaveItems().stream()
                                        .filter(item -> ((Item.ItemType.ITEM) == item.getItem().getType()))
                                        .map(item -> item.getItem().getItemName())
                                        .collect(Collectors.toList()))
                                .build())
                        .build())
                .build();

    }

}
