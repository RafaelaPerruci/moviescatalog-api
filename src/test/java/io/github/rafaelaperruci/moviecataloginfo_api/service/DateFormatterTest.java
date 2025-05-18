package io.github.rafaelaperruci.moviecataloginfo_api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {

    private final DateFormatter formatter = new DateFormatter();

    @Test
    void fromISODateToBrazilianDateFormat() {
        String isoDate = "2024-12-25";
        String result = formatter.format(isoDate);
        assertEquals("25/12/2024", result);
    }

    @Test
    void shouldThrowExceptionWhenDateIsInvalid() {
        String invalidDate = "25-12-2024";
        assertThrows(IllegalArgumentException.class, () -> formatter.format(invalidDate));

        Assertions.assertEquals("Formato de data inválido. Esperado: yyyy-MM-dd.", invalidDate);
    }

}