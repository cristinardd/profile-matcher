package main.model;
import jakarta.persistence.*;
import lombok.Getter;
import main.model.embeddable.CampaignCountryId;


@Entity
@Table(name = "campaign_country")
@Getter
public class CampaignCountry {
    @EmbeddedId
    private CampaignCountryId campaignCountryId;

    @ManyToOne
    @MapsId("campaign")
    @JoinColumn(name = "campaign_name")
    private Campaign campaign;

    @ManyToOne
    @MapsId("country")
    @JoinColumn(name = "country_code")
    private Country country;


}

