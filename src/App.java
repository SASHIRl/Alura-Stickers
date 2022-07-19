import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 movies
        // Passo a URL para uma váriavel que recebe uma String
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        // Nesse caso declarei como var, o Java sabe que é um HttpClient
        var client = HttpClient.newHttpClient();
        // Nesse caso declarei como var, o Java sabe que é um HttpRequest
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);


        // Pegar apenas os dados que interessam (título, poster, classificação)

        // Exibir e manipular os dados
    }
}