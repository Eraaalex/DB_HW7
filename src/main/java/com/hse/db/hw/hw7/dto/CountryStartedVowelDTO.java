package com.hse.db.hw.hw7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryStartedVowelDTO {
    private String countryName;
    private Double percentage;

    public CountryStartedVowelDTO(String countryName, Integer percentage) {
        this.countryName = countryName;
        this.percentage = percentage.doubleValue();
    }
}
