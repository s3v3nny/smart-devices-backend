package ru.s3v3nny.smartdevices.models;

import lombok.Data;

@Data
public class Indicator {
    String user_id;
    Integer steps;
    Integer pulse;
    String sleep;
}
