package main.model;

import jakarta.persistence.*;
@Entity
@Table(name = "inventory")
@IdClass(InventoryId.class)
public class Inventory {
    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_name")
    private Item item;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}

