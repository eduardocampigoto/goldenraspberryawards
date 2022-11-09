package com.goldenraspberry.awards.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MovieDto {

    @NotBlank
    @Size(max = 4)
    private String year;

    private String producers;

    private String winner;
}
