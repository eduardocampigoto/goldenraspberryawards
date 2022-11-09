package com.goldenraspberry.awards.dto;

import lombok.Data;

@Data
public class ProducerDto {

    private long id;

    private String name;

    private TitleDto titleDto;
}
