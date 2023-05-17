package com.dhd.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhd.domain.Game;
import com.dhd.domain.Record;
import com.dhd.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class GameService {

    @Autowired
    GameMapper gameMapper;
    public int consoleGame(String offensive){
        //先判断是否有未结束的比赛
        Game gameIng = getGameInProgress(offensive);
        if(gameIng!=null){ // 正在比赛中
            return gameIng.getGameId();
        }else{//若无则重新开始比赛
            Game game = new Game();
            game.setOffensive(offensive);
            game.setDefensive("robot");
            game.setGameTime(getCurrentTime());
            gameMapper.insert(game);
            return getGameInProgress(offensive).getGameId();
        }
    }

    public Game getGameInProgress(String offensive){ // 获取正在进行的游戏
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("offensive",offensive);
        wrapper.eq("status","true");
        return gameMapper.selectOne(wrapper);
    }

    public String getCurrentTime(){ // 获得当前时间
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public boolean gameOver(int gameId,int userScore,int robotScore){
       try{
           Game game = gameMapper.selectById(gameId);
           game.setGameTime(getCurrentTime());
           game.setResult(userScore>robotScore);
           game.setScore(userScore+":"+robotScore);
           game.setStatus(true);
           gameMapper.updateById(game);
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    public ArrayList<Record> getRecord(String account){
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("offensive",account);
        wrapper.eq("status",true);
        Game[] games = gameMapper.selectList(wrapper).toArray(new Game[0]);
        ArrayList<Record> records = new ArrayList<>();
        for (int i = 0; i < games.length; i++) {
            if(games[i].isStatus()){
                Record record = new Record();
                record.opponent = games[i].getDefensive();
                record.result = games[i].isResult() ? "胜利":"失败";
                record.time = games[i].getGameTime();
                record.score = games[i].getScore();
                records.add(record);
            }
        }
        return records;
    }

}
