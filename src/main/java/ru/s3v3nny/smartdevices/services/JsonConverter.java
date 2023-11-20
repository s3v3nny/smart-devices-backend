package ru.s3v3nny.smartdevices.services;

import com.google.gson.Gson;
import ru.s3v3nny.smartdevices.models.*;

public class JsonConverter {
    private static final Gson gson = new Gson();

    public User getUser(String json) {
        return gson.fromJson(json, User.class);
    }

    public Indicator getIndicator(String json) {
        return gson.fromJson(json, Indicator.class);
    }

    public String indicatorToJson(RelevantIndicator relevantIndicator) {
        return gson.toJson(relevantIndicator);
    }
}
