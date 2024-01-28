package main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.embeddable.CampaignCountryId;
import main.model.embeddable.CampaignItemId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_does_not_have_item")
public class CampaignDoesNotHaveItem {
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
