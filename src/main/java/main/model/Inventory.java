package main.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "inventory")
//@IdClass(InventoryId.class)
@Getter
public class Inventory {
    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @Id
    @ManyToOne
    @JoinColumn(name = "item_name")
    private Item item;

    @Column(name = "item_name", insertable = false, updatable = false)
    private String itemName;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}

