package main.model;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name = "campaign_has_item")
//@IdClass(CampaignItemId.class)
@Getter
public class CampaignItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "campaign_name")
    private Campaign campaign;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_name")
    private Item item;

}


