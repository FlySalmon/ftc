package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundTransOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundTransOrderExample() {
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

        public Criteria andFundTransOrderNoIsNull() {
            addCriterion("fund_trans_order_no is null");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoIsNotNull() {
            addCriterion("fund_trans_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoEqualTo(String value) {
            addCriterion("fund_trans_order_no =", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoNotEqualTo(String value) {
            addCriterion("fund_trans_order_no <>", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoGreaterThan(String value) {
            addCriterion("fund_trans_order_no >", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("fund_trans_order_no >=", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoLessThan(String value) {
            addCriterion("fund_trans_order_no <", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoLessThanOrEqualTo(String value) {
            addCriterion("fund_trans_order_no <=", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoLike(String value) {
            addCriterion("fund_trans_order_no like", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoNotLike(String value) {
            addCriterion("fund_trans_order_no not like", value, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoIn(List<String> values) {
            addCriterion("fund_trans_order_no in", values, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoNotIn(List<String> values) {
            addCriterion("fund_trans_order_no not in", values, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoBetween(String value1, String value2) {
            addCriterion("fund_trans_order_no between", value1, value2, "fundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransOrderNoNotBetween(String value1, String value2) {
            addCriterion("fund_trans_order_no not between", value1, value2, "fundTransOrderNo");
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

        public Criteria andAssetAccountNoIsNull() {
            addCriterion("asset_account_no is null");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoIsNotNull() {
            addCriterion("asset_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoEqualTo(String value) {
            addCriterion("asset_account_no =", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoNotEqualTo(String value) {
            addCriterion("asset_account_no <>", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoGreaterThan(String value) {
            addCriterion("asset_account_no >", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("asset_account_no >=", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoLessThan(String value) {
            addCriterion("asset_account_no <", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoLessThanOrEqualTo(String value) {
            addCriterion("asset_account_no <=", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoLike(String value) {
            addCriterion("asset_account_no like", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoNotLike(String value) {
            addCriterion("asset_account_no not like", value, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoIn(List<String> values) {
            addCriterion("asset_account_no in", values, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoNotIn(List<String> values) {
            addCriterion("asset_account_no not in", values, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoBetween(String value1, String value2) {
            addCriterion("asset_account_no between", value1, value2, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andAssetAccountNoNotBetween(String value1, String value2) {
            addCriterion("asset_account_no not between", value1, value2, "assetAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoIsNull() {
            addCriterion("ref_fund_account_no is null");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoIsNotNull() {
            addCriterion("ref_fund_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoEqualTo(String value) {
            addCriterion("ref_fund_account_no =", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoNotEqualTo(String value) {
            addCriterion("ref_fund_account_no <>", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoGreaterThan(String value) {
            addCriterion("ref_fund_account_no >", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_fund_account_no >=", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoLessThan(String value) {
            addCriterion("ref_fund_account_no <", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoLessThanOrEqualTo(String value) {
            addCriterion("ref_fund_account_no <=", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoLike(String value) {
            addCriterion("ref_fund_account_no like", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoNotLike(String value) {
            addCriterion("ref_fund_account_no not like", value, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoIn(List<String> values) {
            addCriterion("ref_fund_account_no in", values, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoNotIn(List<String> values) {
            addCriterion("ref_fund_account_no not in", values, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoBetween(String value1, String value2) {
            addCriterion("ref_fund_account_no between", value1, value2, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andRefFundAccountNoNotBetween(String value1, String value2) {
            addCriterion("ref_fund_account_no not between", value1, value2, "refFundAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoIsNull() {
            addCriterion("trans_account_no is null");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoIsNotNull() {
            addCriterion("trans_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoEqualTo(String value) {
            addCriterion("trans_account_no =", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoNotEqualTo(String value) {
            addCriterion("trans_account_no <>", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoGreaterThan(String value) {
            addCriterion("trans_account_no >", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("trans_account_no >=", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoLessThan(String value) {
            addCriterion("trans_account_no <", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoLessThanOrEqualTo(String value) {
            addCriterion("trans_account_no <=", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoLike(String value) {
            addCriterion("trans_account_no like", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoNotLike(String value) {
            addCriterion("trans_account_no not like", value, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoIn(List<String> values) {
            addCriterion("trans_account_no in", values, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoNotIn(List<String> values) {
            addCriterion("trans_account_no not in", values, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoBetween(String value1, String value2) {
            addCriterion("trans_account_no between", value1, value2, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andTransAccountNoNotBetween(String value1, String value2) {
            addCriterion("trans_account_no not between", value1, value2, "transAccountNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoIsNull() {
            addCriterion("parent_fund_trans_order_no is null");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoIsNotNull() {
            addCriterion("parent_fund_trans_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoEqualTo(String value) {
            addCriterion("parent_fund_trans_order_no =", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoNotEqualTo(String value) {
            addCriterion("parent_fund_trans_order_no <>", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoGreaterThan(String value) {
            addCriterion("parent_fund_trans_order_no >", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("parent_fund_trans_order_no >=", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoLessThan(String value) {
            addCriterion("parent_fund_trans_order_no <", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoLessThanOrEqualTo(String value) {
            addCriterion("parent_fund_trans_order_no <=", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoLike(String value) {
            addCriterion("parent_fund_trans_order_no like", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoNotLike(String value) {
            addCriterion("parent_fund_trans_order_no not like", value, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoIn(List<String> values) {
            addCriterion("parent_fund_trans_order_no in", values, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoNotIn(List<String> values) {
            addCriterion("parent_fund_trans_order_no not in", values, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoBetween(String value1, String value2) {
            addCriterion("parent_fund_trans_order_no between", value1, value2, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andParentFundTransOrderNoNotBetween(String value1, String value2) {
            addCriterion("parent_fund_trans_order_no not between", value1, value2, "parentFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoIsNull() {
            addCriterion("ref_fund_trans_order_no is null");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoIsNotNull() {
            addCriterion("ref_fund_trans_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoEqualTo(String value) {
            addCriterion("ref_fund_trans_order_no =", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoNotEqualTo(String value) {
            addCriterion("ref_fund_trans_order_no <>", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoGreaterThan(String value) {
            addCriterion("ref_fund_trans_order_no >", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_fund_trans_order_no >=", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoLessThan(String value) {
            addCriterion("ref_fund_trans_order_no <", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ref_fund_trans_order_no <=", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoLike(String value) {
            addCriterion("ref_fund_trans_order_no like", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoNotLike(String value) {
            addCriterion("ref_fund_trans_order_no not like", value, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoIn(List<String> values) {
            addCriterion("ref_fund_trans_order_no in", values, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoNotIn(List<String> values) {
            addCriterion("ref_fund_trans_order_no not in", values, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoBetween(String value1, String value2) {
            addCriterion("ref_fund_trans_order_no between", value1, value2, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransOrderNoNotBetween(String value1, String value2) {
            addCriterion("ref_fund_trans_order_no not between", value1, value2, "refFundTransOrderNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoIsNull() {
            addCriterion("transcore_trans_no is null");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoIsNotNull() {
            addCriterion("transcore_trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoEqualTo(String value) {
            addCriterion("transcore_trans_no =", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoNotEqualTo(String value) {
            addCriterion("transcore_trans_no <>", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoGreaterThan(String value) {
            addCriterion("transcore_trans_no >", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("transcore_trans_no >=", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoLessThan(String value) {
            addCriterion("transcore_trans_no <", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoLessThanOrEqualTo(String value) {
            addCriterion("transcore_trans_no <=", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoLike(String value) {
            addCriterion("transcore_trans_no like", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoNotLike(String value) {
            addCriterion("transcore_trans_no not like", value, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoIn(List<String> values) {
            addCriterion("transcore_trans_no in", values, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoNotIn(List<String> values) {
            addCriterion("transcore_trans_no not in", values, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoBetween(String value1, String value2) {
            addCriterion("transcore_trans_no between", value1, value2, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andTranscoreTransNoNotBetween(String value1, String value2) {
            addCriterion("transcore_trans_no not between", value1, value2, "transcoreTransNo");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedIsNull() {
            addCriterion("is_adjusted is null");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedIsNotNull() {
            addCriterion("is_adjusted is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedEqualTo(Boolean value) {
            addCriterion("is_adjusted =", value, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedNotEqualTo(Boolean value) {
            addCriterion("is_adjusted <>", value, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedGreaterThan(Boolean value) {
            addCriterion("is_adjusted >", value, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_adjusted >=", value, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedLessThan(Boolean value) {
            addCriterion("is_adjusted <", value, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_adjusted <=", value, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedIn(List<Boolean> values) {
            addCriterion("is_adjusted in", values, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedNotIn(List<Boolean> values) {
            addCriterion("is_adjusted not in", values, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_adjusted between", value1, value2, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andIsAdjustedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_adjusted not between", value1, value2, "isAdjusted");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonIsNull() {
            addCriterion("trigger_reason is null");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonIsNotNull() {
            addCriterion("trigger_reason is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonEqualTo(Integer value) {
            addCriterion("trigger_reason =", value, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonNotEqualTo(Integer value) {
            addCriterion("trigger_reason <>", value, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonGreaterThan(Integer value) {
            addCriterion("trigger_reason >", value, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("trigger_reason >=", value, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonLessThan(Integer value) {
            addCriterion("trigger_reason <", value, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonLessThanOrEqualTo(Integer value) {
            addCriterion("trigger_reason <=", value, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonIn(List<Integer> values) {
            addCriterion("trigger_reason in", values, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonNotIn(List<Integer> values) {
            addCriterion("trigger_reason not in", values, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonBetween(Integer value1, Integer value2) {
            addCriterion("trigger_reason between", value1, value2, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andTriggerReasonNotBetween(Integer value1, Integer value2) {
            addCriterion("trigger_reason not between", value1, value2, "triggerReason");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoIsNull() {
            addCriterion("business_order_item_no is null");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoIsNotNull() {
            addCriterion("business_order_item_no is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoEqualTo(String value) {
            addCriterion("business_order_item_no =", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoNotEqualTo(String value) {
            addCriterion("business_order_item_no <>", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoGreaterThan(String value) {
            addCriterion("business_order_item_no >", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoGreaterThanOrEqualTo(String value) {
            addCriterion("business_order_item_no >=", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoLessThan(String value) {
            addCriterion("business_order_item_no <", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoLessThanOrEqualTo(String value) {
            addCriterion("business_order_item_no <=", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoLike(String value) {
            addCriterion("business_order_item_no like", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoNotLike(String value) {
            addCriterion("business_order_item_no not like", value, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoIn(List<String> values) {
            addCriterion("business_order_item_no in", values, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoNotIn(List<String> values) {
            addCriterion("business_order_item_no not in", values, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoBetween(String value1, String value2) {
            addCriterion("business_order_item_no between", value1, value2, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andBusinessOrderItemNoNotBetween(String value1, String value2) {
            addCriterion("business_order_item_no not between", value1, value2, "businessOrderItemNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoIsNull() {
            addCriterion("tracking_no is null");
            return (Criteria) this;
        }

        public Criteria andTrackingNoIsNotNull() {
            addCriterion("tracking_no is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingNoEqualTo(String value) {
            addCriterion("tracking_no =", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotEqualTo(String value) {
            addCriterion("tracking_no <>", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoGreaterThan(String value) {
            addCriterion("tracking_no >", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoGreaterThanOrEqualTo(String value) {
            addCriterion("tracking_no >=", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoLessThan(String value) {
            addCriterion("tracking_no <", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoLessThanOrEqualTo(String value) {
            addCriterion("tracking_no <=", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoLike(String value) {
            addCriterion("tracking_no like", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotLike(String value) {
            addCriterion("tracking_no not like", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoIn(List<String> values) {
            addCriterion("tracking_no in", values, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotIn(List<String> values) {
            addCriterion("tracking_no not in", values, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoBetween(String value1, String value2) {
            addCriterion("tracking_no between", value1, value2, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotBetween(String value1, String value2) {
            addCriterion("tracking_no not between", value1, value2, "trackingNo");
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

        public Criteria andMerMemberNoIsNull() {
            addCriterion("mer_member_no is null");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoIsNotNull() {
            addCriterion("mer_member_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoEqualTo(String value) {
            addCriterion("mer_member_no =", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoNotEqualTo(String value) {
            addCriterion("mer_member_no <>", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoGreaterThan(String value) {
            addCriterion("mer_member_no >", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoGreaterThanOrEqualTo(String value) {
            addCriterion("mer_member_no >=", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoLessThan(String value) {
            addCriterion("mer_member_no <", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoLessThanOrEqualTo(String value) {
            addCriterion("mer_member_no <=", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoLike(String value) {
            addCriterion("mer_member_no like", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoNotLike(String value) {
            addCriterion("mer_member_no not like", value, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoIn(List<String> values) {
            addCriterion("mer_member_no in", values, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoNotIn(List<String> values) {
            addCriterion("mer_member_no not in", values, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoBetween(String value1, String value2) {
            addCriterion("mer_member_no between", value1, value2, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andMerMemberNoNotBetween(String value1, String value2) {
            addCriterion("mer_member_no not between", value1, value2, "merMemberNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNull() {
            addCriterion("contract_no is null");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNotNull() {
            addCriterion("contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andContractNoEqualTo(String value) {
            addCriterion("contract_no =", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotEqualTo(String value) {
            addCriterion("contract_no <>", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThan(String value) {
            addCriterion("contract_no >", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("contract_no >=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThan(String value) {
            addCriterion("contract_no <", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThanOrEqualTo(String value) {
            addCriterion("contract_no <=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLike(String value) {
            addCriterion("contract_no like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotLike(String value) {
            addCriterion("contract_no not like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(List<String> values) {
            addCriterion("contract_no in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(List<String> values) {
            addCriterion("contract_no not in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoBetween(String value1, String value2) {
            addCriterion("contract_no between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotBetween(String value1, String value2) {
            addCriterion("contract_no not between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeIsNull() {
            addCriterion("fund_trans_type is null");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeIsNotNull() {
            addCriterion("fund_trans_type is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeEqualTo(Integer value) {
            addCriterion("fund_trans_type =", value, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeNotEqualTo(Integer value) {
            addCriterion("fund_trans_type <>", value, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeGreaterThan(Integer value) {
            addCriterion("fund_trans_type >", value, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fund_trans_type >=", value, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeLessThan(Integer value) {
            addCriterion("fund_trans_type <", value, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fund_trans_type <=", value, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeIn(List<Integer> values) {
            addCriterion("fund_trans_type in", values, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeNotIn(List<Integer> values) {
            addCriterion("fund_trans_type not in", values, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeBetween(Integer value1, Integer value2) {
            addCriterion("fund_trans_type between", value1, value2, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fund_trans_type not between", value1, value2, "fundTransType");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountIsNull() {
            addCriterion("fund_trans_amount is null");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountIsNotNull() {
            addCriterion("fund_trans_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountEqualTo(BigDecimal value) {
            addCriterion("fund_trans_amount =", value, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountNotEqualTo(BigDecimal value) {
            addCriterion("fund_trans_amount <>", value, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountGreaterThan(BigDecimal value) {
            addCriterion("fund_trans_amount >", value, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_trans_amount >=", value, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountLessThan(BigDecimal value) {
            addCriterion("fund_trans_amount <", value, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_trans_amount <=", value, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountIn(List<BigDecimal> values) {
            addCriterion("fund_trans_amount in", values, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountNotIn(List<BigDecimal> values) {
            addCriterion("fund_trans_amount not in", values, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_trans_amount between", value1, value2, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_trans_amount not between", value1, value2, "fundTransAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesIsNull() {
            addCriterion("fund_trans_shares is null");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesIsNotNull() {
            addCriterion("fund_trans_shares is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesEqualTo(BigDecimal value) {
            addCriterion("fund_trans_shares =", value, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesNotEqualTo(BigDecimal value) {
            addCriterion("fund_trans_shares <>", value, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesGreaterThan(BigDecimal value) {
            addCriterion("fund_trans_shares >", value, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_trans_shares >=", value, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesLessThan(BigDecimal value) {
            addCriterion("fund_trans_shares <", value, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_trans_shares <=", value, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesIn(List<BigDecimal> values) {
            addCriterion("fund_trans_shares in", values, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesNotIn(List<BigDecimal> values) {
            addCriterion("fund_trans_shares not in", values, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_trans_shares between", value1, value2, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andFundTransSharesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_trans_shares not between", value1, value2, "fundTransShares");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIsNull() {
            addCriterion("discount_amount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIsNotNull() {
            addCriterion("discount_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountEqualTo(BigDecimal value) {
            addCriterion("discount_amount =", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotEqualTo(BigDecimal value) {
            addCriterion("discount_amount <>", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountGreaterThan(BigDecimal value) {
            addCriterion("discount_amount >", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_amount >=", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountLessThan(BigDecimal value) {
            addCriterion("discount_amount <", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_amount <=", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIn(List<BigDecimal> values) {
            addCriterion("discount_amount in", values, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotIn(List<BigDecimal> values) {
            addCriterion("discount_amount not in", values, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_amount between", value1, value2, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_amount not between", value1, value2, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIsNull() {
            addCriterion("currency_type is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIsNotNull() {
            addCriterion("currency_type is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeEqualTo(String value) {
            addCriterion("currency_type =", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotEqualTo(String value) {
            addCriterion("currency_type <>", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeGreaterThan(String value) {
            addCriterion("currency_type >", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("currency_type >=", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLessThan(String value) {
            addCriterion("currency_type <", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLessThanOrEqualTo(String value) {
            addCriterion("currency_type <=", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLike(String value) {
            addCriterion("currency_type like", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotLike(String value) {
            addCriterion("currency_type not like", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIn(List<String> values) {
            addCriterion("currency_type in", values, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotIn(List<String> values) {
            addCriterion("currency_type not in", values, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeBetween(String value1, String value2) {
            addCriterion("currency_type between", value1, value2, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotBetween(String value1, String value2) {
            addCriterion("currency_type not between", value1, value2, "currencyType");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeIsNull() {
            addCriterion("ref_fund_code is null");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeIsNotNull() {
            addCriterion("ref_fund_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeEqualTo(String value) {
            addCriterion("ref_fund_code =", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeNotEqualTo(String value) {
            addCriterion("ref_fund_code <>", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeGreaterThan(String value) {
            addCriterion("ref_fund_code >", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ref_fund_code >=", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeLessThan(String value) {
            addCriterion("ref_fund_code <", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeLessThanOrEqualTo(String value) {
            addCriterion("ref_fund_code <=", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeLike(String value) {
            addCriterion("ref_fund_code like", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeNotLike(String value) {
            addCriterion("ref_fund_code not like", value, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeIn(List<String> values) {
            addCriterion("ref_fund_code in", values, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeNotIn(List<String> values) {
            addCriterion("ref_fund_code not in", values, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeBetween(String value1, String value2) {
            addCriterion("ref_fund_code between", value1, value2, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundCodeNotBetween(String value1, String value2) {
            addCriterion("ref_fund_code not between", value1, value2, "refFundCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeIsNull() {
            addCriterion("ref_fund_sub_code is null");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeIsNotNull() {
            addCriterion("ref_fund_sub_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeEqualTo(String value) {
            addCriterion("ref_fund_sub_code =", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeNotEqualTo(String value) {
            addCriterion("ref_fund_sub_code <>", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeGreaterThan(String value) {
            addCriterion("ref_fund_sub_code >", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ref_fund_sub_code >=", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeLessThan(String value) {
            addCriterion("ref_fund_sub_code <", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeLessThanOrEqualTo(String value) {
            addCriterion("ref_fund_sub_code <=", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeLike(String value) {
            addCriterion("ref_fund_sub_code like", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeNotLike(String value) {
            addCriterion("ref_fund_sub_code not like", value, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeIn(List<String> values) {
            addCriterion("ref_fund_sub_code in", values, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeNotIn(List<String> values) {
            addCriterion("ref_fund_sub_code not in", values, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeBetween(String value1, String value2) {
            addCriterion("ref_fund_sub_code between", value1, value2, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundSubCodeNotBetween(String value1, String value2) {
            addCriterion("ref_fund_sub_code not between", value1, value2, "refFundSubCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeIsNull() {
            addCriterion("ref_fund_business_code is null");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeIsNotNull() {
            addCriterion("ref_fund_business_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeEqualTo(String value) {
            addCriterion("ref_fund_business_code =", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeNotEqualTo(String value) {
            addCriterion("ref_fund_business_code <>", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeGreaterThan(String value) {
            addCriterion("ref_fund_business_code >", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ref_fund_business_code >=", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeLessThan(String value) {
            addCriterion("ref_fund_business_code <", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeLessThanOrEqualTo(String value) {
            addCriterion("ref_fund_business_code <=", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeLike(String value) {
            addCriterion("ref_fund_business_code like", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeNotLike(String value) {
            addCriterion("ref_fund_business_code not like", value, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeIn(List<String> values) {
            addCriterion("ref_fund_business_code in", values, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeNotIn(List<String> values) {
            addCriterion("ref_fund_business_code not in", values, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeBetween(String value1, String value2) {
            addCriterion("ref_fund_business_code between", value1, value2, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andRefFundBusinessCodeNotBetween(String value1, String value2) {
            addCriterion("ref_fund_business_code not between", value1, value2, "refFundBusinessCode");
            return (Criteria) this;
        }

        public Criteria andBizChannelIsNull() {
            addCriterion("biz_channel is null");
            return (Criteria) this;
        }

        public Criteria andBizChannelIsNotNull() {
            addCriterion("biz_channel is not null");
            return (Criteria) this;
        }

        public Criteria andBizChannelEqualTo(Integer value) {
            addCriterion("biz_channel =", value, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelNotEqualTo(Integer value) {
            addCriterion("biz_channel <>", value, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelGreaterThan(Integer value) {
            addCriterion("biz_channel >", value, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("biz_channel >=", value, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelLessThan(Integer value) {
            addCriterion("biz_channel <", value, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelLessThanOrEqualTo(Integer value) {
            addCriterion("biz_channel <=", value, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelIn(List<Integer> values) {
            addCriterion("biz_channel in", values, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelNotIn(List<Integer> values) {
            addCriterion("biz_channel not in", values, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelBetween(Integer value1, Integer value2) {
            addCriterion("biz_channel between", value1, value2, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andBizChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("biz_channel not between", value1, value2, "bizChannel");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeIsNull() {
            addCriterion("fund_interact_type is null");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeIsNotNull() {
            addCriterion("fund_interact_type is not null");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeEqualTo(Integer value) {
            addCriterion("fund_interact_type =", value, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeNotEqualTo(Integer value) {
            addCriterion("fund_interact_type <>", value, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeGreaterThan(Integer value) {
            addCriterion("fund_interact_type >", value, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fund_interact_type >=", value, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeLessThan(Integer value) {
            addCriterion("fund_interact_type <", value, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fund_interact_type <=", value, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeIn(List<Integer> values) {
            addCriterion("fund_interact_type in", values, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeNotIn(List<Integer> values) {
            addCriterion("fund_interact_type not in", values, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeBetween(Integer value1, Integer value2) {
            addCriterion("fund_interact_type between", value1, value2, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFundInteractTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fund_interact_type not between", value1, value2, "fundInteractType");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeIsNull() {
            addCriterion("frozen_code is null");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeIsNotNull() {
            addCriterion("frozen_code is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeEqualTo(String value) {
            addCriterion("frozen_code =", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeNotEqualTo(String value) {
            addCriterion("frozen_code <>", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeGreaterThan(String value) {
            addCriterion("frozen_code >", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeGreaterThanOrEqualTo(String value) {
            addCriterion("frozen_code >=", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeLessThan(String value) {
            addCriterion("frozen_code <", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeLessThanOrEqualTo(String value) {
            addCriterion("frozen_code <=", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeLike(String value) {
            addCriterion("frozen_code like", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeNotLike(String value) {
            addCriterion("frozen_code not like", value, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeIn(List<String> values) {
            addCriterion("frozen_code in", values, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeNotIn(List<String> values) {
            addCriterion("frozen_code not in", values, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeBetween(String value1, String value2) {
            addCriterion("frozen_code between", value1, value2, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andFrozenCodeNotBetween(String value1, String value2) {
            addCriterion("frozen_code not between", value1, value2, "frozenCode");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeIsNull() {
            addCriterion("total_expiry_time is null");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeIsNotNull() {
            addCriterion("total_expiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeEqualTo(Date value) {
            addCriterion("total_expiry_time =", value, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeNotEqualTo(Date value) {
            addCriterion("total_expiry_time <>", value, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeGreaterThan(Date value) {
            addCriterion("total_expiry_time >", value, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("total_expiry_time >=", value, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeLessThan(Date value) {
            addCriterion("total_expiry_time <", value, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("total_expiry_time <=", value, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeIn(List<Date> values) {
            addCriterion("total_expiry_time in", values, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeNotIn(List<Date> values) {
            addCriterion("total_expiry_time not in", values, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("total_expiry_time between", value1, value2, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andTotalExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("total_expiry_time not between", value1, value2, "totalExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeIsNull() {
            addCriterion("cur_status_expiry_time is null");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeIsNotNull() {
            addCriterion("cur_status_expiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeEqualTo(Date value) {
            addCriterion("cur_status_expiry_time =", value, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeNotEqualTo(Date value) {
            addCriterion("cur_status_expiry_time <>", value, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeGreaterThan(Date value) {
            addCriterion("cur_status_expiry_time >", value, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cur_status_expiry_time >=", value, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeLessThan(Date value) {
            addCriterion("cur_status_expiry_time <", value, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("cur_status_expiry_time <=", value, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeIn(List<Date> values) {
            addCriterion("cur_status_expiry_time in", values, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeNotIn(List<Date> values) {
            addCriterion("cur_status_expiry_time not in", values, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("cur_status_expiry_time between", value1, value2, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andCurStatusExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("cur_status_expiry_time not between", value1, value2, "curStatusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoIsNull() {
            addCriterion("ref_app_sheet_serial_no is null");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoIsNotNull() {
            addCriterion("ref_app_sheet_serial_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoEqualTo(String value) {
            addCriterion("ref_app_sheet_serial_no =", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoNotEqualTo(String value) {
            addCriterion("ref_app_sheet_serial_no <>", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoGreaterThan(String value) {
            addCriterion("ref_app_sheet_serial_no >", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_app_sheet_serial_no >=", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoLessThan(String value) {
            addCriterion("ref_app_sheet_serial_no <", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoLessThanOrEqualTo(String value) {
            addCriterion("ref_app_sheet_serial_no <=", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoLike(String value) {
            addCriterion("ref_app_sheet_serial_no like", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoNotLike(String value) {
            addCriterion("ref_app_sheet_serial_no not like", value, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoIn(List<String> values) {
            addCriterion("ref_app_sheet_serial_no in", values, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoNotIn(List<String> values) {
            addCriterion("ref_app_sheet_serial_no not in", values, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoBetween(String value1, String value2) {
            addCriterion("ref_app_sheet_serial_no between", value1, value2, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andRefAppSheetSerialNoNotBetween(String value1, String value2) {
            addCriterion("ref_app_sheet_serial_no not between", value1, value2, "refAppSheetSerialNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoIsNull() {
            addCriterion("ext_info_order_no is null");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoIsNotNull() {
            addCriterion("ext_info_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoEqualTo(String value) {
            addCriterion("ext_info_order_no =", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoNotEqualTo(String value) {
            addCriterion("ext_info_order_no <>", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoGreaterThan(String value) {
            addCriterion("ext_info_order_no >", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ext_info_order_no >=", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoLessThan(String value) {
            addCriterion("ext_info_order_no <", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ext_info_order_no <=", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoLike(String value) {
            addCriterion("ext_info_order_no like", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoNotLike(String value) {
            addCriterion("ext_info_order_no not like", value, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoIn(List<String> values) {
            addCriterion("ext_info_order_no in", values, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoNotIn(List<String> values) {
            addCriterion("ext_info_order_no not in", values, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoBetween(String value1, String value2) {
            addCriterion("ext_info_order_no between", value1, value2, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andExtInfoOrderNoNotBetween(String value1, String value2) {
            addCriterion("ext_info_order_no not between", value1, value2, "extInfoOrderNo");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andExtFieldIsNull() {
            addCriterion("ext_field is null");
            return (Criteria) this;
        }

        public Criteria andExtFieldIsNotNull() {
            addCriterion("ext_field is not null");
            return (Criteria) this;
        }

        public Criteria andExtFieldEqualTo(String value) {
            addCriterion("ext_field =", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldNotEqualTo(String value) {
            addCriterion("ext_field <>", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldGreaterThan(String value) {
            addCriterion("ext_field >", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldGreaterThanOrEqualTo(String value) {
            addCriterion("ext_field >=", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldLessThan(String value) {
            addCriterion("ext_field <", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldLessThanOrEqualTo(String value) {
            addCriterion("ext_field <=", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldLike(String value) {
            addCriterion("ext_field like", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldNotLike(String value) {
            addCriterion("ext_field not like", value, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldIn(List<String> values) {
            addCriterion("ext_field in", values, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldNotIn(List<String> values) {
            addCriterion("ext_field not in", values, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldBetween(String value1, String value2) {
            addCriterion("ext_field between", value1, value2, "extField");
            return (Criteria) this;
        }

        public Criteria andExtFieldNotBetween(String value1, String value2) {
            addCriterion("ext_field not between", value1, value2, "extField");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNull() {
            addCriterion("trans_time is null");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNotNull() {
            addCriterion("trans_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransTimeEqualTo(Date value) {
            addCriterion("trans_time =", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotEqualTo(Date value) {
            addCriterion("trans_time <>", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThan(Date value) {
            addCriterion("trans_time >", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trans_time >=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThan(Date value) {
            addCriterion("trans_time <", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThanOrEqualTo(Date value) {
            addCriterion("trans_time <=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeIn(List<Date> values) {
            addCriterion("trans_time in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotIn(List<Date> values) {
            addCriterion("trans_time not in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeBetween(Date value1, Date value2) {
            addCriterion("trans_time between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotBetween(Date value1, Date value2) {
            addCriterion("trans_time not between", value1, value2, "transTime");
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

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNull() {
            addCriterion("refund_time is null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNotNull() {
            addCriterion("refund_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeEqualTo(Date value) {
            addCriterion("refund_time =", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotEqualTo(Date value) {
            addCriterion("refund_time <>", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThan(Date value) {
            addCriterion("refund_time >", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_time >=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThan(Date value) {
            addCriterion("refund_time <", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_time <=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIn(List<Date> values) {
            addCriterion("refund_time in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotIn(List<Date> values) {
            addCriterion("refund_time not in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeBetween(Date value1, Date value2) {
            addCriterion("refund_time between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_time not between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNull() {
            addCriterion("cancel_time is null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNotNull() {
            addCriterion("cancel_time is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeEqualTo(Date value) {
            addCriterion("cancel_time =", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotEqualTo(Date value) {
            addCriterion("cancel_time <>", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThan(Date value) {
            addCriterion("cancel_time >", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cancel_time >=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThan(Date value) {
            addCriterion("cancel_time <", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThanOrEqualTo(Date value) {
            addCriterion("cancel_time <=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIn(List<Date> values) {
            addCriterion("cancel_time in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotIn(List<Date> values) {
            addCriterion("cancel_time not in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeBetween(Date value1, Date value2) {
            addCriterion("cancel_time between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotBetween(Date value1, Date value2) {
            addCriterion("cancel_time not between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitIsNull() {
            addCriterion("refund_amount_limit is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitIsNotNull() {
            addCriterion("refund_amount_limit is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitEqualTo(BigDecimal value) {
            addCriterion("refund_amount_limit =", value, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount_limit <>", value, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitGreaterThan(BigDecimal value) {
            addCriterion("refund_amount_limit >", value, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_limit >=", value, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitLessThan(BigDecimal value) {
            addCriterion("refund_amount_limit <", value, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_limit <=", value, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitIn(List<BigDecimal> values) {
            addCriterion("refund_amount_limit in", values, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount_limit not in", values, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_limit between", value1, value2, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_limit not between", value1, value2, "refundAmountLimit");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNull() {
            addCriterion("refund_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("refund_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("refund_amount =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_amount >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("refund_amount <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("refund_amount in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountIsNull() {
            addCriterion("refund_fee_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountIsNotNull() {
            addCriterion("refund_fee_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountEqualTo(BigDecimal value) {
            addCriterion("refund_fee_amount =", value, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_fee_amount <>", value, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_fee_amount >", value, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_fee_amount >=", value, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountLessThan(BigDecimal value) {
            addCriterion("refund_fee_amount <", value, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_fee_amount <=", value, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountIn(List<BigDecimal> values) {
            addCriterion("refund_fee_amount in", values, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_fee_amount not in", values, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_fee_amount between", value1, value2, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_fee_amount not between", value1, value2, "refundFeeAmount");
            return (Criteria) this;
        }

        public Criteria andToFeeIsNull() {
            addCriterion("to_fee is null");
            return (Criteria) this;
        }

        public Criteria andToFeeIsNotNull() {
            addCriterion("to_fee is not null");
            return (Criteria) this;
        }

        public Criteria andToFeeEqualTo(BigDecimal value) {
            addCriterion("to_fee =", value, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeNotEqualTo(BigDecimal value) {
            addCriterion("to_fee <>", value, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeGreaterThan(BigDecimal value) {
            addCriterion("to_fee >", value, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("to_fee >=", value, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeLessThan(BigDecimal value) {
            addCriterion("to_fee <", value, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("to_fee <=", value, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeIn(List<BigDecimal> values) {
            addCriterion("to_fee in", values, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeNotIn(List<BigDecimal> values) {
            addCriterion("to_fee not in", values, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("to_fee between", value1, value2, "toFee");
            return (Criteria) this;
        }

        public Criteria andToFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("to_fee not between", value1, value2, "toFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeIsNull() {
            addCriterion("from_fee is null");
            return (Criteria) this;
        }

        public Criteria andFromFeeIsNotNull() {
            addCriterion("from_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFromFeeEqualTo(BigDecimal value) {
            addCriterion("from_fee =", value, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeNotEqualTo(BigDecimal value) {
            addCriterion("from_fee <>", value, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeGreaterThan(BigDecimal value) {
            addCriterion("from_fee >", value, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("from_fee >=", value, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeLessThan(BigDecimal value) {
            addCriterion("from_fee <", value, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("from_fee <=", value, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeIn(List<BigDecimal> values) {
            addCriterion("from_fee in", values, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeNotIn(List<BigDecimal> values) {
            addCriterion("from_fee not in", values, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("from_fee between", value1, value2, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFromFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("from_fee not between", value1, value2, "fromFee");
            return (Criteria) this;
        }

        public Criteria andFeeMethodIsNull() {
            addCriterion("fee_method is null");
            return (Criteria) this;
        }

        public Criteria andFeeMethodIsNotNull() {
            addCriterion("fee_method is not null");
            return (Criteria) this;
        }

        public Criteria andFeeMethodEqualTo(Integer value) {
            addCriterion("fee_method =", value, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodNotEqualTo(Integer value) {
            addCriterion("fee_method <>", value, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodGreaterThan(Integer value) {
            addCriterion("fee_method >", value, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee_method >=", value, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodLessThan(Integer value) {
            addCriterion("fee_method <", value, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodLessThanOrEqualTo(Integer value) {
            addCriterion("fee_method <=", value, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodIn(List<Integer> values) {
            addCriterion("fee_method in", values, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodNotIn(List<Integer> values) {
            addCriterion("fee_method not in", values, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodBetween(Integer value1, Integer value2) {
            addCriterion("fee_method between", value1, value2, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andFeeMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("fee_method not between", value1, value2, "feeMethod");
            return (Criteria) this;
        }

        public Criteria andOperatorNoIsNull() {
            addCriterion("operator_no is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNoIsNotNull() {
            addCriterion("operator_no is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNoEqualTo(String value) {
            addCriterion("operator_no =", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoNotEqualTo(String value) {
            addCriterion("operator_no <>", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoGreaterThan(String value) {
            addCriterion("operator_no >", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoGreaterThanOrEqualTo(String value) {
            addCriterion("operator_no >=", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoLessThan(String value) {
            addCriterion("operator_no <", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoLessThanOrEqualTo(String value) {
            addCriterion("operator_no <=", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoLike(String value) {
            addCriterion("operator_no like", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoNotLike(String value) {
            addCriterion("operator_no not like", value, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoIn(List<String> values) {
            addCriterion("operator_no in", values, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoNotIn(List<String> values) {
            addCriterion("operator_no not in", values, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoBetween(String value1, String value2) {
            addCriterion("operator_no between", value1, value2, "operatorNo");
            return (Criteria) this;
        }

        public Criteria andOperatorNoNotBetween(String value1, String value2) {
            addCriterion("operator_no not between", value1, value2, "operatorNo");
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