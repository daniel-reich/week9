package Spring_RestTemplate_Method;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

@SpringBootApplication


public class Application {



    public static void main(String args[]) {
        HashMap<String, String> map = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        System.out.println(quote.getValue().getId());
    }

}

