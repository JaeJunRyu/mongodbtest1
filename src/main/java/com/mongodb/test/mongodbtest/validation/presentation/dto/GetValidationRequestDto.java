package com.mongodb.test.mongodbtest.validation.presentation.dto;

import javax.validation.constraints.Min;

public class GetValidationRequestDto {

//    @Min
    private Integer minValue;

    private Integer maxValue;

    private String nullValue;

    private String PatternValue;

    private String notBlankValue;

    private String notEmptyValue;

    private Integer numberValue;



    private String lengthValue;

    private String rangeValue;

    private String htmlValue;

    private String urlValue;



}
