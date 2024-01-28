package main.model.embeddable;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CampaignItemId implements Serializable {
    private String campaignId;
    private String itemName;
}
