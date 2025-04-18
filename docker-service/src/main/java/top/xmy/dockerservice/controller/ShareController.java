package top.xmy.dockerservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xmy.dockerservice.enty.Share;
import top.xmy.dockerservice.service.ShareService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ShareController {
    private final ShareService  shareService;

    @GetMapping("/shares")
    public List<Share> getShares() {
        return shareService.list();
    }
}
