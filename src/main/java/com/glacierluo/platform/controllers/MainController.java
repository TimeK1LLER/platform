package com.glacierluo.platform.controllers;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.glacierluo.platform.entity.User;
import com.glacierluo.platform.repository.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.VfsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.glacierluo.platform.classes.Json;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.ExceptionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public @ResponseBody Json register (@RequestBody User user){
        if(user.checkNull()){
            userRepository.save(user);
            return new Json("Register success!", 200);
        }else{
            return new Json("Data not complete!", 400);
        }
    }

    @PostMapping("/updateProfile")
    public @ResponseBody Json updateProfile (@RequestBody User user){
        if(user.checkNull()){
            User updated = userRepository.findById(user.getId()).orElse(null);
            if(updated == null){
                return new Json("Unknow User!", 400);
            }else{
                updated.updateUser(user);
                userRepository.save(updated);
            }
//            userRepository.save(userRepository.findById(user.getId()).orElse(null).updateUser(user));
            return new Json("Update success!", 200);
        }else{
            return new Json("Data not complete", 400);
        }
    }

//    @PostMapping("deleteUser")


//    @GetMapping("userSheet")
//    public void userSheet(HttpServletResponse response) throws IOException{
//        List<User> users = new ArrayList<>();
//        userRepository.findAll().forEach(users::add);
//        ExcelU
//    }

    @GetMapping("/usersSheetExport")
    public void userSheetExport(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        String fileName = null;
        fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .getBytes(), StandardCharsets.UTF_8);
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
        Sheet sheet1 = new Sheet(1, 0, User.class);
        sheet1.setSheetName("用户表");
        List<List<String>> users = new ArrayList<>();
//        userRepository.findAll().forEach(users::add);
        for (User user : userRepository.findAll()) {
//            List<String> temp = new ArrayList<>();
//            temp.add(all.next().toListString());
            users.add(user.toListString());
        }
        writer.write0(users, sheet1);
        writer.finish();
        try {
            if (out != null) {
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/userSheetInport")
    public @ResponseBody Json userSheetExport(HttpServletRequest request) throws IOException {
        int len = request.getContentLength();
//        System.out.println(request.getContentType());
        ServletInputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[len];
        inputStream.read(buffer, 0, len);
        InputStream input = new BufferedInputStream(new ByteArrayInputStream(buffer));
        List<List> data = new ArrayList<>();
        try{
            ExcelReader excelReader = new ExcelReader(input, null,
                    new AnalysisEventListener<List<String>>() {
                        @Override
                        public void invoke(List<String> object, AnalysisContext context) {
                            System.out.println(
                                    "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum()
                                            + " data:" + object);
                            data.add(object);
                        }
                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {
                            data.remove(0);
                        }
                    });
            excelReader.read();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return new Json(data.toString(), 200);
    }

    @GetMapping("/userInfo/userID/{userID}")
    public @ResponseBody
    Optional<User> userInfo(@PathVariable Long userID){
        return userRepository.findById(userID);
    }

    @GetMapping("/userInfo/userName/{userName}")
    public @ResponseBody User userInfo(@PathVariable String userName){
        return userRepository.findByName(userName);
    }


    @GetMapping("/allUser")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
