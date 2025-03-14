package top.xmy.requestservice.controller;

import org.springframework.web.bind.annotation.*;
import top.xmy.requestservice.entity.User;
import top.xmy.requestservice.openfeign.NodeJsClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final NodeJsClient nodeJsClient;

    public RequestController(NodeJsClient nodeJsClient) {
        this.nodeJsClient = nodeJsClient;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return nodeJsClient.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return nodeJsClient.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return nodeJsClient.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return nodeJsClient.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        nodeJsClient.deleteUser(id);
    }
}
