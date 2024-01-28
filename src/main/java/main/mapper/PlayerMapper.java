package main.mapper;

import main.dto.ClanDto;
import main.dto.DeviceDto;
import main.dto.PlayerDto;
import main.model.Inventory;
import main.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {

    public PlayerDto toDto(Player player) {
        return PlayerDto.builder()
                .playerId(player.getPlayerId())
                .credential(player.getCredential())
                .created(player.getCreated())
                .modified(player.getModified())
                .lastSession(player.getLastSession())
                .totalSpent(player.getTotalSpent())
                .totalRefund(player.getTotalRefund())
                .totalTransactions(player.getTotalTransactions())
                .lastPurchase(player.getLastPurchase())
                .level(player.getLevel())
                .xp(player.getXp())
                .totalPlaytime(player.getTotalPlaytime())
                .country(player.getCountry().getCountryCode())
                .language(player.getLanguage())
                .birthdate(player.getBirthdate())
                .gender(player.getGender().name())
                .clan(mapClans(player))
                .customfield(player.getCustomfield())
                .devices(mapDevices(player))
                .inventory(mapInventory(player))
                .build();
    }

    private List<ClanDto> mapClans(Player player) {
        return player.getClans().stream()
                .map(clan -> ClanDto.builder()
                        .clanId(clan.getClanId())
                        .name(clan.getName())
                        .build())
                .collect(Collectors.toList());
    }

    private List<DeviceDto> mapDevices(Player player) {
        return player.getDevices().stream()
                .map(device -> DeviceDto.builder()
                        .id(device.getId())
                        .firmware(device.getFirmware())
                        .model(device.getModel())
                        .carrier(device.getCarrier())
                        .build())
                .collect(Collectors.toList());
    }

    private Map<String, Integer> mapInventory(Player player) {
        return player.getInventory().stream()
                .collect(Collectors.groupingBy(
                        Inventory::getItemName,
                        Collectors.summingInt(Inventory::getQuantity)
                ));
    }
}
