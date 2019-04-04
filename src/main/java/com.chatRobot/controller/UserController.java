package com.chatRobot.controller;

import com.chatRobot.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by wanjiayuan on 2018/3/6.
 */


@Controller
@RequestMapping("/user")
public class UserController {
//    @Resource
//    private IUserService userService;

//    @RequestMapping("/showUser.do")
//    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        long userId = Long.parseLong(request.getParameter("id"));
//        User user = this.userService.selectUser(userId);
//        ObjectMapper mapper = new ObjectMapper();
//        response.getWriter().write(mapper.writeValueAsString(user));
//        response.getWriter().close();
//    }
//
//
//    @RequestMapping(value="index.html",method=RequestMethod.POST)
//    public ModelAndView login(String username,String password,HttpServletRequest request){
//        request.setAttribute("username", username);
//        request.setAttribute("password", password);
//        return new ModelAndView("succ");
//    }



//    @RequestMapping("/showUser")
//    public void usercount(){
//
//        userService.countTime();
//
//
//    }

    public static void main(String[] args) {

        System.out.println(String.format("%03d", 1002));


    }
}




