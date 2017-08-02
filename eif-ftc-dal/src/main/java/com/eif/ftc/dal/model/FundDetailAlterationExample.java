package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundDetailAlterationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundDetailAlterationExample() {
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

        public Criteria andDetailAlterationUuidIsNull() {
            addCriterion("detail_alteration_uuid is null");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidIsNotNull() {
            addCriterion("detail_alteration_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidEqualTo(String value) {
            addCriterion("detail_alteration_uuid =", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidNotEqualTo(String value) {
            addCriterion("detail_alteration_uuid <>", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidGreaterThan(String value) {
            addCriterion("detail_alteration_uuid >", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidGreaterThanOrEqualTo(String value) {
            addCriterion("detail_alteration_uuid >=", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidLessThan(String value) {
            addCriterion("detail_alteration_uuid <", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidLessThanOrEqualTo(String value) {
            addCriterion("detail_alteration_uuid <=", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidLike(String value) {
            addCriterion("detail_alteration_uuid like", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidNotLike(String value) {
            addCriterion("detail_alteration_uuid not like", value, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidIn(List<String> values) {
            addCriterion("detail_alteration_uuid in", values, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidNotIn(List<String> values) {
            addCriterion("detail_alteration_uuid not in", values, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidBetween(String value1, String value2) {
            addCriterion("detail_alteration_uuid between", value1, value2, "detailAlterationUuid");
            return (Criteria) this;
        }

        public Criteria andDetailAlterationUuidNotBetween(String value1, String value2) {
            addCriterion("detail_alteration_uuid not between", value1, value2, "detailAlterationUuid");
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

        public Criteria andFundShareIsNull() {
            addCriterion("fund_share is null");
            return (Criteria) this;
        }

        public Criteria andFundShareIsNotNull() {
            addCriterion("fund_share is not null");
            return (Criteria) this;
        }

        public Criteria andFundShareEqualTo(BigDecimal value) {
            addCriterion("fund_share =", value, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareNotEqualTo(BigDecimal value) {
            addCriterion("fund_share <>", value, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareGreaterThan(BigDecimal value) {
            addCriterion("fund_share >", value, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_share >=", value, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareLessThan(BigDecimal value) {
            addCriterion("fund_share <", value, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_share <=", value, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareIn(List<BigDecimal> values) {
            addCriterion("fund_share in", values, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareNotIn(List<BigDecimal> values) {
            addCriterion("fund_share not in", values, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_share between", value1, value2, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_share not between", value1, value2, "fundShare");
            return (Criteria) this;
        }

        public Criteria andFundAmountIsNull() {
            addCriterion("fund_amount is null");
            return (Criteria) this;
        }

        public Criteria andFundAmountIsNotNull() {
            addCriterion("fund_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFundAmountEqualTo(BigDecimal value) {
            addCriterion("fund_amount =", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountNotEqualTo(BigDecimal value) {
            addCriterion("fund_amount <>", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountGreaterThan(BigDecimal value) {
            addCriterion("fund_amount >", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_amount >=", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountLessThan(BigDecimal value) {
            addCriterion("fund_amount <", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_amount <=", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountIn(List<BigDecimal> values) {
            addCriterion("fund_amount in", values, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountNotIn(List<BigDecimal> values) {
            addCriterion("fund_amount not in", values, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_amount between", value1, value2, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_amount not between", value1, value2, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusIsNull() {
            addCriterion("alteration_status is null");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusIsNotNull() {
            addCriterion("alteration_status is not null");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusEqualTo(Integer value) {
            addCriterion("alteration_status =", value, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusNotEqualTo(Integer value) {
            addCriterion("alteration_status <>", value, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusGreaterThan(Integer value) {
            addCriterion("alteration_status >", value, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("alteration_status >=", value, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusLessThan(Integer value) {
            addCriterion("alteration_status <", value, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusLessThanOrEqualTo(Integer value) {
            addCriterion("alteration_status <=", value, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusIn(List<Integer> values) {
            addCriterion("alteration_status in", values, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusNotIn(List<Integer> values) {
            addCriterion("alteration_status not in", values, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusBetween(Integer value1, Integer value2) {
            addCriterion("alteration_status between", value1, value2, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("alteration_status not between", value1, value2, "alterationStatus");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeIsNull() {
            addCriterion("alteration_type is null");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeIsNotNull() {
            addCriterion("alteration_type is not null");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeEqualTo(Integer value) {
            addCriterion("alteration_type =", value, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeNotEqualTo(Integer value) {
            addCriterion("alteration_type <>", value, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeGreaterThan(Integer value) {
            addCriterion("alteration_type >", value, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("alteration_type >=", value, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeLessThan(Integer value) {
            addCriterion("alteration_type <", value, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("alteration_type <=", value, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeIn(List<Integer> values) {
            addCriterion("alteration_type in", values, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeNotIn(List<Integer> values) {
            addCriterion("alteration_type not in", values, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeBetween(Integer value1, Integer value2) {
            addCriterion("alteration_type between", value1, value2, "alterationType");
            return (Criteria) this;
        }

        public Criteria andAlterationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("alteration_type not between", value1, value2, "alterationType");
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

        public Criteria andFtcOrderCreateTimeIsNull() {
            addCriterion("ftc_order_create_time is null");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeIsNotNull() {
            addCriterion("ftc_order_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeEqualTo(Date value) {
            addCriterion("ftc_order_create_time =", value, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeNotEqualTo(Date value) {
            addCriterion("ftc_order_create_time <>", value, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeGreaterThan(Date value) {
            addCriterion("ftc_order_create_time >", value, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ftc_order_create_time >=", value, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeLessThan(Date value) {
            addCriterion("ftc_order_create_time <", value, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("ftc_order_create_time <=", value, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeIn(List<Date> values) {
            addCriterion("ftc_order_create_time in", values, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeNotIn(List<Date> values) {
            addCriterion("ftc_order_create_time not in", values, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeBetween(Date value1, Date value2) {
            addCriterion("ftc_order_create_time between", value1, value2, "ftcOrderCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcOrderCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("ftc_order_create_time not between", value1, value2, "ftcOrderCreateTime");
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