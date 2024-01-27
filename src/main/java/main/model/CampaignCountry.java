package main.model;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name = "campaign_country")
@Getter
//@IdClass(CampaignCountryId.class)
public class CampaignCountry {
    @Id
    @ManyToOne
    @JoinColumn(name = "campaign_name")
    private Campaign campaign;

    @Id
    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

}

