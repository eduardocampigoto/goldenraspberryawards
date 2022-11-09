package com.goldenraspberry.awards.controller;

import com.goldenraspberry.awards.dto.AwardDto;
import com.goldenraspberry.awards.service.AwardsService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/awards")
@RequiredArgsConstructor
public class AwardsController {

    private final AwardsService awardsService;

    @GetMapping
    public ResponseEntity<Map<String, List<AwardDto>>> awards() {
        return ResponseEntity.status(HttpStatus.OK).body(awardsService.findWinners());
    }
}
