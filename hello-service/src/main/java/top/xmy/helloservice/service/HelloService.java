package top.xmy.helloservice.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getName() {
        return "Hello, Nacos!";
    }
    public String sayHello(String name) {
        return "hello, " + name ;
    }
}
