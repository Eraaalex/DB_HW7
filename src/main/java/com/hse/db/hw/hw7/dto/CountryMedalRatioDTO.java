package com.hse.db.hw.hw7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CountryMedalRatioDTO {

    private String countryName;
    private Double ratio;
}