package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.DadosTraducao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.checkerframework.checker.units.qual.C;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

import java.net.URLEncoder;

public class Tradutor {
    public static String obterTraducaoChatGPT(String texto){
        OpenAiService service = new OpenAiService("yourApiKeys");

        CompletionRequest request =  CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o portugues o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(request);
        return response.getChoices().get(0).getText();
    }

    public static String obterTraducaoNativa(String texto){
        return Translator.translate(Language.ENGLISH, Language.PORTUGUESE, texto);
    }

    public static String obterTraducaoMyMemory(String texto){
        ConsumoAPI consumo = new ConsumoAPI();
        ConverteDados conversor = new ConverteDados();

        String text = URLEncoder.encode(texto);
        String langpair = URLEncoder.encode("en|pt-br");

        String url = "https://api.mymemory.translated.net/get?q=" + text + "&langpair=" + langpair;
        String json = consumo.obterDados(url);

        DadosTraducao traducao = conversor.obterDados(json, DadosTraducao.class);

        return traducao.dadosReposta().textoTraduzido();
    }
}
