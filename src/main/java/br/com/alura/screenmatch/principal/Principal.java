package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final Scanner sc = new Scanner(System.in);
    private static final String DOMINIO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=f6818b3c";
    private static final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final ConverteDados conversor = new ConverteDados();
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                1 - Buscar series
                2 - Buscar episodios
                3 - Listar series buscadas
                
                0 - Sair
                """;

            System.out.println(menu);
            opcao = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine();

            switch (opcao){
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opacao invalida");
                    break;
            }
        }
    }

    private void listarSeriesBuscadas() {
        dadosSeries.forEach(System.out::println);
    }

    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumoAPI.obterDados(DOMINIO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da serie para busca: ");
        var nomeSerie = sc.nextLine();
        var json = consumoAPI.obterDados(DOMINIO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie serie = conversor.obterDados(json, DadosSerie.class);
        return serie;
    }

    /**
     * Este metodo tem como objetivo um conteudo didatico, para treinamento de conhecimento em stream de dados e funcoes lambdas
     *
     * Curso Alura: Java: Trabalhando com lambdas, stream e Spring Framework
     */
    public void exibeMenuStream(){

        System.out.println("Digite o nome de uma serie: ");
        var nmSerie = sc.nextLine();
        var json = consumoAPI.obterDados(DOMINIO + nmSerie.replace(" ", "+") + API_KEY);
        System.out.println("Json depois da requisicao: "+json);
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
//            for (DadosEpisodio dadosEpisodio : episodiosTemporada){
//                System.out.println(dadosEpisodio.tituloEpisodio());
//            }
//        }

//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.tituloEpisodio())) );


        List<String> nomess = Arrays.asList("Lilica", "Eliel", "Thaina", "Theo", "Elisa", "Valdeni", "Nanci");

        nomess.stream()
                .sorted()
                .limit(4)
                .filter(n -> n.startsWith("E"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList()); // tambem funciona adicionando somente o toList(), porem a lista se torna imutavel


//        System.out.println("\nTop 10 Episodios");
//        dadosEpisodios.stream().filter(e -> !e.notaIMDB().equalsIgnoreCase("N/A"))
//                .peek( e -> System.out.println("Primeiro Filtro(N/A) " + e))
//                .sorted(Comparator.comparing(DadosEpisodio::notaIMDB).reversed())
//                .peek( e -> System.out.println("Ordenacao " + e))
//                .limit(10)
//                .peek( e -> System.out.println("Limite " + e))
//                .map(e ->  e.tituloEpisodio().toUpperCase())
//                .peek( e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(e -> new Episodio(t.numeroTemporada(), e))
                ).collect(Collectors.toList());

//        episodios.forEach(System.out::println);

//        System.out.println("Digite um trecho do titulo do episodio: ");
//        var trechoTitulo = sc.nextLine();
//
//       Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//
//       if (episodioBuscado.isPresent()){
//           System.out.println("Episodio encontrado!");
//           System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//       } else {
//           System.out.println("Episodio nao encontrado!");
//       }

//
//        System.out.println("A partir de qual ano voce deseja ver os episodios? ");
//        var ano = sc.nextInt();
//        sc.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream().filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                "| Episodio: " + e.getTitulo() +
//                                "| Data de lancamento: " + e.getDataLancamento().format(formatter)
//                ));
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getNotaIMDB() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getNotaIMDB)));

        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getNotaIMDB() > 0.0)
                .peek(System.out::println)
                .collect(Collectors.summarizingDouble(Episodio::getNotaIMDB));

        System.out.println("Media: " + est.getAverage());
        System.out.println("Melhor Episodio: " + est.getMax());
        System.out.println("Pior Episodio: " + est.getMin());
        System.out.println("Quantidade de episodios: " + est.getCount());
    }


}
