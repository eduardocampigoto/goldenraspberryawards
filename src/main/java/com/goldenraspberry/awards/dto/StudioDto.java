package com.goldenraspberry.awards.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudioDto {

private long id;

private String name;

private List<TitleDto> titles;


}
