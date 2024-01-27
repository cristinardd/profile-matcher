package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.CampaignDto;
import main.model.Player;
import main.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping(value = "/getCampaigns")
    public ResponseEntity<List<CampaignDto>> getCurrentCampaigns(){
        return ResponseEntity.ok(campaignService.getCurrentCampaigns());
    }

}
