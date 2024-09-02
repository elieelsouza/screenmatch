package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

public class ConsumoTradutor {
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
}
