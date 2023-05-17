package com.dhd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//卡牌类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card{
    char suit;
    String point;
}
