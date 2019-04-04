package com.chatRobot.dao;

import com.chatRobot.model.DimExtendsim;
import com.chatRobot.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DimExtendsimDAO继承基类
 */
@Repository
public interface DimExtendsimDAO extends MyBatisBaseDao<DimExtendsim, Integer> {

    Integer selectTotalSale(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName,@Param("targetDay")int targetDay,@Param("fre")int fre);
    Integer selecttotalReplen(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName);
    Integer selectsevenSales(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName,
                             @Param("targetDay")int targetDay,@Param("fre")int fre);
    Integer selectsevenSales1(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName,
                             @Param("targetDay")int targetDay,@Param("fre")int fre);
  Integer selectInventoryRsp(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName);
  Integer selectnoReplen(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName);
  Integer selectReplenQtyRsp(@Param("dayRsp")String dayRsp,@Param("scenarioName")String scenarioName);
  Integer selectInventoryRspleadDays(@Param("dayRsp")String dayRsp,@Param("leadDays")Integer leadDays,@Param("scenarioName")String scenarioName);



    void batchInsertListDim(@Param("listD") List<DimExtendsim> listD);

}