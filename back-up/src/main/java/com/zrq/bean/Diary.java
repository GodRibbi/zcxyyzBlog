package com.zrq.bean;

import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "diary")
public class Diary {

    @Id
    @KeySql(useGeneratedKeys = true)
    private int diary_id;
    
    private Date diary_date;

    private String diary_content;
}
