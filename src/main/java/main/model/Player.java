package main.model;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "credential", nullable = false)
    private String credential;

    @Column(name = "created", nullable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modified;

    @Column(name = "last_session", nullable = false)
    private LocalDateTime lastSession;

    @Column(name = "total_spent", nullable = false)
    private Integer totalSpent;

    @Column(name = "total_refund", nullable = false)
    private Integer totalRefund;

    @Column(name = "total_transactions", nullable = false)
    private Integer totalTransactions;

    @Column(name = "last_purchase", nullable = false)
    private LocalDateTime lastPurchase;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "xp", nullable = false)
    private Integer xp;

    @Column(name = "total_playtime", nullable = false)
    private Integer totalPlaytime;

    @OneToOne
    @JoinColumn(name = "country", referencedColumnName = "country_code")
    private Country country;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "birthdate", nullable = false)
    private LocalDateTime birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "clan_id")
    private UUID clanId;

    @Column(name = "_customfield")
    private String customfield;


    public enum Gender {
        MALE, FEMALE, OTHER
    }
}