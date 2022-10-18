package com.zrq.mapper;

import java.util.List;

import com.zrq.bean.Diary;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

public interface DiaryMapper extends Mapper<Diary> {
    
    @Select("select * from diary order by diary_date desc")
    public List<Diary> getDiarise();

    @Insert("insert into diary (diary_date , diary_content) values (#{diary_date},#{diary_content})")
    public void addDiary(Diary diary);
}
