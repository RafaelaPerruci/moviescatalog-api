package io.github.rafaelaperruci.moviecataloginfo_api.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatter {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String format(String isoDate) {
       if (isoDate == null || isoDate.isBlank()) {
           throw new IllegalArgumentException("A data não pode ser nula ou vazia.");
       }
        try {
            LocalDate date = LocalDate.parse(isoDate, INPUT_FORMAT);
            return date.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Esperado: yyyy-MM-dd.");
        }
    }
}
