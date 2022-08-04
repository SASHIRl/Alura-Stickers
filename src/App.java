import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 movies
        // Passo a URL para uma váriavel que recebe uma String
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_4kp3ws1j";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudoLocalHost extrator = new ExtratorDeConteudoLocalHost();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados

        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 2; i++) {
            
            Conteudo conteudo = conteudos.get(i);


            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}