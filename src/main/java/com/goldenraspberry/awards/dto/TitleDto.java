package com.goldenraspberry.awards.dto;

import lombok.Data;

import java.util.List;

@Data
public class TitleDto {

    private long id;

    private String name;

    private StudioDto studio;

    private List<ProducerDto> producers;

    private String winner;

}
