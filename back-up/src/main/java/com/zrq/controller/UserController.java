package com.zrq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private List<Msg> message=new ArrayList<>();
    @RequestMapping(value = "/quick6" ,method = RequestMethod.POST)
    @ResponseBody
    public void save6(String username, MultipartFile uploadFile) throws IOException {
        System.out.println(username);
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("G:\\"+originalFilename));
    }

    @RequestMapping(value = "/quick5" ,method = RequestMethod.POST)
    @ResponseBody
    public void save5(@RequestBody List<User> users){
        System.out.println(users);
    }
    @RequestMapping("/quick44")
    @ResponseBody
    public List<Msg> save44(){
        return message;
    }
    @RequestMapping("/quick4")
    @ResponseBody
    public List<Msg> save4(Msg msg){
        System.out.println(msg);
        message.add(msg);
        return message;
    }

    @RequestMapping(value = "/quick3",method = RequestMethod.POST)
    @ResponseBody
    public String save3(String userid,String password){
        System.out.println(userid+"........"+password);
        if(userid.equals("123")&&password.equals("123")){
            return "success";
        }
        else{
            return "fail";
        }
    }
    @RequestMapping("/quick2")
    @ResponseBody
    public String save2(){
        System.out.println("UserController running........");
        return "quick";
    }

    @RequestMapping("/quick")
    public String save(){
        System.out.println("UserController running........");
        return "123";
    }
}
