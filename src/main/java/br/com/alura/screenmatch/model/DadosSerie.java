package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String  titulo,
                        @JsonAlias("totalSeasons") Integer totalTemporadas,
                        @JsonAlias("imdbRating") String avaliacao,
                        @JsonAlias("Poster") String poster,
                        @JsonAlias("Plot") String sinopse,
                        @JsonAlias("Genre") String genero,
                        @JsonAlias("Actors") String atores ) {

    @Override
    public String toString(){
        return "" +
                "==========================================================\n" +
                "=======================Dados da Serie=====================\n" +
                "Titulo:................... " + titulo + "\n" +
                "Total de temporadas:...... " + totalTemporadas + "\n" +
                "Avaliacao IMDB:........... " + avaliacao + "\n" +
                "Genero:................... " + genero + "\n" +
                "Atores:................... " + atores + "\n" +
                "Sinopse:.................. " + sinopse + "\n" +
                "URL do poster da serie:... " + poster + "\n" +
                "==========================================================";
    }
}
