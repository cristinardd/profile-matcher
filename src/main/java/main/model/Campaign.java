package main.model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

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
    private boolean enabled;
    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    @OneToMany(mappedBy = "campaign")
    private Set<CampaignLevel> levelMatchers;

    @OneToMany(mappedBy = "campaign")
    private Set<CampaignCountry> countries;

    @OneToMany(mappedBy = "campaign")
    private Set<CampaignItem> items;

    @OneToMany(mappedBy = "campaign")
    private Set<CampaignDoesNotHaveItem> doesNotHaveItems;
}
