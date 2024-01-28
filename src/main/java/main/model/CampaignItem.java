package main.model;

import jakarta.persistence.*;
import lombok.Getter;
import main.model.embeddable.CampaignItemId;


@Entity
@Table(name = "campaign_has_item")
@Getter
public class CampaignItem {
    @EmbeddedId
    private CampaignItemId id;

    @ManyToOne
    @MapsId("campaignId")
    @JoinColumn(name = "campaign_name")
    private Campaign campaign;

    @ManyToOne
    @MapsId("itemName")
    @JoinColumn(name = "item_name")
    private Item item;

}


