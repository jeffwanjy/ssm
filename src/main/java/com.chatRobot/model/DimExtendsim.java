package com.chatRobot.model;

import java.io.Serializable;
import java.util.Date;

/**
 * dim_extendsim
 * @author
 */
public class DimExtendsim implements Serializable {
    private Integer id;

    private String scenarioName;

    /**
     * 间隔几天
     */
    private Integer freqFct;

    /**
     * sku
     */
    private String prodCd;

    /**
     *  进货周期
     */
    private Integer targetDaysFct;

    /**
     * 总下单数量
     */
    private Integer totalDemandRsp;

    /**
     * 第一批到店量
     */
    private Integer initialInventoryRsp;

    /**
     * 第几天
     */
    private Integer dayRsp;

    /**
     * 店铺当前库存
     */
    private Integer inventoryRsp;

    private Integer dcCheckRsp;

    /**
     * 有没有补货
     */
    private Integer instockRecordRsp;

    /**
     * 当天实际销售量
     */
    private Integer actualSalesRsp;

    /**
     * 近7天销售量
     */
    private Integer lastSevenDaysSales;

    /**
     * 累计销售
     */
    private Integer accumulatedSales;

    /**
     * 当天进货数量
     */
    private Integer replenQtyRsp;

    /**
     * 订单成本
     */
    private String orderingCostRsp;

    /**
     * 运输成本
     */
    private String transportingCostRsp;

    /**
     * 仓库成本
     */
    private String holdingCostRsp;

    /**
     * 店铺id
     */
    private Integer posId;

    /**
     * 操作人id
     */
    private Integer userId;

    /**
     * 1补货，0不补货
     */
    private Integer flag;

    /**
     * 补货池剩余
     */
    private Integer noReplen;

    /**
     * 累计进货
     */
    private Integer totalReplen;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public Integer getFreqFct() {
        return freqFct;
    }

    public void setFreqFct(Integer freqFct) {
        this.freqFct = freqFct;
    }

    public String getProdCd() {
        return prodCd;
    }

    public void setProdCd(String prodCd) {
        this.prodCd = prodCd;
    }

    public Integer getTargetDaysFct() {
        return targetDaysFct;
    }

    public void setTargetDaysFct(Integer targetDaysFct) {
        this.targetDaysFct = targetDaysFct;
    }

    public Integer getTotalDemandRsp() {
        return totalDemandRsp;
    }

    public void setTotalDemandRsp(Integer totalDemandRsp) {
        this.totalDemandRsp = totalDemandRsp;
    }

    public Integer getInitialInventoryRsp() {
        return initialInventoryRsp;
    }

    public void setInitialInventoryRsp(Integer initialInventoryRsp) {
        this.initialInventoryRsp = initialInventoryRsp;
    }

    public Integer getDayRsp() {
        return dayRsp;
    }

    public void setDayRsp(Integer dayRsp) {
        this.dayRsp = dayRsp;
    }

    public Integer getInventoryRsp() {
        return inventoryRsp;
    }

    public void setInventoryRsp(Integer inventoryRsp) {
        this.inventoryRsp = inventoryRsp;
    }

    public Integer getDcCheckRsp() {
        return dcCheckRsp;
    }

    public void setDcCheckRsp(Integer dcCheckRsp) {
        this.dcCheckRsp = dcCheckRsp;
    }

    public Integer getInstockRecordRsp() {
        return instockRecordRsp;
    }

    public void setInstockRecordRsp(Integer instockRecordRsp) {
        this.instockRecordRsp = instockRecordRsp;
    }

    public Integer getActualSalesRsp() {
        return actualSalesRsp;
    }

    public void setActualSalesRsp(Integer actualSalesRsp) {
        this.actualSalesRsp = actualSalesRsp;
    }

    public Integer getLastSevenDaysSales() {
        return lastSevenDaysSales;
    }

    public void setLastSevenDaysSales(Integer lastSevenDaysSales) {
        this.lastSevenDaysSales = lastSevenDaysSales;
    }

