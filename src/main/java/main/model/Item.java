package main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "item")
@Getter
public class Item {
    @Id
    @Column(name = "item_name",updatable = false, nullable = false)
    private String itemName;
}