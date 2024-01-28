package main.model.embeddable;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class CampaignCountryId implements Serializable {
    private String campaign;
    private String country;
}
