package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.helpers.Categoria;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {
    private String  titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    private String poster;
    private String sinopse;
    private Categoria genero;
    private String atores;

    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0]);
        this.atores = dadosSerie.atores();

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    @Override
    public String toString(){
        return "" +
                "==========================================================\n" +
                "=======================Dados da Serie=====================\n" +
                "Genero:................... " + genero  + "\n" +
                "Titulo:................... " + titulo + "\n" +
                "Total de temporadas:...... " + totalTemporadas + "\n" +
                "Avaliacao IMDB:........... " + avaliacao + "\n" +
                "Atores:................... " + atores + "\n" +
                "Sinopse:.................. " + sinopse + "\n" +
                "URL do poster da serie:... " + poster + "\n" +
                "==========================================================";
    }
}
