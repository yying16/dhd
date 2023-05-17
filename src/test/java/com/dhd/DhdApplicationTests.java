package com.dhd;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhd.domain.Game;
import com.dhd.mapper.GameMapper;
import com.dhd.service.CardService;
import com.dhd.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class DhdApplicationTests {

    @Autowired
    CardService cardService;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    GameService gameService;

    @Test
    void contextLoads() {
        System.out.println(gameService.getCurrentTime());
    }

    @Test
    void test1(){
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("game_id",1);
        Game game = gameMapper.selectOne(wrapper);
        System.out.println(game);
        System.out.println(game.getGameId()==1);
        System.out.println(gameMapper.selectById(game.getGameId()));
    }

    @Test
    void test2(){
    }
}
