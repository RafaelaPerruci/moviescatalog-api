package io.github.rafaelaperruci.moviecataloginfo_api.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DateFormatter {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String format(String isoDate) {
        LocalDate date = LocalDate.parse(isoDate, INPUT_FORMAT);
        return date.format(OUTPUT_FORMAT);
    }
}
