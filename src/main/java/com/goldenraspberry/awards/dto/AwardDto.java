package com.goldenraspberry.awards.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AwardDto {
    public String producer;
    public Integer interval;
    public Integer previousWin;
    public Integer followingWin;
}
