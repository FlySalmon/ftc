package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundDetailExample() {
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

        public Criteria andFundDetailUuidIsNull() {
            addCriterion("fund_detail_uuid is null");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidIsNotNull() {
            addCriterion("fund_detail_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidEqualTo(String value) {
            addCriterion("fund_detail_uuid =", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidNotEqualTo(String value) {
            addCriterion("fund_detail_uuid <>", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidGreaterThan(String value) {
            addCriterion("fund_detail_uuid >", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidGreaterThanOrEqualTo(String value) {
            addCriterion("fund_detail_uuid >=", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidLessThan(String value) {
            addCriterion("fund_detail_uuid <", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidLessThanOrEqualTo(String value) {
            addCriterion("fund_detail_uuid <=", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidLike(String value) {
            addCriterion("fund_detail_uuid like", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidNotLike(String value) {
            addCriterion("fund_detail_uuid not like", value, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidIn(List<String> values) {
            addCriterion("fund_detail_uuid in", values, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidNotIn(List<String> values) {
            addCriterion("fund_detail_uuid not in", values, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidBetween(String value1, String value2) {
            addCriterion("fund_detail_uuid between", value1, value2, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailUuidNotBetween(String value1, String value2) {
            addCriterion("fund_detail_uuid not between", value1, value2, "fundDetailUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdIsNull() {
            addCriterion("fund_total_id is null");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdIsNotNull() {
            addCriterion("fund_total_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdEqualTo(Long value) {
            addCriterion("fund_total_id =", value, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdNotEqualTo(Long value) {
            addCriterion("fund_total_id <>", value, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdGreaterThan(Long value) {
            addCriterion("fund_total_id >", value, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fund_total_id >=", value, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdLessThan(Long value) {
            addCriterion("fund_total_id <", value, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdLessThanOrEqualTo(Long value) {
            addCriterion("fund_total_id <=", value, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdIn(List<Long> values) {
            addCriterion("fund_total_id in", values, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdNotIn(List<Long> values) {
            addCriterion("fund_total_id not in", values, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdBetween(Long value1, Long value2) {
            addCriterion("fund_total_id between", value1, value2, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalIdNotBetween(Long value1, Long value2) {
            addCriterion("fund_total_id not between", value1, value2, "fundTotalId");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidIsNull() {
            addCriterion("fund_total_uuid is null");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidIsNotNull() {
            addCriterion("fund_total_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidEqualTo(String value) {
            addCriterion("fund_total_uuid =", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidNotEqualTo(String value) {
            addCriterion("fund_total_uuid <>", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidGreaterThan(String value) {
            addCriterion("fund_total_uuid >", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidGreaterThanOrEqualTo(String value) {
            addCriterion("fund_total_uuid >=", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidLessThan(String value) {
            addCriterion("fund_total_uuid <", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidLessThanOrEqualTo(String value) {
            addCriterion("fund_total_uuid <=", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidLike(String value) {
            addCriterion("fund_total_uuid like", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidNotLike(String value) {
            addCriterion("fund_total_uuid not like", value, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidIn(List<String> values) {
            addCriterion("fund_total_uuid in", values, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidNotIn(List<String> values) {
            addCriterion("fund_total_uuid not in", values, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidBetween(String value1, String value2) {
            addCriterion("fund_total_uuid between", value1, value2, "fundTotalUuid");
            return (Criteria) this;
        }

        public Criteria andFundTotalUuidNotBetween(String value1, String value2) {
            addCriterion("fund_total_uuid not between", value1, value2, "fundTotalUuid");
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

        public Criteria andFundTotalShareIsNull() {
            addCriterion("fund_total_share is null");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareIsNotNull() {
            addCriterion("fund_total_share is not null");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareEqualTo(BigDecimal value) {
            addCriterion("fund_total_share =", value, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareNotEqualTo(BigDecimal value) {
            addCriterion("fund_total_share <>", value, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareGreaterThan(BigDecimal value) {
            addCriterion("fund_total_share >", value, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_total_share >=", value, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareLessThan(BigDecimal value) {
            addCriterion("fund_total_share <", value, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_total_share <=", value, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareIn(List<BigDecimal> values) {
            addCriterion("fund_total_share in", values, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareNotIn(List<BigDecimal> values) {
            addCriterion("fund_total_share not in", values, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_total_share between", value1, value2, "fundTotalShare");
            return (Criteria) this;
        }

        public Criteria andFundTotalShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_total_share not between", value1, value2, "fundTotalShare");
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

        public Criteria andYesterdayProfitIsNull() {
            addCriterion("yesterday_profit is null");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitIsNotNull() {
            addCriterion("yesterday_profit is not null");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit =", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitNotEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit <>", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitGreaterThan(BigDecimal value) {
            addCriterion("yesterday_profit >", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit >=", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitLessThan(BigDecimal value) {
            addCriterion("yesterday_profit <", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit <=", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitIn(List<BigDecimal> values) {
            addCriterion("yesterday_profit in", values, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitNotIn(List<BigDecimal> values) {
            addCriterion("yesterday_profit not in", values, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yesterday_profit between", value1, value2, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yesterday_profit not between", value1, value2, "yesterdayProfit");
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

        public Criteria andFrozenAmountIsNull() {
            addCriterion("frozen_amount is null");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountIsNotNull() {
            addCriterion("frozen_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountEqualTo(BigDecimal value) {
            addCriterion("frozen_amount =", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountNotEqualTo(BigDecimal value) {
            addCriterion("frozen_amount <>", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountGreaterThan(BigDecimal value) {
            addCriterion("frozen_amount >", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_amount >=", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountLessThan(BigDecimal value) {
            addCriterion("frozen_amount <", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_amount <=", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountIn(List<BigDecimal> values) {
            addCriterion("frozen_amount in", values, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountNotIn(List<BigDecimal> values) {
            addCriterion("frozen_amount not in", values, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_amount between", value1, value2, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_amount not between", value1, value2, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenShareIsNull() {
            addCriterion("frozen_share is null");
            return (Criteria) this;
        }

        public Criteria andFrozenShareIsNotNull() {
            addCriterion("frozen_share is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenShareEqualTo(BigDecimal value) {
            addCriterion("frozen_share =", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareNotEqualTo(BigDecimal value) {
            addCriterion("frozen_share <>", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareGreaterThan(BigDecimal value) {
            addCriterion("frozen_share >", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_share >=", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareLessThan(BigDecimal value) {
            addCriterion("frozen_share <", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_share <=", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareIn(List<BigDecimal> values) {
            addCriterion("frozen_share in", values, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareNotIn(List<BigDecimal> values) {
            addCriterion("frozen_share not in", values, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_share between", value1, value2, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_share not between", value1, value2, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountIsNull() {
            addCriterion("confirmed_add_amount is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountIsNotNull() {
            addCriterion("confirmed_add_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount =", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount <>", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountGreaterThan(BigDecimal value) {
            addCriterion("confirmed_add_amount >", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount >=", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountLessThan(BigDecimal value) {
            addCriterion("confirmed_add_amount <", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount <=", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_amount in", values, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_amount not in", values, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_amount between", value1, value2, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_amount not between", value1, value2, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareIsNull() {
            addCriterion("confirmed_add_share is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareIsNotNull() {
            addCriterion("confirmed_add_share is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share =", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share <>", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareGreaterThan(BigDecimal value) {
            addCriterion("confirmed_add_share >", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share >=", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareLessThan(BigDecimal value) {
            addCriterion("confirmed_add_share <", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share <=", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_share in", values, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_share not in", values, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_share between", value1, value2, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_share not between", value1, value2, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountIsNull() {
            addCriterion("unconfirmed_add_amount is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountIsNotNull() {
            addCriterion("unconfirmed_add_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount =", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount <>", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_add_amount >", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount >=", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountLessThan(BigDecimal value) {
            addCriterion("unconfirmed_add_amount <", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount <=", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_amount in", values, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_amount not in", values, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_amount between", value1, value2, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_amount not between", value1, value2, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareIsNull() {
            addCriterion("unconfirmed_add_share is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareIsNotNull() {
            addCriterion("unconfirmed_add_share is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share =", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share <>", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_add_share >", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share >=", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareLessThan(BigDecimal value) {
            addCriterion("unconfirmed_add_share <", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share <=", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_share in", values, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_share not in", values, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_share between", value1, value2, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_share not between", value1, value2, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountIsNull() {
            addCriterion("confirmed_sub_amount is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountIsNotNull() {
            addCriterion("confirmed_sub_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount =", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount <>", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountGreaterThan(BigDecimal value) {
            addCriterion("confirmed_sub_amount >", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount >=", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountLessThan(BigDecimal value) {
            addCriterion("confirmed_sub_amount <", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount <=", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_amount in", values, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_amount not in", values, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_amount between", value1, value2, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_amount not between", value1, value2, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareIsNull() {
            addCriterion("confirmed_sub_share is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareIsNotNull() {
            addCriterion("confirmed_sub_share is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share =", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share <>", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareGreaterThan(BigDecimal value) {
            addCriterion("confirmed_sub_share >", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share >=", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareLessThan(BigDecimal value) {
            addCriterion("confirmed_sub_share <", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share <=", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_share in", values, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_share not in", values, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_share between", value1, value2, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_share not between", value1, value2, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountIsNull() {
            addCriterion("unconfirmed_sub_amount is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountIsNotNull() {
            addCriterion("unconfirmed_sub_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount =", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount <>", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount >", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount >=", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountLessThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount <", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount <=", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_amount in", values, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_amount not in", values, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_amount between", value1, value2, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_amount not between", value1, value2, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareIsNull() {
            addCriterion("unconfirmed_sub_share is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareIsNotNull() {
            addCriterion("unconfirmed_sub_share is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share =", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share <>", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_share >", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share >=", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareLessThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_share <", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share <=", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_share in", values, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_share not in", values, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_share between", value1, value2, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_share not between", value1, value2, "unconfirmedSubShare");
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

        public Criteria andExpectBonusAmountIsNull() {
            addCriterion("expect_bonus_amount is null");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountIsNotNull() {
            addCriterion("expect_bonus_amount is not null");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountEqualTo(BigDecimal value) {
            addCriterion("expect_bonus_amount =", value, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountNotEqualTo(BigDecimal value) {
            addCriterion("expect_bonus_amount <>", value, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountGreaterThan(BigDecimal value) {
            addCriterion("expect_bonus_amount >", value, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("expect_bonus_amount >=", value, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountLessThan(BigDecimal value) {
            addCriterion("expect_bonus_amount <", value, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("expect_bonus_amount <=", value, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountIn(List<BigDecimal> values) {
            addCriterion("expect_bonus_amount in", values, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountNotIn(List<BigDecimal> values) {
            addCriterion("expect_bonus_amount not in", values, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("expect_bonus_amount between", value1, value2, "expectBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExpectBonusAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("expect_bonus_amount not between", value1, value2, "expectBonusAmount");
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

        public Criteria andExpectProfitAmountIsNull() {
            addCriterion("expect_profit_amount is null");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountIsNotNull() {
            addCriterion("expect_profit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountEqualTo(BigDecimal value) {
            addCriterion("expect_profit_amount =", value, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountNotEqualTo(BigDecimal value) {
            addCriterion("expect_profit_amount <>", value, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountGreaterThan(BigDecimal value) {
            addCriterion("expect_profit_amount >", value, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("expect_profit_amount >=", value, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountLessThan(BigDecimal value) {
            addCriterion("expect_profit_amount <", value, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("expect_profit_amount <=", value, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountIn(List<BigDecimal> values) {
            addCriterion("expect_profit_amount in", values, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountNotIn(List<BigDecimal> values) {
            addCriterion("expect_profit_amount not in", values, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("expect_profit_amount between", value1, value2, "expectProfitAmount");
            return (Criteria) this;
        }

        public Criteria andExpectProfitAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("expect_profit_amount not between", value1, value2, "expectProfitAmount");
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

        public Criteria andGrouponBonusIsNull() {
            addCriterion("groupon_bonus is null");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusIsNotNull() {
            addCriterion("groupon_bonus is not null");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusEqualTo(BigDecimal value) {
            addCriterion("groupon_bonus =", value, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusNotEqualTo(BigDecimal value) {
            addCriterion("groupon_bonus <>", value, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusGreaterThan(BigDecimal value) {
            addCriterion("groupon_bonus >", value, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("groupon_bonus >=", value, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusLessThan(BigDecimal value) {
            addCriterion("groupon_bonus <", value, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("groupon_bonus <=", value, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusIn(List<BigDecimal> values) {
            addCriterion("groupon_bonus in", values, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusNotIn(List<BigDecimal> values) {
            addCriterion("groupon_bonus not in", values, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("groupon_bonus between", value1, value2, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponBonusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("groupon_bonus not between", value1, value2, "grouponBonus");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeIsNull() {
            addCriterion("groupon_type is null");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeIsNotNull() {
            addCriterion("groupon_type is not null");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeEqualTo(Byte value) {
            addCriterion("groupon_type =", value, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeNotEqualTo(Byte value) {
            addCriterion("groupon_type <>", value, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeGreaterThan(Byte value) {
            addCriterion("groupon_type >", value, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("groupon_type >=", value, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeLessThan(Byte value) {
            addCriterion("groupon_type <", value, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeLessThanOrEqualTo(Byte value) {
            addCriterion("groupon_type <=", value, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeIn(List<Byte> values) {
            addCriterion("groupon_type in", values, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeNotIn(List<Byte> values) {
            addCriterion("groupon_type not in", values, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeBetween(Byte value1, Byte value2) {
            addCriterion("groupon_type between", value1, value2, "grouponType");
            return (Criteria) this;
        }

        public Criteria andGrouponTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("groupon_type not between", value1, value2, "grouponType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeIsNull() {
            addCriterion("product_close_type is null");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeIsNotNull() {
            addCriterion("product_close_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeEqualTo(Integer value) {
            addCriterion("product_close_type =", value, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeNotEqualTo(Integer value) {
            addCriterion("product_close_type <>", value, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeGreaterThan(Integer value) {
            addCriterion("product_close_type >", value, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_close_type >=", value, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeLessThan(Integer value) {
            addCriterion("product_close_type <", value, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("product_close_type <=", value, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeIn(List<Integer> values) {
            addCriterion("product_close_type in", values, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeNotIn(List<Integer> values) {
            addCriterion("product_close_type not in", values, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeBetween(Integer value1, Integer value2) {
            addCriterion("product_close_type between", value1, value2, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andProductCloseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("product_close_type not between", value1, value2, "productCloseType");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountIsNull() {
            addCriterion("half_open_bonus_amount is null");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountIsNotNull() {
            addCriterion("half_open_bonus_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountEqualTo(BigDecimal value) {
            addCriterion("half_open_bonus_amount =", value, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountNotEqualTo(BigDecimal value) {
            addCriterion("half_open_bonus_amount <>", value, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountGreaterThan(BigDecimal value) {
            addCriterion("half_open_bonus_amount >", value, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("half_open_bonus_amount >=", value, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountLessThan(BigDecimal value) {
            addCriterion("half_open_bonus_amount <", value, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("half_open_bonus_amount <=", value, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountIn(List<BigDecimal> values) {
            addCriterion("half_open_bonus_amount in", values, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountNotIn(List<BigDecimal> values) {
            addCriterion("half_open_bonus_amount not in", values, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("half_open_bonus_amount between", value1, value2, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andHalfOpenBonusAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("half_open_bonus_amount not between", value1, value2, "halfOpenBonusAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusIsNull() {
            addCriterion("exchange_status is null");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusIsNotNull() {
            addCriterion("exchange_status is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusEqualTo(Byte value) {
            addCriterion("exchange_status =", value, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusNotEqualTo(Byte value) {
            addCriterion("exchange_status <>", value, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusGreaterThan(Byte value) {
            addCriterion("exchange_status >", value, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("exchange_status >=", value, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusLessThan(Byte value) {
            addCriterion("exchange_status <", value, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusLessThanOrEqualTo(Byte value) {
            addCriterion("exchange_status <=", value, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusIn(List<Byte> values) {
            addCriterion("exchange_status in", values, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusNotIn(List<Byte> values) {
            addCriterion("exchange_status not in", values, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusBetween(Byte value1, Byte value2) {
            addCriterion("exchange_status between", value1, value2, "exchangeStatus");
            return (Criteria) this;
        }

        public Criteria andExchangeStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("exchange_status not between", value1, value2, "exchangeStatus");
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