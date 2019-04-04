package com.chatRobot.dao;

import com.chatRobot.model.DimExtendsim;
import com.chatRobot.model.DimStepCa;
import com.chatRobot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wanjiayuan
 * @Version V1.0.0
 * @Date 2018/10/23
 */
// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {

    @Autowired
    private IUserDao dao;


    @Autowired
    private DimExtendsimDAO extendsimDAO;

    @Autowired
    private DimStepCaDAO stepCaDAO;


    @Test
    public void testSelectUser() throws Exception {

        stepCaDAO.selectByPrimaryKey(120385);

    }


    @Test
    public void count() throws Exception {


        List<User> userList = new ArrayList<User>();

        for (int i = 1; i <= 92; i++) {
            User user = new User();
            user.setName(i);
            user.setCity(i + 2);
            userList.add(user);
        }
        dao.batchInsertList(userList);

    }


    @Test
    public void test1() throws Exception {

        //间隔几天    freq_fct
        int a[] = {1, 2, 3, 5, 7, 8, 9};
        //进货周期
        int b[] = {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 67, 89};
        int posId = 136307;
        int userId = 123;

        //货物种类
        //  String d[]  = {"aaa","bbb","ccc","ddd","eee","fff","ggg","hhh"};

        long startTime = System.currentTimeMillis();    //获取开始时间

        int i = 0;

        List<DimStepCa> list = stepCaDAO.selectListByRecordId("1",posId,userId);

        List<String> names = stepCaDAO.selectNameList(posId,userId);

        String initialInventoryRsp = stepCaDAO.selectNameByRecordId("1", "-2",posId,userId);
        String totalDemandRsp = stepCaDAO.selectNameByRecordId("1", "-1",posId,userId);


        for (int targetDay : a) {
            for (int fre : b) {

                //   int a[] = {2,4};
                Integer leadDays = 2;



                for (String name : names) {
                    int j = (i++);
                    for (DimStepCa dim : list) {

                        DimExtendsim extendsim = new DimExtendsim();
                        extendsim.setScenarioName("Scenario" + String.format("%03d", j));
                        extendsim.setFreqFct(fre);
                        extendsim.setProdCd(name);
                        extendsim.setPosId(136307);
                        extendsim.setUserId(123);
                        extendsim.setTargetDaysFct(targetDay);
                        extendsim.setTotalDemandRsp(Integer.parseInt(totalDemandRsp));
                        extendsim.setInitialInventoryRsp(Integer.parseInt(initialInventoryRsp));
                        extendsim.setDayRsp(Integer.parseInt(dim.getName()));
                        extendsim.setActualSalesRsp(dim.getValue().equals("null") ? 0 : Integer.parseInt(dim.getValue()));

                        //总计销量
                        if (Integer.parseInt(dim.getName()) == 0) {
                            extendsim.setAccumulatedSales(extendsim.getActualSalesRsp());
                        } else {
                            Integer totalSale = extendsimDAO.selectTotalSale(dim.getName(),extendsim.getScenarioName(),targetDay,fre);
                            extendsim.setAccumulatedSales(totalSale + extendsim.getActualSalesRsp());
                        }
                        // 近7天销量
                        if (Integer.parseInt(dim.getName()) <= 6) {
                            extendsim.setLastSevenDaysSales(extendsim.getAccumulatedSales());
                        } else {
                            Integer sevenSales = extendsimDAO.selectsevenSales(dim.getName(),extendsim.getScenarioName(),targetDay,fre);
                            extendsim.setLastSevenDaysSales(sevenSales + extendsim.getActualSalesRsp());
                        }

                        //补货flag
                        if ((Integer.parseInt(dim.getName()) + 1) % 7 == 2 || (Integer.parseInt(dim.getName()) + 1) % 7 == 4) {
                            extendsim.setFlag(1);
                        } else {
                            extendsim.setFlag(0);
                        }

                        // 店铺当前库存
                        if (Integer.parseInt(dim.getName()) == 0) {
                            extendsim.setInventoryRsp(Integer.parseInt(initialInventoryRsp) - Integer.parseInt(dim.getValue()));
                        } else if (Integer.parseInt(dim.getName()) < leadDays && Integer.parseInt(dim.getName()) > 0) {

                            Integer inventoryRsp = extendsimDAO.selectInventoryRsp(dim.getName(),extendsim.getScenarioName());

                            System.out.println("1111111-----" + inventoryRsp);
                            extendsim.setInventoryRsp(inventoryRsp - extendsim.getActualSalesRsp());

                        } else {
                            Integer inventoryRsp = extendsimDAO.selectInventoryRspleadDays(dim.getName(), leadDays,extendsim.getScenarioName());
                            extendsim.setInventoryRsp(inventoryRsp - extendsim.getActualSalesRsp());
                        }


                        // 剩余未补货

                        if (Integer.parseInt(dim.getName()) == 0) {

                            Integer noReplen = extendsim.getTotalDemandRsp() - extendsim.getInitialInventoryRsp();
                            extendsim.setNoReplen(noReplen > 0 ? noReplen : 0);

                        } else {
                            Integer noReplen = extendsimDAO.selectnoReplen(dim.getName(),extendsim.getScenarioName());
                            extendsim.setNoReplen(noReplen > 0 ? noReplen : 0);
                        }


                        //  店铺当天进货量

                        if (Integer.parseInt(dim.getName()) > 0 && extendsim.getFlag() == 1) {
                            int result1 = (int) Math.ceil(extendsim.getLastSevenDaysSales() / 7 * extendsim.getTargetDaysFct()) - extendsim.getInventoryRsp();
                            extendsim.setReplenQtyRsp(result1 > 0 ? result1 : 0);

                        } else {
                            extendsim.setReplenQtyRsp(0);
                        }


                        //补货总计

                        if (Integer.parseInt(dim.getName()) == 0) {
                            extendsim.setTotalReplen(0);

                        } else {
                            Integer totalReplen = extendsimDAO.selecttotalReplen(dim.getName(),extendsim.getScenarioName());
                            extendsim.setTotalReplen(totalReplen + extendsim.getReplenQtyRsp());
                        }

                        if (extendsim.getInventoryRsp() > 0) {
                            extendsim.setInstockRecordRsp(1);
                        } else {
                            extendsim.setInstockRecordRsp(0);
                        }

                        if (extendsim.getTotalDemandRsp() - extendsim.getInitialInventoryRsp() - extendsim.getTotalReplen() > 0) {
                            extendsim.setDcCheckRsp(1);
                        } else {
                            extendsim.setDcCheckRsp(0);
                        }


                        extendsim.setOrderingCostRsp("1");
                        extendsim.setTransportingCostRsp("1");
                        extendsim.setHoldingCostRsp("1");

                        extendsimDAO.insertSelective(extendsim);


                    }

                }
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时

    }


    @Test
    public void test2() {

        int a[] = {2, 4};
        Integer leadDays = 2;

        int posId = 136307;
        int userId = 123;


        List<DimStepCa> list = stepCaDAO.selectListByRecordId("1",posId,userId);
        String name = stepCaDAO.selectNameByRecordId("1", "Name",posId,userId);
        String initialInventoryRsp = stepCaDAO.selectNameByRecordId("1", "-2",posId,userId);
        String totalDemandRsp = stepCaDAO.selectNameByRecordId("1", "-1",posId,userId);
        List<DimExtendsim>  listD = new ArrayList<>();

        for (DimStepCa dim : list) {

            DimExtendsim extendsim = new DimExtendsim();
            extendsim.setScenarioName("Scenario" + String.format("%03d", 1));
            extendsim.setFreqFct(1);
            extendsim.setProdCd(name);
            extendsim.setPosId(136307);
            extendsim.setUserId(123);
            extendsim.setTargetDaysFct(15);
            extendsim.setTotalDemandRsp(Integer.parseInt(totalDemandRsp));
            extendsim.setInitialInventoryRsp(Integer.parseInt(initialInventoryRsp));
            extendsim.setDayRsp(Integer.parseInt(dim.getName()));
            extendsim.setActualSalesRsp(dim.getValue().equals("null") ? 0 : Integer.parseInt(dim.getValue()));

            //总计销量
            if (Integer.parseInt(dim.getName()) == 0) {
                extendsim.setAccumulatedSales(extendsim.getActualSalesRsp());
            } else {
                Integer totalSale = extendsimDAO.selectTotalSale(dim.getName(),extendsim.getScenarioName(),15,1);
                extendsim.setAccumulatedSales(totalSale + extendsim.getActualSalesRsp());
            }
            // 近7天销量
            if (Integer.parseInt(dim.getName()) <= 6) {
                extendsim.setLastSevenDaysSales(extendsim.getAccumulatedSales());
            } else {
                Integer sevenSales = extendsimDAO.selectsevenSales(dim.getName(),extendsim.getScenarioName(),15,1);
                extendsim.setLastSevenDaysSales(sevenSales + extendsim.getActualSalesRsp());
            }

            //补货flag
            if ((Integer.parseInt(dim.getName()) + 1) % 7 == 2 || (Integer.parseInt(dim.getName()) + 1) % 7 == 4) {
                extendsim.setFlag(1);
            } else {
                extendsim.setFlag(0);
            }

            // 店铺当前库存
            if (Integer.parseInt(dim.getName()) == 0) {
                extendsim.setInventoryRsp(Integer.parseInt(initialInventoryRsp) - Integer.parseInt(dim.getValue()));
            } else if (Integer.parseInt(dim.getName()) < leadDays && Integer.parseInt(dim.getName()) > 0) {

                Integer inventoryRsp = extendsimDAO.selectInventoryRsp(dim.getName(),extendsim.getScenarioName());

                System.out.println("1111111-----" + inventoryRsp);
                extendsim.setInventoryRsp(inventoryRsp - extendsim.getActualSalesRsp());

            } else {
                Integer inventoryRsp = extendsimDAO.selectInventoryRspleadDays(dim.getName(), leadDays,extendsim.getScenarioName());
                extendsim.setInventoryRsp(inventoryRsp - extendsim.getActualSalesRsp());
            }


            // 剩余未补货

            if (Integer.parseInt(dim.getName()) == 0) {

                Integer noReplen = extendsim.getTotalDemandRsp() - extendsim.getInitialInventoryRsp();
                extendsim.setNoReplen(noReplen > 0 ? noReplen : 0);

            } else {
                Integer noReplen = extendsimDAO.selectnoReplen(dim.getName(),extendsim.getScenarioName());
                extendsim.setNoReplen(noReplen > 0 ? noReplen : 0);
            }


            //  店铺当天进货量

            if (Integer.parseInt(dim.getName()) > 0 && extendsim.getFlag() == 1) {
                int result1 = (int) Math.ceil(extendsim.getLastSevenDaysSales() / 7 * extendsim.getTargetDaysFct()) - extendsim.getInventoryRsp();
                extendsim.setReplenQtyRsp(result1 > 0 ? result1 : 0);

            } else {
                extendsim.setReplenQtyRsp(0);
            }


            //补货总计

            if (Integer.parseInt(dim.getName()) == 0) {
                extendsim.setTotalReplen(0);

            } else {
                Integer totalReplen = extendsimDAO.selecttotalReplen(dim.getName(),extendsim.getScenarioName());
                extendsim.setTotalReplen(totalReplen + extendsim.getReplenQtyRsp());
            }

            if (extendsim.getInventoryRsp() > 0) {
                extendsim.setInstockRecordRsp(1);
            } else {
                extendsim.setInstockRecordRsp(0);
            }

            if (extendsim.getTotalDemandRsp() - extendsim.getInitialInventoryRsp() - extendsim.getTotalReplen() > 0) {
                extendsim.setDcCheckRsp(1);
            } else {
                extendsim.setDcCheckRsp(0);
            }





            extendsim.setOrderingCostRsp("1");
            extendsim.setTransportingCostRsp("1");
            extendsim.setHoldingCostRsp("1");

            //extendsimDAO.insertSelective(extendsim);
            extendsim.setUpdateTime(new Date());

            listD.add(extendsim);

        }

        extendsimDAO.batchInsertListDim(listD);

    }




}
