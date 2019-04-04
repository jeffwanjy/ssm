package com.chatRobot.dao;

import com.chatRobot.model.DimExtendsim;
import com.chatRobot.model.DimStepCa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class test3 {


    @Autowired
    private DimExtendsimDAO extendsimDAO;

    @Autowired
    private DimStepCaDAO stepCaDAO;



}