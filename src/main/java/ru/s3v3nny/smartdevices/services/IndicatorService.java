package ru.s3v3nny.smartdevices.services;

import lombok.SneakyThrows;
import ru.s3v3nny.smartdevices.models.Indicator;
import ru.s3v3nny.smartdevices.models.RelevantIndicator;
import ru.s3v3nny.smartdevices.repositories.DatabaseRepo;

import java.util.List;

public class IndicatorService {

    private final JsonConverter converter = new JsonConverter();
    DatabaseRepo databaseRepo = new DatabaseRepo();

    @SneakyThrows
    public void updateIndicators(String body) {
        Indicator indicator = converter.getIndicator(body);
        databaseRepo.updateIndicators(indicator);
    }

    @SneakyThrows
    public List<RelevantIndicator> getRelevantIndicators() {
        return databaseRepo.getRelevantIndicators();
    }
}
