package main.model.embeddable;


import jakarta.persistence.Embeddable;
import main.model.Campaign;
import main.model.Item;

import java.io.Serializable;

@Embeddable
public class CampaignItemId implements Serializable {
    private String campaignId;
    private String itemName;
}
