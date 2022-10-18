package com.zrq.controller;

import java.util.List;

import com.zrq.bean.Diary;
import com.zrq.bean.DiaryJson;
import com.zrq.service.DiaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @ResponseBody
    @RequestMapping("/blog/DiaryController/getDiarise")
    public List<DiaryJson> getDiarise() {
        log.info('\t'+"获取日记成功");
        return diaryService.getDiarise();
    }
    @ResponseBody
    @RequestMapping("/blog/DiaryController/addDiarise")
    public void addDiarise(@RequestBody Diary diary) {
        diaryService.addDiary(diary);
        log.info('\t'+"添加日记成功");
    }
}
