package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Episode") Integer numeroEpisodio,
                            @JsonAlias("Title") String tituloEpisodio,
                            @JsonAlias("imdbRating") String notaIMDB,
                            @JsonAlias("Released") String dataLancamento) {
}
