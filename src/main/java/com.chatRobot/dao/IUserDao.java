package com.chatRobot.dao;

import com.chatRobot.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanjiayuan on 2018/3/6.
 */
@Repository
public interface IUserDao {

    void batchInsertList(@Param("userList")List<User> userList);
    void insert(@Param("others")long t1,@Param("others2")long t2);
}
