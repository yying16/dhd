package com.dhd.controller;

import com.dhd.domain.Card;
import com.dhd.domain.Record;
import com.dhd.service.CardService;
import com.dhd.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;

@RestController
@EnableCaching
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    GameService gameService;

    @GetMapping("/initCard/{gameId}")
    public ArrayList<Card> initCard(@PathVariable int gameId){
        return cardService.initCard(gameId);
    }

    @GetMapping("/touchCard/{gameId}/{index}")
    public Card touchCard(@PathVariable("gameId") int gameId, @PathVariable("index") int index){
        return cardService.initCard(gameId).get(index);
    }

    //摸多张手牌
    @GetMapping("/TouchNumCard/{gameId}/{index}/{num}")
    public Card[] initTouchCard(@PathVariable("gameId") int gameId, @PathVariable("index") int index, @PathVariable("num") int num){
        Card[] cards = new Card[num];
        for (int i = 0; i < num; i++) {
            cards[i] = cardService.initCard(gameId).get(index);
            index ++;
        }
        return cards;
    }
    @GetMapping("gameOver/{gameId}/{userScore}/{robotScore}")
    public boolean gameOVer(@PathVariable("gameId") int gameId, @PathVariable("userScore") int userScore,@PathVariable("robotScore") int robotScore){
        return cardService.gameOver(gameId,userScore,robotScore);
    }

    @GetMapping("/getRecord/{account}")
    public ArrayList<Record> getRecord(@PathVariable String account){
        return gameService.getRecord(account);
    }

}
