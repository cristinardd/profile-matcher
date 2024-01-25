package main.model;
import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Table(name = "campaign_level_matcher")
@Getter
public class CampaignLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "campaign_name")
    private Campaign campaign;
    @Column(name = "min_level", nullable = false)
    private Integer minLevel;
    @Column(name = "max_level", nullable = false)
    private Integer maxLevel;
}
