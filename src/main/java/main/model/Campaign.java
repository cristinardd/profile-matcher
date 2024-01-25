package main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Timestamp;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "campaign")
@Getter
public class Campaign {
    @Id
    @Column(name = "name", updatable = false, nullable = false)
    private String name;
    @Column(name = "game", nullable = false)
    private String game;
    @Column(name = "priority", nullable = false)
    private BigDecimal priority;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date", nullable = false)
    @Timestamp
    private LocalDateTime endDate;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
