package com.dhd.controller;

import com.dhd.service.GameService;
import com.dhd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/consoleGame")
    public String consoleGame(String account, Model model) {
        int gameId = gameService.consoleGame(account);
        String username = userService.getUsername(account);
        model.addAttribute("gameId", gameId);
        model.addAttribute("username", username);
        model.addAttribute("account", account);
        return "console";
    }
}