    public Integer getAccumulatedSales() {
        return accumulatedSales;
    }

    public void setAccumulatedSales(Integer accumulatedSales) {
        this.accumulatedSales = accumulatedSales;
    }

    public Integer getReplenQtyRsp() {
        return replenQtyRsp;
    }

    public void setReplenQtyRsp(Integer replenQtyRsp) {
        this.replenQtyRsp = replenQtyRsp;
    }

    public String getOrderingCostRsp() {
        return orderingCostRsp;
    }

    public void setOrderingCostRsp(String orderingCostRsp) {
        this.orderingCostRsp = orderingCostRsp;
    }

    public String getTransportingCostRsp() {
        return transportingCostRsp;
    }

    public void setTransportingCostRsp(String transportingCostRsp) {
        this.transportingCostRsp = transportingCostRsp;
    }

    public String getHoldingCostRsp() {
        return holdingCostRsp;
    }

    public void setHoldingCostRsp(String holdingCostRsp) {
        this.holdingCostRsp = holdingCostRsp;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getNoReplen() {
        return noReplen;
    }

    public void setNoReplen(Integer noReplen) {
        this.noReplen = noReplen;
    }

    public Integer getTotalReplen() {
        return totalReplen;
    }

    public void setTotalReplen(Integer totalReplen) {
        this.totalReplen = totalReplen;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DimExtendsim other = (DimExtendsim) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getScenarioName() == null ? other.getScenarioName() == null : this.getScenarioName().equals(other.getScenarioName()))
                && (this.getFreqFct() == null ? other.getFreqFct() == null : this.getFreqFct().equals(other.getFreqFct()))
                && (this.getProdCd() == null ? other.getProdCd() == null : this.getProdCd().equals(other.getProdCd()))
                && (this.getTargetDaysFct() == null ? other.getTargetDaysFct() == null : this.getTargetDaysFct().equals(other.getTargetDaysFct()))
                && (this.getTotalDemandRsp() == null ? other.getTotalDemandRsp() == null : this.getTotalDemandRsp().equals(other.getTotalDemandRsp()))
                && (this.getInitialInventoryRsp() == null ? other.getInitialInventoryRsp() == null : this.getInitialInventoryRsp().equals(other.getInitialInventoryRsp()))
                && (this.getDayRsp() == null ? other.getDayRsp() == null : this.getDayRsp().equals(other.getDayRsp()))
                && (this.getInventoryRsp() == null ? other.getInventoryRsp() == null : this.getInventoryRsp().equals(other.getInventoryRsp()))
                && (this.getDcCheckRsp() == null ? other.getDcCheckRsp() == null : this.getDcCheckRsp().equals(other.getDcCheckRsp()))
                && (this.getInstockRecordRsp() == null ? other.getInstockRecordRsp() == null : this.getInstockRecordRsp().equals(other.getInstockRecordRsp()))
                && (this.getActualSalesRsp() == null ? other.getActualSalesRsp() == null : this.getActualSalesRsp().equals(other.getActualSalesRsp()))
                && (this.getLastSevenDaysSales() == null ? other.getLastSevenDaysSales() == null : this.getLastSevenDaysSales().equals(other.getLastSevenDaysSales()))
                && (this.getAccumulatedSales() == null ? other.getAccumulatedSales() == null : this.getAccumulatedSales().equals(other.getAccumulatedSales()))
                && (this.getReplenQtyRsp() == null ? other.getReplenQtyRsp() == null : this.getReplenQtyRsp().equals(other.getReplenQtyRsp()))
                && (this.getOrderingCostRsp() == null ? other.getOrderingCostRsp() == null : this.getOrderingCostRsp().equals(other.getOrderingCostRsp()))
                && (this.getTransportingCostRsp() == null ? other.getTransportingCostRsp() == null : this.getTransportingCostRsp().equals(other.getTransportingCostRsp()))
                && (this.getHoldingCostRsp() == null ? other.getHoldingCostRsp() == null : this.getHoldingCostRsp().equals(other.getHoldingCostRsp()))
                && (this.getPosId() == null ? other.getPosId() == null : this.getPosId().equals(other.getPosId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
                && (this.getNoReplen() == null ? other.getNoReplen() == null : this.getNoReplen().equals(other.getNoReplen()))
                && (this.getTotalReplen() == null ? other.getTotalReplen() == null : this.getTotalReplen().equals(other.getTotalReplen()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getScenarioName() == null) ? 0 : getScenarioName().hashCode());
        result = prime * result + ((getFreqFct() == null) ? 0 : getFreqFct().hashCode());
        result = prime * result + ((getProdCd() == null) ? 0 : getProdCd().hashCode());
        result = prime * result + ((getTargetDaysFct() == null) ? 0 : getTargetDaysFct().hashCode());
        result = prime * result + ((getTotalDemandRsp() == null) ? 0 : getTotalDemandRsp().hashCode());
        result = prime * result + ((getInitialInventoryRsp() == null) ? 0 : getInitialInventoryRsp().hashCode());
        result = prime * result + ((getDayRsp() == null) ? 0 : getDayRsp().hashCode());
        result = prime * result + ((getInventoryRsp() == null) ? 0 : getInventoryRsp().hashCode());
        result = prime * result + ((getDcCheckRsp() == null) ? 0 : getDcCheckRsp().hashCode());
        result = prime * result + ((getInstockRecordRsp() == null) ? 0 : getInstockRecordRsp().hashCode());
        result = prime * result + ((getActualSalesRsp() == null) ? 0 : getActualSalesRsp().hashCode());
        result = prime * result + ((getLastSevenDaysSales() == null) ? 0 : getLastSevenDaysSales().hashCode());
        result = prime * result + ((getAccumulatedSales() == null) ? 0 : getAccumulatedSales().hashCode());
        result = prime * result + ((getReplenQtyRsp() == null) ? 0 : getReplenQtyRsp().hashCode());
        result = prime * result + ((getOrderingCostRsp() == null) ? 0 : getOrderingCostRsp().hashCode());
        result = prime * result + ((getTransportingCostRsp() == null) ? 0 : getTransportingCostRsp().hashCode());
        result = prime * result + ((getHoldingCostRsp() == null) ? 0 : getHoldingCostRsp().hashCode());
        result = prime * result + ((getPosId() == null) ? 0 : getPosId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getNoReplen() == null) ? 0 : getNoReplen().hashCode());
        result = prime * result + ((getTotalReplen() == null) ? 0 : getTotalReplen().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", scenarioName=").append(scenarioName);
        sb.append(", freqFct=").append(freqFct);
        sb.append(", prodCd=").append(prodCd);
        sb.append(", targetDaysFct=").append(targetDaysFct);
        sb.append(", totalDemandRsp=").append(totalDemandRsp);
        sb.append(", initialInventoryRsp=").append(initialInventoryRsp);
        sb.append(", dayRsp=").append(dayRsp);
        sb.append(", inventoryRsp=").append(inventoryRsp);
        sb.append(", dcCheckRsp=").append(dcCheckRsp);
        sb.append(", instockRecordRsp=").append(instockRecordRsp);
        sb.append(", actualSalesRsp=").append(actualSalesRsp);
        sb.append(", lastSevenDaysSales=").append(lastSevenDaysSales);
        sb.append(", accumulatedSales=").append(accumulatedSales);
        sb.append(", replenQtyRsp=").append(replenQtyRsp);
        sb.append(", orderingCostRsp=").append(orderingCostRsp);
        sb.append(", transportingCostRsp=").append(transportingCostRsp);
        sb.append(", holdingCostRsp=").append(holdingCostRsp);
        sb.append(", posId=").append(posId);
        sb.append(", userId=").append(userId);
        sb.append(", flag=").append(flag);
        sb.append(", noReplen=").append(noReplen);
        sb.append(", totalReplen=").append(totalReplen);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}