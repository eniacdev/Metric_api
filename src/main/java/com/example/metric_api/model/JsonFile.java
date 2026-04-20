package com.example.metric_api.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JsonFile {

    private String file;
    private LocalDate createdAt;
}
