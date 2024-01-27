package main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_does_not_have_item")
public class CampaignDoesNotHaveItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "campaign_name", referencedColumnName = "name")
    private Campaign campaign;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_name", referencedColumnName = "item_name")
    private Item item;
}
