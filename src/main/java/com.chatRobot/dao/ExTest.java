package com.chatRobot.dao;

import com.chatRobot.model.DimExtendsim;
import com.chatRobot.model.DimStepCa;
import com.chatRobot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Demo class
 *
 * @author wjy
 * @date 2018/10/31
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class ExTest {

    @Autowired
    private IUserDao dao;

    @Autowired
    private DimExtendsimDAO extendsimDAO;

    @Autowired
    private DimStepCaDAO stepCaDAO;

    public static void main(String[] args) {

        long atartTime = System.currentTimeMillis();
        // System.out.println(action(60));
        long endTime = System.currentTimeMillis();

    }


    @Test
    public void count() throws Exception {


        List<User> userList = new ArrayList<User>();

        int n1 = 1;
        int n2 = 1;


//        for (int i = 1; i <= 92; i++) {
//            User user = new User();
//
//            n2 = n2 + n1;
//            user.setOthers(n1);
//            user.setOthers2(n2);
//            userList.add(user);
//        }
        dao.batchInsertList(userList);

    }


    @Test
    public void count1() throws Exception {


        List<User> userList = new ArrayList<User>();

        User user1 = new User();

        User user2 = new User();

        user1.setOthers(1);
        user1.setOthers2(2);
        user2.setOthers(3);
        user2.setOthers2(4);
        userList.add(user1);
        userList.add(user2);


        for (int i = 0; i < userList.size(); i++) {

            System.out.println(i);

            System.out.println(userList.get(i).getOthers() + "@@@@@@@@@");
            System.out.println(userList.get(i).getOthers2() + "#######");

        }

    }


    @Test
    public void action() {

        //获取开始时间
        long startTime = System.currentTimeMillis();


        int posId = 136307;
        int userId = 123;
        Integer leadDays = 20;
        List<String> names = stepCaDAO.selectNameList(posId, userId);

        // 补几天 freq_fct
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7};

        Map<Integer, int[]> freqMap = new HashMap<>(16);
        freqMap.put(1, new int[]{1});
        freqMap.put(2, new int[]{1, 3});
        freqMap.put(3, new int[]{1, 2, 6});
        freqMap.put(4, new int[]{1, 2, 3, 5});
        freqMap.put(5, new int[]{1, 3, 4, 5, 6});
        freqMap.put(6, new int[]{1, 3, 4, 5, 6, 7});
        freqMap.put(7, new int[]{1, 2, 3, 4, 5, 6, 7});
        freqMap.put(0, new int[]{6});

        //进货周期
        int[] b = {7, 8, 9, 10, 11};


        int i = 0;
        for (int m = 0; m < names.size(); m++) {

            List<DimStepCa> list = stepCaDAO.selectListByRecordId((m + 1) + "", posId, userId);
            String initialInventoryRsp = stepCaDAO.selectNameByRecordId((m + 1) + "", "-2", posId, userId);
            String totalDemandRsp = stepCaDAO.selectNameByRecordId((m + 1) + "", "-1", posId, userId);
            List<DimExtendsim> listD = new ArrayList<>();

            for (int fre : a) {
                for (int targetDay : b) {
                    int totalSale = 0;
                    int totalPlen = 0;

                    int inventoryRsp = 0;

                    List<Integer> resultList = new ArrayList<>();
                    int j = i++;
                    for (int k = 0; k < list.size(); k++) {

                        if (names.get(m) != null && names.get(m).length() > 0) {

                            DimExtendsim extendsim = new DimExtendsim();
                            extendsim.setScenarioName("Scenario" + String.format("%03d", j));
                            extendsim.setFreqFct(fre);
                            extendsim.setProdCd(names.get(m));
                            extendsim.setPosId(posId);
                            extendsim.setUserId(userId);
                            extendsim.setTargetDaysFct(targetDay);
                            extendsim.setTotalDemandRsp(null == totalDemandRsp ? 0 : Integer.parseInt(totalDemandRsp));
                            extendsim.setInitialInventoryRsp(null == initialInventoryRsp ? 0 : Integer.parseInt(initialInventoryRsp));
                            extendsim.setDayRsp(Integer.parseInt(list.get(k).getName()));

                            int actSale = 0;

                            if (null == list.get(k).getValue() || "".equals(list.get(k).getValue()) || "null".equals(list.get(k).getValue())) {

                                actSale = 0;
                            } else {
                                actSale = Integer.parseInt(list.get(k).getValue());
                            }


                            // actSale = list.get(k).getValue().equals("null") ? 0 : Integer.parseInt(list.get(k).getValue());
                            extendsim.setActualSalesRsp(actSale);

                            //   int inventory_rsp = Integer.parseInt(initialInventoryRsp) - actSale;
                            int lastSevenDaySale = actSale;
                            totalSale = actSale + totalSale;


                            int noPlen = (Integer.parseInt(totalDemandRsp) - Integer.parseInt(initialInventoryRsp)) > 0 ? (Integer.parseInt(totalDemandRsp) - Integer.parseInt(initialInventoryRsp)) : 0;


                            //总计销量
                            if (Integer.parseInt(list.get(k).getName()) == 0) {
                                extendsim.setAccumulatedSales(actSale);
                            } else if (Integer.parseInt(list.get(k).getName()) > 0) {
                                extendsim.setAccumulatedSales(totalSale);
                            }
                            // 近7天销量
                            if (Integer.parseInt(list.get(k).getName()) <= 6) {
                                extendsim.setLastSevenDaysSales(extendsim.getAccumulatedSales());
                            } else if (Integer.parseInt(list.get(k).getName()) > 6) {
                                int actSalek1 = 0;
                                int actSalek2 = 0;
                                int actSalek3 = 0;
                                int actSalek4 = 0;
                                int actSalek5 = 0;
                                int actSalek6 = 0;


                                if (null == list.get(k - 1).getValue() || "".equals(list.get(k - 1).getValue()) || "null".equals(list.get(k - 1).getValue())) {

                                    actSalek1 = 0;
                                } else {
                                    actSalek1 = Integer.parseInt(list.get(k - 1).getValue());
                                }
                                if (null == list.get(k - 2).getValue() || "".equals(list.get(k - 2).getValue()) || "null".equals(list.get(k - 2).getValue())) {

                                    actSalek2 = 0;
                                } else {
                                    actSalek2 = Integer.parseInt(list.get(k - 2).getValue());
                                }
                                if (null == list.get(k - 3).getValue() || "".equals(list.get(k - 3).getValue()) || "null".equals(list.get(k - 3).getValue())) {

                                    actSalek3 = 0;
                                } else {
                                    actSalek3 = Integer.parseInt(list.get(k - 3).getValue());
                                }
                                if (null == list.get(k - 4).getValue() || "".equals(list.get(k - 4).getValue()) || "null".equals(list.get(k - 4).getValue())) {

                                    actSalek4 = 0;
                                } else {
                                    actSalek4 = Integer.parseInt(list.get(k - 4).getValue());
                                }
                                if (null == list.get(k - 5).getValue() || "".equals(list.get(k - 5).getValue()) || "null".equals(list.get(k - 5).getValue())) {

                                    actSalek5 = 0;
                                } else {
                                    actSalek5 = Integer.parseInt(list.get(k - 5).getValue());
                                }
                                if (null == list.get(k - 6).getValue() || "".equals(list.get(k - 6).getValue()) || "null".equals(list.get(k - 6).getValue())) {

                                    actSalek6 = 0;
                                } else {
                                    actSalek6 = Integer.parseInt(list.get(k - 6).getValue());
                                }

                                extendsim.setLastSevenDaysSales(actSale + actSalek1 + actSalek2 + actSalek3 + actSalek4 + actSalek5 + actSalek6);

                            }

                            // 店铺当前库存
                            if (Integer.parseInt(list.get(k).getName()) < leadDays && Integer.parseInt(list.get(k).getName()) >= 0) {
                                inventoryRsp = Integer.parseInt(initialInventoryRsp) - extendsim.getAccumulatedSales();
                                extendsim.setInventoryRsp(inventoryRsp);

                            } else if (Integer.parseInt(list.get(k).getName()) >= leadDays) {
                                inventoryRsp = Integer.parseInt(initialInventoryRsp) - extendsim.getAccumulatedSales() + resultList.get(k - leadDays);

                                extendsim.setInventoryRsp(inventoryRsp);

                            }


                            extendsim.setFlag(0);
                            int[] locals = freqMap.get(fre);
                            if (fre == 7) {
                                extendsim.setFlag(1);
                            } else if (fre == 0) {

                                for (int local : locals) {
                                    //补货flag
                                    if ((Integer.parseInt(list.get(k).getName()) + 1) % 14 == local) {
                                        extendsim.setFlag(1);
                                    }

                                }


                            } else {
                                for (int local : locals) {
                                    //补货flag
                                    if ((Integer.parseInt(list.get(k).getName()) + 1) % 7 == local) {
                                        extendsim.setFlag(1);
                                    }

                                }
                            }


                            //  店铺当天进货量


                            if (Integer.parseInt(list.get(k).getName()) > 0 && extendsim.getFlag() == 1) {
                                Integer result = ((int) Math.ceil(extendsim.getLastSevenDaysSales() / 7 * extendsim.getTargetDaysFct()) - extendsim.getInventoryRsp()) > 0 ?
                                        ((int) Math.ceil(extendsim.getLastSevenDaysSales() / 7 * extendsim.getTargetDaysFct()) - extendsim.getInventoryRsp()) : 0;
                                resultList.add(result);
                                extendsim.setReplenQtyRsp(result);

                            } else {
                                resultList.add(0);
                                extendsim.setReplenQtyRsp(0);

                            }

                            totalPlen = totalPlen + extendsim.getReplenQtyRsp();

                            //总计补货
                            if (Integer.parseInt(list.get(k).getName()) == 0) {
                                extendsim.setTotalReplen(0);

                            } else if (Integer.parseInt(list.get(k).getName()) > 0) {

                                extendsim.setTotalReplen(totalPlen);
                            }

                            //补货池剩余


                            noPlen = noPlen - totalPlen;


                            if (Integer.parseInt(list.get(k).getName()) == 0) {

                                extendsim.setNoReplen(noPlen);

                            } else if (Integer.parseInt(list.get(k).getName()) > 0) {
                                extendsim.setNoReplen(noPlen > 0 ? noPlen : 0);
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


//


                            extendsim.setOrderingCostRsp("30");
                            extendsim.setTransportingCostRsp("30");
                            extendsim.setHoldingCostRsp("30");
                            extendsim.setUpdateTime(new Date());

                            listD.add(extendsim);
                        }
                    }
                }

            }
            extendsimDAO.batchInsertListDim(listD);
        }
        //获取结束时间
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

    }


}