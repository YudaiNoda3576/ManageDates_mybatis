package com.example.demo.controller;

import javax.validation.GroupSequence;

//バリデーションの実行順序を設定
@GroupSequence({ValidGroup1.class, ValidGroup2.class, ValidGroup3.class})
public interface GroupOrder {

}
