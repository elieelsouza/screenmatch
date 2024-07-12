package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double notaIMDB;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio){
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.tituloEpisodio();
        this.numeroEpisodio = dadosEpisodio.numeroEpisodio();
        try {
            this.notaIMDB = Double.valueOf(dadosEpisodio.notaIMDB());
        } catch(NumberFormatException ex){
            this.notaIMDB = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch(DateTimeParseException ex) {
            this.dataLancamento = null;
        }
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getNotaIMDB() {
        return notaIMDB;
    }

    public void setNotaIMDB(Double notaIMDB) {
        this.notaIMDB = notaIMDB;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }


    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", notaIMDB=" + notaIMDB +
                ", dataLancamento=" + dataLancamento ;
    }
}
