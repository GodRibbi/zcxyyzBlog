package com.zrq.bean;

import java.util.List;

import lombok.Data;

@Data
public class DiaryJson {

    private int year;

    private List<Diary> diaries;
}
