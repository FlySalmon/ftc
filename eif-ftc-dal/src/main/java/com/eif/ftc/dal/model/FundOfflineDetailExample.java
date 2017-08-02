package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundOfflineDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundOfflineDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidIsNull() {
            addCriterion("fund_offline_detail_uuid is null");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidIsNotNull() {
            addCriterion("fund_offline_detail_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidEqualTo(String value) {
            addCriterion("fund_offline_detail_uuid =", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidNotEqualTo(String value) {
            addCriterion("fund_offline_detail_uuid <>", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidGreaterThan(String value) {
            addCriterion("fund_offline_detail_uuid >", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidGreaterThanOrEqualTo(String value) {
            addCriterion("fund_offline_detail_uuid >=", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidLessThan(String value) {
            addCriterion("fund_offline_detail_uuid <", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidLessThanOrEqualTo(String value) {
            addCriterion("fund_offline_detail_uuid <=", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidLike(String value) {
            addCriterion("fund_offline_detail_uuid like", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidNotLike(String value) {
            addCriterion("fund_offline_detail_uuid not like", value, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidIn(List<String> values) {
            addCriterion("fund_offline_detail_uuid in", values, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidNotIn(List<String> values) {
            addCriterion("fund_offline_detail_uuid not in", values, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidBetween(String value1, String value2) {
            addCriterion("fund_offline_detail_uuid between", value1, value2, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundOfflineDetailUuidNotBetween(String value1, String value2) {
            addCriterion("fund_offline_detail_uuid not between", value1, value2, "fundOfflineDetailUuid");
            return (Criteria) this;
        }

        public Criteria andMemberNoIsNull() {
            addCriterion("member_no is null");
            return (Criteria) this;
        }

        public Criteria andMemberNoIsNotNull() {
            addCriterion("member_no is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNoEqualTo(String value) {
            addCriterion("member_no =", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoNotEqualTo(String value) {
            addCriterion("member_no <>", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoGreaterThan(String value) {
            addCriterion("member_no >", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoGreaterThanOrEqualTo(String value) {
            addCriterion("member_no >=", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoLessThan(String value) {
            addCriterion("member_no <", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoLessThanOrEqualTo(String value) {
            addCriterion("member_no <=", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoLike(String value) {
            addCriterion("member_no like", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoNotLike(String value) {
            addCriterion("member_no not like", value, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoIn(List<String> values) {
            addCriterion("member_no in", values, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoNotIn(List<String> values) {
            addCriterion("member_no not in", values, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoBetween(String value1, String value2) {
            addCriterion("member_no between", value1, value2, "memberNo");
            return (Criteria) this;
        }

        public Criteria andMemberNoNotBetween(String value1, String value2) {
            addCriterion("member_no not between", value1, value2, "memberNo");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountIsNull() {
            addCriterion("fund_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountIsNotNull() {
            addCriterion("fund_total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountEqualTo(BigDecimal value) {
            addCriterion("fund_total_amount =", value, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("fund_total_amount <>", value, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("fund_total_amount >", value, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_total_amount >=", value, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountLessThan(BigDecimal value) {
            addCriterion("fund_total_amount <", value, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_total_amount <=", value, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountIn(List<BigDecimal> values) {
            addCriterion("fund_total_amount in", values, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("fund_total_amount not in", values, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_total_amount between", value1, value2, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andFundTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_total_amount not between", value1, value2, "fundTotalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalProfitIsNull() {
            addCriterion("total_profit is null");
            return (Criteria) this;
        }

        public Criteria andTotalProfitIsNotNull() {
            addCriterion("total_profit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalProfitEqualTo(BigDecimal value) {
            addCriterion("total_profit =", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitNotEqualTo(BigDecimal value) {
            addCriterion("total_profit <>", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitGreaterThan(BigDecimal value) {
            addCriterion("total_profit >", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_profit >=", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitLessThan(BigDecimal value) {
            addCriterion("total_profit <", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_profit <=", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitIn(List<BigDecimal> values) {
            addCriterion("total_profit in", values, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitNotIn(List<BigDecimal> values) {
            addCriterion("total_profit not in", values, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_profit between", value1, value2, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_profit not between", value1, value2, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountIsNull() {
            addCriterion("bonus_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountIsNotNull() {
            addCriterion("bonus_total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountEqualTo(BigDecimal value) {
            addCriterion("bonus_total_amount =", value, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("bonus_total_amount <>", value, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("bonus_total_amount >", value, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bonus_total_amount >=", value, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountLessThan(BigDecimal value) {
            addCriterion("bonus_total_amount <", value, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bonus_total_amount <=", value, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountIn(List<BigDecimal> values) {
            addCriterion("bonus_total_amount in", values, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("bonus_total_amount not in", values, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bonus_total_amount between", value1, value2, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bonus_total_amount not between", value1, value2, "bonusTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountIsNull() {
            addCriterion("profit_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountIsNotNull() {
            addCriterion("profit_total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountEqualTo(BigDecimal value) {
            addCriterion("profit_total_amount =", value, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("profit_total_amount <>", value, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("profit_total_amount >", value, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_total_amount >=", value, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountLessThan(BigDecimal value) {
            addCriterion("profit_total_amount <", value, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_total_amount <=", value, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountIn(List<BigDecimal> values) {
            addCriterion("profit_total_amount in", values, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("profit_total_amount not in", values, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_total_amount between", value1, value2, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andProfitTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_total_amount not between", value1, value2, "profitTotalAmount");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeIsNull() {
            addCriterion("settlement_time is null");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeIsNotNull() {
            addCriterion("settlement_time is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeEqualTo(Date value) {
            addCriterion("settlement_time =", value, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeNotEqualTo(Date value) {
            addCriterion("settlement_time <>", value, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeGreaterThan(Date value) {
            addCriterion("settlement_time >", value, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("settlement_time >=", value, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeLessThan(Date value) {
            addCriterion("settlement_time <", value, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeLessThanOrEqualTo(Date value) {
            addCriterion("settlement_time <=", value, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeIn(List<Date> values) {
            addCriterion("settlement_time in", values, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeNotIn(List<Date> values) {
            addCriterion("settlement_time not in", values, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeBetween(Date value1, Date value2) {
            addCriterion("settlement_time between", value1, value2, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andSettlementTimeNotBetween(Date value1, Date value2) {
            addCriterion("settlement_time not between", value1, value2, "settlementTime");
            return (Criteria) this;
        }

        public Criteria andHasSettlementIsNull() {
            addCriterion("has_settlement is null");
            return (Criteria) this;
        }

        public Criteria andHasSettlementIsNotNull() {
            addCriterion("has_settlement is not null");
            return (Criteria) this;
        }

        public Criteria andHasSettlementEqualTo(Integer value) {
            addCriterion("has_settlement =", value, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementNotEqualTo(Integer value) {
            addCriterion("has_settlement <>", value, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementGreaterThan(Integer value) {
            addCriterion("has_settlement >", value, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementGreaterThanOrEqualTo(Integer value) {
            addCriterion("has_settlement >=", value, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementLessThan(Integer value) {
            addCriterion("has_settlement <", value, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementLessThanOrEqualTo(Integer value) {
            addCriterion("has_settlement <=", value, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementIn(List<Integer> values) {
            addCriterion("has_settlement in", values, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementNotIn(List<Integer> values) {
            addCriterion("has_settlement not in", values, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementBetween(Integer value1, Integer value2) {
            addCriterion("has_settlement between", value1, value2, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andHasSettlementNotBetween(Integer value1, Integer value2) {
            addCriterion("has_settlement not between", value1, value2, "hasSettlement");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalIsNull() {
            addCriterion("settlement_capital is null");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalIsNotNull() {
            addCriterion("settlement_capital is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalEqualTo(BigDecimal value) {
            addCriterion("settlement_capital =", value, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalNotEqualTo(BigDecimal value) {
            addCriterion("settlement_capital <>", value, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalGreaterThan(BigDecimal value) {
            addCriterion("settlement_capital >", value, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settlement_capital >=", value, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalLessThan(BigDecimal value) {
            addCriterion("settlement_capital <", value, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settlement_capital <=", value, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalIn(List<BigDecimal> values) {
            addCriterion("settlement_capital in", values, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalNotIn(List<BigDecimal> values) {
            addCriterion("settlement_capital not in", values, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settlement_capital between", value1, value2, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andSettlementCapitalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settlement_capital not between", value1, value2, "settlementCapital");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeIsNull() {
            addCriterion("offline_code is null");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeIsNotNull() {
            addCriterion("offline_code is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeEqualTo(String value) {
            addCriterion("offline_code =", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeNotEqualTo(String value) {
            addCriterion("offline_code <>", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeGreaterThan(String value) {
            addCriterion("offline_code >", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeGreaterThanOrEqualTo(String value) {
            addCriterion("offline_code >=", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeLessThan(String value) {
            addCriterion("offline_code <", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeLessThanOrEqualTo(String value) {
            addCriterion("offline_code <=", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeLike(String value) {
            addCriterion("offline_code like", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeNotLike(String value) {
            addCriterion("offline_code not like", value, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeIn(List<String> values) {
            addCriterion("offline_code in", values, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeNotIn(List<String> values) {
            addCriterion("offline_code not in", values, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeBetween(String value1, String value2) {
            addCriterion("offline_code between", value1, value2, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andOfflineCodeNotBetween(String value1, String value2) {
            addCriterion("offline_code not between", value1, value2, "offlineCode");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIsNull() {
            addCriterion("customer_phone is null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIsNotNull() {
            addCriterion("customer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneEqualTo(String value) {
            addCriterion("customer_phone =", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotEqualTo(String value) {
            addCriterion("customer_phone <>", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneGreaterThan(String value) {
            addCriterion("customer_phone >", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("customer_phone >=", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLessThan(String value) {
            addCriterion("customer_phone <", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLessThanOrEqualTo(String value) {
            addCriterion("customer_phone <=", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLike(String value) {
            addCriterion("customer_phone like", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotLike(String value) {
            addCriterion("customer_phone not like", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIn(List<String> values) {
            addCriterion("customer_phone in", values, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotIn(List<String> values) {
            addCriterion("customer_phone not in", values, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneBetween(String value1, String value2) {
            addCriterion("customer_phone between", value1, value2, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotBetween(String value1, String value2) {
            addCriterion("customer_phone not between", value1, value2, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoIsNull() {
            addCriterion("customer_cardno is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoIsNotNull() {
            addCriterion("customer_cardno is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoEqualTo(String value) {
            addCriterion("customer_cardno =", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoNotEqualTo(String value) {
            addCriterion("customer_cardno <>", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoGreaterThan(String value) {
            addCriterion("customer_cardno >", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoGreaterThanOrEqualTo(String value) {
            addCriterion("customer_cardno >=", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoLessThan(String value) {
            addCriterion("customer_cardno <", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoLessThanOrEqualTo(String value) {
            addCriterion("customer_cardno <=", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoLike(String value) {
            addCriterion("customer_cardno like", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoNotLike(String value) {
            addCriterion("customer_cardno not like", value, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoIn(List<String> values) {
            addCriterion("customer_cardno in", values, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoNotIn(List<String> values) {
            addCriterion("customer_cardno not in", values, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoBetween(String value1, String value2) {
            addCriterion("customer_cardno between", value1, value2, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andCustomerCardnoNotBetween(String value1, String value2) {
            addCriterion("customer_cardno not between", value1, value2, "customerCardno");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andInceptionDateIsNull() {
            addCriterion("inception_date is null");
            return (Criteria) this;
        }

        public Criteria andInceptionDateIsNotNull() {
            addCriterion("inception_date is not null");
            return (Criteria) this;
        }

        public Criteria andInceptionDateEqualTo(Date value) {
            addCriterion("inception_date =", value, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateNotEqualTo(Date value) {
            addCriterion("inception_date <>", value, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateGreaterThan(Date value) {
            addCriterion("inception_date >", value, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateGreaterThanOrEqualTo(Date value) {
            addCriterion("inception_date >=", value, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateLessThan(Date value) {
            addCriterion("inception_date <", value, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateLessThanOrEqualTo(Date value) {
            addCriterion("inception_date <=", value, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateIn(List<Date> values) {
            addCriterion("inception_date in", values, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateNotIn(List<Date> values) {
            addCriterion("inception_date not in", values, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateBetween(Date value1, Date value2) {
            addCriterion("inception_date between", value1, value2, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andInceptionDateNotBetween(Date value1, Date value2) {
            addCriterion("inception_date not between", value1, value2, "inceptionDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNull() {
            addCriterion("due_date is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("due_date is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(Date value) {
            addCriterion("due_date =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(Date value) {
            addCriterion("due_date <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(Date value) {
            addCriterion("due_date >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(Date value) {
            addCriterion("due_date >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(Date value) {
            addCriterion("due_date <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(Date value) {
            addCriterion("due_date <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<Date> values) {
            addCriterion("due_date in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<Date> values) {
            addCriterion("due_date not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(Date value1, Date value2) {
            addCriterion("due_date between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(Date value1, Date value2) {
            addCriterion("due_date not between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkIsNull() {
            addCriterion("offline_mark is null");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkIsNotNull() {
            addCriterion("offline_mark is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkEqualTo(Integer value) {
            addCriterion("offline_mark =", value, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkNotEqualTo(Integer value) {
            addCriterion("offline_mark <>", value, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkGreaterThan(Integer value) {
            addCriterion("offline_mark >", value, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("offline_mark >=", value, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkLessThan(Integer value) {
            addCriterion("offline_mark <", value, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkLessThanOrEqualTo(Integer value) {
            addCriterion("offline_mark <=", value, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkIn(List<Integer> values) {
            addCriterion("offline_mark in", values, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkNotIn(List<Integer> values) {
            addCriterion("offline_mark not in", values, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkBetween(Integer value1, Integer value2) {
            addCriterion("offline_mark between", value1, value2, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andOfflineMarkNotBetween(Integer value1, Integer value2) {
            addCriterion("offline_mark not between", value1, value2, "offlineMark");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedIsNull() {
            addCriterion("soft_deleted is null");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedIsNotNull() {
            addCriterion("soft_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedEqualTo(Integer value) {
            addCriterion("soft_deleted =", value, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedNotEqualTo(Integer value) {
            addCriterion("soft_deleted <>", value, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedGreaterThan(Integer value) {
            addCriterion("soft_deleted >", value, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("soft_deleted >=", value, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedLessThan(Integer value) {
            addCriterion("soft_deleted <", value, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("soft_deleted <=", value, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedIn(List<Integer> values) {
            addCriterion("soft_deleted in", values, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedNotIn(List<Integer> values) {
            addCriterion("soft_deleted not in", values, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedBetween(Integer value1, Integer value2) {
            addCriterion("soft_deleted between", value1, value2, "softDeleted");
            return (Criteria) this;
        }

        public Criteria andSoftDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("soft_deleted not between", value1, value2, "softDeleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}