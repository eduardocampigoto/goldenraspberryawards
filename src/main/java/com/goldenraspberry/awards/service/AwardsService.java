package com.goldenraspberry.awards.service;

import com.goldenraspberry.awards.dto.AwardDto;
import com.goldenraspberry.awards.model.Producer;
import com.goldenraspberry.awards.model.Title;
import com.goldenraspberry.awards.repository.ProducerRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwardsService {

    private final ProducerRepository producerRepository;
    private final AwardsIntervalService awardsIntervalService;


    public Map<String, List<AwardDto>> findWinners() {
        var producersAwardsIntervals = producerRepository.findAll()
                .stream()
                .map(producer -> {
                    var movies = findWinnerMovies(producer);

                    return (movies.size() > 1)
                            ? awardsIntervalService.findBiggestAndLowestIntervals(producer, movies)
                            : null;

                })
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        var awardsInterval = producersAwardsIntervals.stream().map(AwardDto::getInterval).collect(Collectors.toList());

        return new HashMap<>() {{
            put("min", awardsIntervalService.getLowestsIntervals(awardsInterval, producersAwardsIntervals));
            put("max", awardsIntervalService.getBiggestsIntervals(awardsInterval, producersAwardsIntervals));
        }};
    }

    private List<Title> findWinnerMovies(Producer producer) {
        return producer.getTitles()
                .stream()
                .filter(Title::isWinner)
                .collect(Collectors.toList());
    }

}
