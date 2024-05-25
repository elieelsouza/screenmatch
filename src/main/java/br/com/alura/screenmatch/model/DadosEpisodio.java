package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Episode") String numeroEpisodio,
                            @JsonAlias("Title") String tituloEpisodio,
                            @JsonAlias("Plot") String trama,
                            @JsonAlias("Runtime") String tempoEpisodio,
                            @JsonAlias("imdbRating") String notaIMDB) {
}
