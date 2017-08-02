package com.eif.ftc.dal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundAcctOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundAcctOrderExample() {
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

        public Criteria andFundAcctOrderNoIsNull() {
            addCriterion("fund_acct_order_no is null");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoIsNotNull() {
            addCriterion("fund_acct_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoEqualTo(String value) {
            addCriterion("fund_acct_order_no =", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoNotEqualTo(String value) {
            addCriterion("fund_acct_order_no <>", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoGreaterThan(String value) {
            addCriterion("fund_acct_order_no >", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("fund_acct_order_no >=", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoLessThan(String value) {
            addCriterion("fund_acct_order_no <", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoLessThanOrEqualTo(String value) {
            addCriterion("fund_acct_order_no <=", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoLike(String value) {
            addCriterion("fund_acct_order_no like", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoNotLike(String value) {
            addCriterion("fund_acct_order_no not like", value, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoIn(List<String> values) {
            addCriterion("fund_acct_order_no in", values, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoNotIn(List<String> values) {
            addCriterion("fund_acct_order_no not in", values, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoBetween(String value1, String value2) {
            addCriterion("fund_acct_order_no between", value1, value2, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundAcctOrderNoNotBetween(String value1, String value2) {
            addCriterion("fund_acct_order_no not between", value1, value2, "fundAcctOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoIsNull() {
            addCriterion("fund_trans_acct_no is null");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoIsNotNull() {
            addCriterion("fund_trans_acct_no is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoEqualTo(String value) {
            addCriterion("fund_trans_acct_no =", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoNotEqualTo(String value) {
            addCriterion("fund_trans_acct_no <>", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoGreaterThan(String value) {
            addCriterion("fund_trans_acct_no >", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoGreaterThanOrEqualTo(String value) {
            addCriterion("fund_trans_acct_no >=", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoLessThan(String value) {
            addCriterion("fund_trans_acct_no <", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoLessThanOrEqualTo(String value) {
            addCriterion("fund_trans_acct_no <=", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoLike(String value) {
            addCriterion("fund_trans_acct_no like", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoNotLike(String value) {
            addCriterion("fund_trans_acct_no not like", value, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoIn(List<String> values) {
            addCriterion("fund_trans_acct_no in", values, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoNotIn(List<String> values) {
            addCriterion("fund_trans_acct_no not in", values, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoBetween(String value1, String value2) {
            addCriterion("fund_trans_acct_no between", value1, value2, "fundTransAcctNo");
            return (Criteria) this;
        }

        public Criteria andFundTransAcctNoNotBetween(String value1, String value2) {
            addCriterion("fund_trans_acct_no not between", value1, value2, "fundTransAcctNo");
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

        public Criteria andFundAcctOperTypeIsNull() {
            addCriterion("fund_acct_oper_type is null");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeIsNotNull() {
            addCriterion("fund_acct_oper_type is not null");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeEqualTo(Integer value) {
            addCriterion("fund_acct_oper_type =", value, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeNotEqualTo(Integer value) {
            addCriterion("fund_acct_oper_type <>", value, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeGreaterThan(Integer value) {
            addCriterion("fund_acct_oper_type >", value, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fund_acct_oper_type >=", value, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeLessThan(Integer value) {
            addCriterion("fund_acct_oper_type <", value, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fund_acct_oper_type <=", value, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeIn(List<Integer> values) {
            addCriterion("fund_acct_oper_type in", values, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeNotIn(List<Integer> values) {
            addCriterion("fund_acct_oper_type not in", values, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeBetween(Integer value1, Integer value2) {
            addCriterion("fund_acct_oper_type between", value1, value2, "fundAcctOperType");
            return (Criteria) this;
        }

        public Criteria andFundAcctOperTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fund_acct_oper_type not between", value1, value2, "fundAcctOperType");
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