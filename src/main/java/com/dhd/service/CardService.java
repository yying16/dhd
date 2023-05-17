package com.dhd.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhd.domain.Card;
import com.dhd.domain.Game;
import com.dhd.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@EnableCaching
public class CardService {

    @Autowired
    GameService gameService;

    @Cacheable("initCard")
    public ArrayList<Card> initCard(int gameId) { // 初始化牌堆
        ArrayList<Card> arr = new ArrayList<>();
        char[] suits = {'h', 'd', 's', 'c'};
        for (int i = 0; i < 4; i++) {
            arr.add(new Card(suits[i], "a"));
            for (int p = 2; p <= 10; p++) {
                arr.add(new Card(suits[i], String.valueOf(p)));
            }
            arr.add(new Card(suits[i], "j"));
            arr.add(new Card(suits[i], "q"));
            arr.add(new Card(suits[i], "k"));
        }
        Collections.shuffle(arr);
        return arr;
    }
    @CacheEvict(value = "initCard",key = "#gameId")
    public boolean gameOver(int gameId,int userScore,int robotScore){
        return gameService.gameOver(gameId,userScore,robotScore);
    }


}
