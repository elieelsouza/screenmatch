package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") String numeroTemporada,
                             @JsonAlias("totalSeasons") Integer totalTemporadas,
                             @JsonAlias("Episodes") List<DadosEpisodio> episodios) {
}
