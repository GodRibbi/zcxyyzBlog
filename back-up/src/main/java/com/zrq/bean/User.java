package com.zrq.bean;

import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

//自动编写getset
@Data
@Table(name = "users")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String username;

    private String password;
}
