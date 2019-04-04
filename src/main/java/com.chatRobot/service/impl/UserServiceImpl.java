//
//package com.chatRobot.service.impl;
//
//import com.chatRobot.dao.IUserDao;
//import com.chatRobot.model.User;
//import com.chatRobot.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class UserServiceImpl implements IUserService {
//
//
//    @Autowired
//    private IUserDao userDao;
//
//
//
//  @Override
//    public void countTime() {
//
//
//        List<User> userList = new ArrayList<User>();
//
//        for (int i = 1; i <= 122; i++) {
//            User user = new User();
//            user.setName(i);
//            user.setCity(i+1);
//            userList.add(user);
//        }
//        userDao.batchInsertList(userList);
//
//
//    }
//}
//
