package main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String playerId;
    private String credential;
    private LocalDateTime lastSession;
    private LocalDateTime created;
    private LocalDateTime lastPurchase;
    private LocalDateTime modified;
    private LocalDateTime birthdate;
    private String gender;
    private String customfield;
    private Integer totalSpent;
    private Integer level;
    private Integer totalRefund;
    private Integer totalTransactions;
    private Integer totalPlaytime;
    private String language;
    private Integer xp;
    private String country;
    private List<CampaignDto> activeCampaigns;
    private List<ClanDto> clan;
    private Map<String, Integer> inventory;
    private List<DeviceDto> devices;
}