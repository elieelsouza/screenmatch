package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final Scanner sc = new Scanner(System.in);
    private static final String DOMINIO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=f6818b3c";
    private static final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){

        System.out.println("Digite o nome de uma serie: ");
        var nmSerie = sc.nextLine();
        var json = consumoAPI.obterDados(DOMINIO + nmSerie.replaceAll(" ", "+") + API_KEY);
        DadosSerie serie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(serie.toString());

        List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i=1; i <= serie.totalTemporadas(); i++ ){
			var jsonTemporada = consumoAPI.obterDados(DOMINIO + nmSerie.replaceAll(" ", "+") + "&season="+i +  API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
//        for (int i = 0; i < serie.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            System.out.printf("\n==== TEMPORADA: %s\n", i+1);
//            for (int a = 0; a < episodiosTemporada.size(); a++){
//                System.out.println(episodiosTemporada.get(a).tituloEpisodio());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.tituloEpisodio())) );
    }
}
