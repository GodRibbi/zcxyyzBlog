package com.zrq.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zrq.bean.Diary;
import com.zrq.bean.DiaryJson;
import com.zrq.mapper.DiaryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;

    public List<DiaryJson> getDiarise(){
        List<DiaryJson> djList=new ArrayList<>();
        List<Diary> dList=diaryMapper.getDiarise();

        Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dList.get(0).getDiary_date());	
        int year = calendar.get(Calendar.YEAR);

        int i=0;
        djList.add(new DiaryJson());
        djList.get(i).setYear(year);
        djList.get(i).setDiaries(new ArrayList<>());

        for (Diary diary : dList) {
            Calendar calendar0 = Calendar.getInstance();
	        calendar0.setTime(diary.getDiary_date());	
            int year0 = calendar0.get(Calendar.YEAR);
            if(year0!=year){
                i++;
                year=year0;
                djList.add(new DiaryJson());
                djList.get(i).setYear(year);
                djList.get(i).setDiaries(new ArrayList<>());
                djList.get(i).getDiaries().add(diary);
            }
            else{
                djList.get(i).getDiaries().add(diary);
            }
        }
        System.out.println(djList);
        System.out.println("++++++++++++++++++++++++++");
        return djList;
    }
    public void addDiary(Diary diary) {
        diary.setDiary_date(new java.sql.Date(new java.util.Date().getTime()));
        diaryMapper.addDiary(diary);
    }
}
