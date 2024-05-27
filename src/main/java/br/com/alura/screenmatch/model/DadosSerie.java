package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String  titulo,
                        @JsonAlias("totalSeasons") Integer totalTemporadas,
                        @JsonAlias("imdbRating") String avaliacao,
                        @JsonAlias("Poster") String poster) {

    @Override
    public String toString(){
        return "" +
                "==========================================================\n" +
                "=======================Dados da Serie=====================\n" +
                "Titulo:................... " + titulo + "\n" +
                "Total de temporadas:...... " + totalTemporadas + "\n" +
                "Avaliacao IMDB:................ " + avaliacao + "\n" +
                "URL do poster da serie:... " + poster + "\n" +
                "==========================================================";
    }
}
