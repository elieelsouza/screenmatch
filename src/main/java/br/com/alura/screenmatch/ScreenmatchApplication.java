package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var jsonSerie = consumoAPI.obterDados("http://www.omdbapi.com/?t=Game-of-Thrones&apikey=f6818b3c");
		var jsonEpisodio = consumoAPI.obterDados("https://www.omdbapi.com/?t=Game-of-Thrones&season=1&episode=4&apikey=f6818b3c");
		//json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");

		ConverteDados conversor = new ConverteDados();
		DadosSerie dadosSerie = conversor.obterDados(jsonSerie, DadosSerie.class);
		DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEpisodio, DadosEpisodio.class);


		System.out.println(dadosSerie);
		System.out.println("======================================================================================");
		System.out.println(dadosEpisodio);
		System.out.println("======================================================================================");

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i=1; i <= dadosSerie.totalTemporadas(); i++ ){
			var jsonTemporada = consumoAPI.obterDados(String.format("https://www.omdbapi.com/?t=Game-of-Thrones&season=%s&apikey=f6818b3c", i));
			DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}
}
