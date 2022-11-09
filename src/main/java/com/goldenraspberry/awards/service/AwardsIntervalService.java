package com.goldenraspberry.awards.service;

import com.goldenraspberry.awards.dto.AwardDto;
import com.goldenraspberry.awards.model.Producer;
import com.goldenraspberry.awards.model.Title;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AwardsIntervalService {

    public List<AwardDto> findBiggestAndLowestIntervals(Producer producer, List<Title> movies) {
        AwardDto biggestInterval = new AwardDto();
        AwardDto lowestInterval = new AwardDto();

        movies.stream()
                .sorted(Comparator.comparing(Title::getYear))
                .reduce((previous, following) -> {
                    fillBiggestInterval(biggestInterval, previous, following, producer);
                    fillLowestInterval(lowestInterval, previous, following, producer);

                    return following;
                });

        return Arrays.asList(biggestInterval, lowestInterval);
    }

    private void fillLowestInterval(AwardDto award, Title previous, Title following, Producer producer) {
        var interval = following.getYear() - previous.getYear();
        if (award.getInterval() == null || interval <= award.getInterval()) {
            fillInterval(award, previous, following, producer, interval);
        }
    }

    private void fillBiggestInterval(AwardDto award, Title previous, Title following, Producer producer) {
        var interval = following.getYear() - previous.getYear();
        if (award.getInterval() == null || interval > award.getInterval()) {
            fillInterval(award, previous, following, producer, interval);
        }
    }

    private void fillInterval(AwardDto award, Title previous, Title following, Producer producer, Integer interval) {
        award.setPreviousWin(previous.getYear());
        award.setFollowingWin(following.getYear());
        award.setInterval(interval);
        award.setProducer(producer.getName());
    }

    public List<AwardDto> getLowestsIntervals(List<Integer> awardsIntervals, List<AwardDto> producers) {
        var lowestInterval = awardsIntervals.stream().min(Integer::compareTo).get();
        return producers.stream().filter(p -> p.getInterval().equals(lowestInterval)).collect(Collectors.toList());
    }

    public List<AwardDto> getBiggestsIntervals(List<Integer> awardsIntervals, List<AwardDto> producers) {
        var biggestInterval = awardsIntervals.stream().max(Integer::compareTo).get();
        return producers.stream().filter(p -> p.getInterval().equals(biggestInterval)).collect(Collectors.toList());
    }
}
