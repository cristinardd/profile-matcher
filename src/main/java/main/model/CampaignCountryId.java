package main.model;

import lombok.Getter;

import java.io.Serializable;
@Getter
public class CampaignCountryId implements Serializable {
    private String campaign;
    private String country;
}
