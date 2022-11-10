package com.goldenraspberry.awards;

import com.goldenraspberry.awards.model.Producer;
import com.goldenraspberry.awards.model.Studio;
import com.goldenraspberry.awards.model.Title;
import com.goldenraspberry.awards.repository.ProducerRepository;
import com.goldenraspberry.awards.repository.StudioRepository;
import com.goldenraspberry.awards.repository.TitleRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class DBInitializer {

    final private ProducerRepository producerRepository;
    final private StudioRepository studioRepository;
    final private TitleRepository titleRepository;

    private static final String COLUMN_SEPARATOR = ";";

    @Transactional
    public void importFile() throws IOException, URISyntaxException {
        InputStream is = getClass().getResourceAsStream("/movielist.csv");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = "";
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(COLUMN_SEPARATOR);

                int year = Integer.parseInt(columns[0]);
                String title = columns[1];
                String studio = columns[2];
                String producer = columns[3];

                String winner = "no";
                if (columns.length > 4 && columns[4].trim().equals("yes")) {
                    winner = "yes";
                }

                Title titleModel = Title
                        .builder()
                        .year(year)
                        .name(title)
                        .winner(winner)
                        .build();

                setOrCreateProducer(producer, titleModel);
                setOrCreateStudios(studio, titleModel);

                titleRepository.save(titleModel);
            }
        }
    }

    private void setOrCreateProducer(String producerColumn, Title title) {

        Stream.of(producerColumn)
                .map(p -> p.split("(,|\s and \s)"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())
                .forEach(p -> {
                    var producerName = p.trim();
                    var producer = producerRepository
                            .findByName(producerName)
                            .orElseGet(() -> Producer.builder()
                                    .name(producerName)
                                    .build()
                            );

                    if (title.getProducers() == null) {
                        title.setProducers(new HashSet());
                    }

                    title.getProducers().add(producer);
                });
    }

    private void setOrCreateStudios(String studioName, Title title) {
        var studio = studioRepository
                .findByName(studioName.trim())
                .orElseGet(() -> Studio.builder()
                        .name(studioName)
                        .build()
                );

        if (title.getStudios() == null) {
            title.setStudios(new HashSet());
        }
        title.getStudios().add(studio);
    }
}
