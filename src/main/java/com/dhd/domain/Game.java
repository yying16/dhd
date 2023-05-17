package com.dhd.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_game")
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @TableId(type = IdType.ASSIGN_ID)
    private int gameId; // 游戏id
    private String offensive; // 先手
    private String defensive; // 后手
    private boolean status;// 游戏状态
    private boolean result; // 游戏结果
    private String score; // 游戏得分
    private String gameTime; // 游戏时间
}
