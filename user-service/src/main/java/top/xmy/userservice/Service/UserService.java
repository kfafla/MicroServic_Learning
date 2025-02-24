package top.xmy.userservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public String askAI(String question) {
        String url = "http://ai-service/ai/ask?question=" + question; // Use service name instead of hardcoded URL
        return restTemplate.getForObject(url, String.class);
    }
}
