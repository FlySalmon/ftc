package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundBonusDetailAlterationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundBonusDetailAlterationExample() {
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

        public Criteria andBonusAlterationUuidIsNull() {
            addCriterion("bonus_alteration_uuid is null");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidIsNotNull() {
            addCriterion("bonus_alteration_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidEqualTo(String value) {
            addCriterion("bonus_alteration_uuid =", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidNotEqualTo(String value) {
            addCriterion("bonus_alteration_uuid <>", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidGreaterThan(String value) {
            addCriterion("bonus_alteration_uuid >", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidGreaterThanOrEqualTo(String value) {
            addCriterion("bonus_alteration_uuid >=", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidLessThan(String value) {
            addCriterion("bonus_alteration_uuid <", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidLessThanOrEqualTo(String value) {
            addCriterion("bonus_alteration_uuid <=", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidLike(String value) {
            addCriterion("bonus_alteration_uuid like", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidNotLike(String value) {
            addCriterion("bonus_alteration_uuid not like", value, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidIn(List<String> values) {
            addCriterion("bonus_alteration_uuid in", values, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidNotIn(List<String> values) {
            addCriterion("bonus_alteration_uuid not in", values, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidBetween(String value1, String value2) {
            addCriterion("bonus_alteration_uuid between", value1, value2, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andBonusAlterationUuidNotBetween(String value1, String value2) {
            addCriterion("bonus_alteration_uuid not between", value1, value2, "bonusAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdIsNull() {
            addCriterion("fund_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdIsNotNull() {
            addCriterion("fund_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdEqualTo(Long value) {
            addCriterion("fund_detail_id =", value, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdNotEqualTo(Long value) {
            addCriterion("fund_detail_id <>", value, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdGreaterThan(Long value) {
            addCriterion("fund_detail_id >", value, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fund_detail_id >=", value, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdLessThan(Long value) {
            addCriterion("fund_detail_id <", value, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("fund_detail_id <=", value, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdIn(List<Long> values) {
            addCriterion("fund_detail_id in", values, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdNotIn(List<Long> values) {
            addCriterion("fund_detail_id not in", values, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdBetween(Long value1, Long value2) {
            addCriterion("fund_detail_id between", value1, value2, "fundDetailId");
            return (Criteria) this;
        }

        public Criteria andFundDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("fund_detail_id not between", value1, value2, "fundDetailId");
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

        public Criteria andFtcOrderNoIsNull() {
            addCriterion("ftc_order_no is null");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoIsNotNull() {
            addCriterion("ftc_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoEqualTo(String value) {
            addCriterion("ftc_order_no =", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoNotEqualTo(String value) {
            addCriterion("ftc_order_no <>", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoGreaterThan(String value) {
            addCriterion("ftc_order_no >", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ftc_order_no >=", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoLessThan(String value) {
            addCriterion("ftc_order_no <", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ftc_order_no <=", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoLike(String value) {
            addCriterion("ftc_order_no like", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoNotLike(String value) {
            addCriterion("ftc_order_no not like", value, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoIn(List<String> values) {
            addCriterion("ftc_order_no in", values, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoNotIn(List<String> values) {
            addCriterion("ftc_order_no not in", values, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoBetween(String value1, String value2) {
            addCriterion("ftc_order_no between", value1, value2, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFtcOrderNoNotBetween(String value1, String value2) {
            addCriterion("ftc_order_no not between", value1, value2, "ftcOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountIsNull() {
            addCriterion("fund_bonus_amount is null");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountIsNotNull() {
            addCriterion("fund_bonus_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountEqualTo(BigDecimal value) {
            addCriterion("fund_bonus_amount =", value, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountNotEqualTo(BigDecimal value) {
            addCriterion("fund_bonus_amount <>", value, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountGreaterThan(BigDecimal value) {
            addCriterion("fund_bonus_amount >", value, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_bonus_amount >=", value, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountLessThan(BigDecimal value) {
            addCriterion("fund_bonus_amount <", value, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_bonus_amount <=", value, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountIn(List<BigDecimal> values) {
            addCriterion("fund_bonus_amount in", values, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountNotIn(List<BigDecimal> values) {
            addCriterion("fund_bonus_amount not in", values, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_bonus_amount between", value1, value2, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andFundBonusAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_bonus_amount not between", value1, value2, "fundBonusAmount");
            return (Criteria) this;
        }

        public Criteria andBonusTimeIsNull() {
            addCriterion("bonus_time is null");
            return (Criteria) this;
        }

        public Criteria andBonusTimeIsNotNull() {
            addCriterion("bonus_time is not null");
            return (Criteria) this;
        }

        public Criteria andBonusTimeEqualTo(Date value) {
            addCriterion("bonus_time =", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeNotEqualTo(Date value) {
            addCriterion("bonus_time <>", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeGreaterThan(Date value) {
            addCriterion("bonus_time >", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bonus_time >=", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeLessThan(Date value) {
            addCriterion("bonus_time <", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeLessThanOrEqualTo(Date value) {
            addCriterion("bonus_time <=", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeIn(List<Date> values) {
            addCriterion("bonus_time in", values, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeNotIn(List<Date> values) {
            addCriterion("bonus_time not in", values, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeBetween(Date value1, Date value2) {
            addCriterion("bonus_time between", value1, value2, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeNotBetween(Date value1, Date value2) {
            addCriterion("bonus_time not between", value1, value2, "bonusTime");
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

        public Criteria andBonusTypeIsNull() {
            addCriterion("bonus_type is null");
            return (Criteria) this;
        }

        public Criteria andBonusTypeIsNotNull() {
            addCriterion("bonus_type is not null");
            return (Criteria) this;
        }

        public Criteria andBonusTypeEqualTo(Integer value) {
            addCriterion("bonus_type =", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeNotEqualTo(Integer value) {
            addCriterion("bonus_type <>", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeGreaterThan(Integer value) {
            addCriterion("bonus_type >", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_type >=", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeLessThan(Integer value) {
            addCriterion("bonus_type <", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_type <=", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeIn(List<Integer> values) {
            addCriterion("bonus_type in", values, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeNotIn(List<Integer> values) {
            addCriterion("bonus_type not in", values, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeBetween(Integer value1, Integer value2) {
            addCriterion("bonus_type between", value1, value2, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_type not between", value1, value2, "bonusType");
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