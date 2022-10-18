package com.zrq.controller;

import java.io.File;
import java.io.IOException;

import com.zrq.bean.ImageMsg;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class FileController {
    @RequestMapping("/blog/FileController/uploadMarkDown")
    @ResponseBody
    public void uploadFile(String name,MultipartFile upfile) throws IOException{
        upfile.transferTo(new File("/usr/local/project/blog/backup/BlogMarkdown/"+name));
        log.info("Name:"+name+'\t'+"上传MarkDown成功！");
    }
    @RequestMapping(value = "/blog/FileController/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ImageMsg uploadImage(String guid,@RequestParam("editormd-image-file") MultipartFile file) throws IOException{
        String name = file.getOriginalFilename();
        file.transferTo(new File("/usr/local/project/blog/backup/images/"+name));
        log.info("Guid:"+guid+'\t'+"Name:"+name+'\t'+"上传图片成功！");
        // System.out.println(file.getOriginalFilename());
        // System.out.println(guid);
        return new ImageMsg(1,"成功",name);
    }
}
