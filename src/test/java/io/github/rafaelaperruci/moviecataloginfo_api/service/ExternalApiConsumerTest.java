package io.github.rafaelaperruci.moviecataloginfo_api.service;

import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ExternalApiConsumerTest {

    @Test
    public void shouldReturnValidDataWhenNameIsProvided() {

        ExternalApiConsumer mockConsumer = mock(ExternalApiConsumer.class);
        MovieService service = new MovieService(mockConsumer);

        MovieDTO mockDto = new MovieDTO("Matrix", "Um hacker descobre a verdade.", "1999-05-01", 7);

        when(mockConsumer.getMovieFromExternalApi("Matrix")).thenReturn(mockDto);


        MovieDTO result = service.getMovieData("Matrix");

        assertNotNull(result);
        assertEquals("Matrix", result.title());
        assertEquals("1999-05-01", result.date());

        verify(mockConsumer, times(1)).getMovieFromExternalApi("Matrix");



    }

}