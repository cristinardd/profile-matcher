package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.PlayerDto;
import main.model.Player;
import main.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/findAll")
    public ResponseEntity<List<PlayerDto>> findAll(){
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/get_client_config/{playerId}")
    public ResponseEntity<PlayerDto> getClientConfig(@PathVariable String playerId) {
        return ResponseEntity.ok(playerService.matchPlayerProfile(playerId));
    }
}
