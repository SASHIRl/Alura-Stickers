import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 movies
        // Passo a URL para uma váriavel que recebe uma String
        String url = "https://imdb-api.com/en/API/Top250Movies/k_4kp3ws1j";
        URI endereco = URI.create(url);
        // Nesse caso declarei como var, o Java sabe que é um HttpClient
        var client = HttpClient.newHttpClient();
        // Nesse caso declarei como var, o Java sabe que é um HttpRequest
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Pegar apenas os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImage = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}