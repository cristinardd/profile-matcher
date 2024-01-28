package main.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "country")
@Getter
public class Country {
    @Id
    @Column(name = "country_code",updatable = false, nullable = false)
    private String countryCode;

    @JsonValue
    public String getCountryCode() {
        return countryCode;
    }
}
