package io.github.rafaelaperruci.moviecataloginfo_api.service;

import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Test
    void returnValidDataWhenNameIsProvided() {

        ExternalApiConsumer mockExternalApiConsumer = mock(ExternalApiConsumer.class);
        MovieService service = new MovieService(mockExternalApiConsumer);
        MovieDTO mockDto = new MovieDTO("Matrix", "Um hacker descobre a verdade.", "1999-05-01", 7);

        when(service.getMovieData("Matrix")).thenReturn(mockDto);

        MovieDTO result = service.getMovieData("Matrix");

        assertNotNull(result);
        assertEquals("Matrix", result.title());
        assertEquals("Um hacker descobre a verdade.", result.resume());
        assertEquals("1999-05-01", result.date());
        assertEquals(7, result.rating());
    }

}