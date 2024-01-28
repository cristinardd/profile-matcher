package main.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "item")
@Getter
public class Item {
    @Id
    @Column(name = "item_name",updatable = false, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ItemType type;

    public enum ItemType {
        ITEM, COIN, CASH
    }
}