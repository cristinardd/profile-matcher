package main.model.embeddable;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class InventoryId implements Serializable {
    private String playerId;
    private String itemName;
}
