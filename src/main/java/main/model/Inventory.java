package main.model;

import jakarta.persistence.*;
import lombok.Getter;
import main.model.embeddable.InventoryId;

@Entity
@Table(name = "inventory")
//@IdClass(InventoryId.class)
@Getter
public class Inventory {
    @EmbeddedId
    private InventoryId id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @MapsId("itemName")
    @JoinColumn(name = "item_name")
    private Item item;
    @Column(name = "item_name", insertable = false, updatable = false)
    private String itemName;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}

