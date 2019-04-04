package com.chatRobot.dao;


import com.chatRobot.model.DimStepCa;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DimStepCaDAO继承基类
 */
@Repository
public interface DimStepCaDAO extends MyBatisBaseDao<DimStepCa, Integer> {


   List<DimStepCa>  selectListByRecordId(@Param("record_id")String record_id,@Param("posId")int posId,@Param("userId")int userId);
   String  selectNameByRecordId(@Param("record_id")String record_id,@Param("name")String name,@Param("posId")int posId,@Param("userId")int userId);
   List<String>  selectNameList(@Param("posId")int posId,@Param("userId")int userId);
}