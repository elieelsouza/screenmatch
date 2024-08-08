package br.com.alura.screenmatch.helpers;

public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    POLICIAL("Police");

    private String catergoriaOmdb;

    Categoria(String catergoriaOmdb) {
        this.catergoriaOmdb = catergoriaOmdb;
    }

    public static Categoria fromString(String texto) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.catergoriaOmdb.equalsIgnoreCase(texto)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + texto);
    }
}
