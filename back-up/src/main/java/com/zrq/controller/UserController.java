package com.zrq.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.zrq.mapper.UserMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession; 
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
    UserMapper mapper;
    SqlSession sqlSession;

    public UserController() throws IOException {
        // 获取核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获得session工厂对象
        SqlSessionFactory SqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得session回话对象
        sqlSession = SqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);
    }
    // public static void main(String [] args) throws IOException {
    //     // 获取核心配置文件
    //     InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    //     // 获得session工厂对象
    //     SqlSessionFactory SqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    //     // 获得session回话对象
    //     SqlSession sqlSession = SqlSessionFactory.openSession();
    //     UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    //     mapper.AddUser(new User("1233", "3421"));
    //     sqlSession.commit();
    //  }
    private List<Msg> message = new ArrayList<>();

    @RequestMapping(value = "/quick6", method = RequestMethod.POST)
    @ResponseBody
    
    public void save6(String username, MultipartFile uploadFile) throws IOException {
        System.out.println(username);
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("G:\\" + originalFilename));
    }

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String UpdatePassword(String useremail,String password){
        mapper.UpdatePassword(useremail, password);
        sqlSession.commit();
        return "success";
    }

    @RequestMapping(value = "/SendEmail", method = RequestMethod.GET)
    @ResponseBody
    public int SendEmail(String useremail){
        SendEmail se=new SendEmail(useremail);
        int code=se.getCode();
        return code;
    }

    @RequestMapping(value = "/CheckUserEmail", method = RequestMethod.GET)
    @ResponseBody
    public String CheckUserEmail(String useremail) {
        List<User> users = mapper.FindByEmail(useremail);
        if (users.size() == 0)
            return "fail";
        else
            return "success";
    }

    @RequestMapping(value = "/CheckUser", method = RequestMethod.GET)
    @ResponseBody
    public String CheckUser(String useremail,String password) {
        List<User> users = mapper.CheckUser(useremail, password);
        if (users.size() == 0)
            return "fail";
        else
            return "success";
    }

    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    @ResponseBody
    public String AddUser(User user) {
        List<User> users = mapper.FindByEmail(user.getUseremail());
        if (users.size() != 0) {
            return "fail";
        }
        mapper.AddUser(user);
        sqlSession.commit();
        return "success";
    }
    @RequestMapping(value = "/Check", method = RequestMethod.POST) 
    
    @ResponseBody
    public Integer Check(@RequestParam(value="fst",defaultValue="0",required=true) String[] fst
        ,@RequestParam(value="sed",defaultValue="0",required=true) Integer sed,
        @RequestParam(value="trd",defaultValue="0",required=true) String trd,
        @RequestParam(value="fth",defaultValue="0",required=true) Integer fth) {
        Integer point = 0;
        for (String str : fst) {
            if (str.equals("1"))
                point += 10;
            if (str.equals("2"))
                point += 10;
            if (str.equals("3"))
                point += 10;
            if (str.equals("4"))
                point += 10;
        }
        if (sed == 3)
            point += 20;
        if (trd.equals("我不知道"))
            point += 20;
        if (fth == 2)
            point += 20;
        return point;
    }

    @RequestMapping("/GetMsgs")
    @ResponseBody
    public List<Msg> GetMsgs() {
        return message;
    }

    @RequestMapping(value = "/SendMsgs", method = RequestMethod.POST)
    @ResponseBody
    public List<Msg> SendMsgs(Msg msg) {
        System.out.println(msg);
        message.add(msg);
        return message;
    }

    @RequestMapping(value = "/quick3", method = RequestMethod.POST)
    @ResponseBody
    public String save3(String userid, String password) {
        System.out.println(userid + "........" + password);
        if (userid.equals("123") && password.equals("123")) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/quick2")
    @ResponseBody
    public String save2() {
        System.out.println("UserController running........");
        return "quick";
    }

    @RequestMapping("/quick")
    public String save() {
        System.out.println("UserController running........");
        return "123";
    }

}
