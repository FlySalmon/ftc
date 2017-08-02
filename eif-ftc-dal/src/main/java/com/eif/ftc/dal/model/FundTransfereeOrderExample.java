package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundTransfereeOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundTransfereeOrderExample() {
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

        public Criteria andFundTransfereeOrderNoIsNull() {
            addCriterion("fund_transferee_order_no is null");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoIsNotNull() {
            addCriterion("fund_transferee_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoEqualTo(String value) {
            addCriterion("fund_transferee_order_no =", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoNotEqualTo(String value) {
            addCriterion("fund_transferee_order_no <>", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoGreaterThan(String value) {
            addCriterion("fund_transferee_order_no >", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("fund_transferee_order_no >=", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoLessThan(String value) {
            addCriterion("fund_transferee_order_no <", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoLessThanOrEqualTo(String value) {
            addCriterion("fund_transferee_order_no <=", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoLike(String value) {
            addCriterion("fund_transferee_order_no like", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoNotLike(String value) {
            addCriterion("fund_transferee_order_no not like", value, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoIn(List<String> values) {
            addCriterion("fund_transferee_order_no in", values, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoNotIn(List<String> values) {
            addCriterion("fund_transferee_order_no not in", values, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoBetween(String value1, String value2) {
            addCriterion("fund_transferee_order_no between", value1, value2, "fundTransfereeOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransfereeOrderNoNotBetween(String value1, String value2) {
            addCriterion("fund_transferee_order_no not between", value1, value2, "fundTransfereeOrderNo");
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

        public Criteria andRefFundTransferorOrderNoIsNull() {
            addCriterion("ref_fund_transferor_order_no is null");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoIsNotNull() {
            addCriterion("ref_fund_transferor_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoEqualTo(String value) {
            addCriterion("ref_fund_transferor_order_no =", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoNotEqualTo(String value) {
            addCriterion("ref_fund_transferor_order_no <>", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoGreaterThan(String value) {
            addCriterion("ref_fund_transferor_order_no >", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_fund_transferor_order_no >=", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoLessThan(String value) {
            addCriterion("ref_fund_transferor_order_no <", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ref_fund_transferor_order_no <=", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoLike(String value) {
            addCriterion("ref_fund_transferor_order_no like", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoNotLike(String value) {
            addCriterion("ref_fund_transferor_order_no not like", value, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoIn(List<String> values) {
            addCriterion("ref_fund_transferor_order_no in", values, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoNotIn(List<String> values) {
            addCriterion("ref_fund_transferor_order_no not in", values, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoBetween(String value1, String value2) {
            addCriterion("ref_fund_transferor_order_no between", value1, value2, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefFundTransferorOrderNoNotBetween(String value1, String value2) {
            addCriterion("ref_fund_transferor_order_no not between", value1, value2, "refFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoIsNull() {
            addCriterion("ref_origin_fund_transferor_order_no is null");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoIsNotNull() {
            addCriterion("ref_origin_fund_transferor_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoEqualTo(String value) {
            addCriterion("ref_origin_fund_transferor_order_no =", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoNotEqualTo(String value) {
            addCriterion("ref_origin_fund_transferor_order_no <>", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoGreaterThan(String value) {
            addCriterion("ref_origin_fund_transferor_order_no >", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_origin_fund_transferor_order_no >=", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoLessThan(String value) {
            addCriterion("ref_origin_fund_transferor_order_no <", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ref_origin_fund_transferor_order_no <=", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoLike(String value) {
            addCriterion("ref_origin_fund_transferor_order_no like", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoNotLike(String value) {
            addCriterion("ref_origin_fund_transferor_order_no not like", value, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoIn(List<String> values) {
            addCriterion("ref_origin_fund_transferor_order_no in", values, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoNotIn(List<String> values) {
            addCriterion("ref_origin_fund_transferor_order_no not in", values, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoBetween(String value1, String value2) {
            addCriterion("ref_origin_fund_transferor_order_no between", value1, value2, "refOriginFundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andRefOriginFundTransferorOrderNoNotBetween(String value1, String value2) {
            addCriterion("ref_origin_fund_transferor_order_no not between", value1, value2, "refOriginFundTransferorOrderNo");
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

        public Criteria andTransferAgreementNoIsNull() {
            addCriterion("transfer_agreement_no is null");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoIsNotNull() {
            addCriterion("transfer_agreement_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoEqualTo(String value) {
            addCriterion("transfer_agreement_no =", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoNotEqualTo(String value) {
            addCriterion("transfer_agreement_no <>", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoGreaterThan(String value) {
            addCriterion("transfer_agreement_no >", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_agreement_no >=", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoLessThan(String value) {
            addCriterion("transfer_agreement_no <", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoLessThanOrEqualTo(String value) {
            addCriterion("transfer_agreement_no <=", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoLike(String value) {
            addCriterion("transfer_agreement_no like", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoNotLike(String value) {
            addCriterion("transfer_agreement_no not like", value, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoIn(List<String> values) {
            addCriterion("transfer_agreement_no in", values, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoNotIn(List<String> values) {
            addCriterion("transfer_agreement_no not in", values, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoBetween(String value1, String value2) {
            addCriterion("transfer_agreement_no between", value1, value2, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andTransferAgreementNoNotBetween(String value1, String value2) {
            addCriterion("transfer_agreement_no not between", value1, value2, "transferAgreementNo");
            return (Criteria) this;
        }

        public Criteria andMktProductIdIsNull() {
            addCriterion("mkt_product_id is null");
            return (Criteria) this;
        }

        public Criteria andMktProductIdIsNotNull() {
            addCriterion("mkt_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andMktProductIdEqualTo(Long value) {
            addCriterion("mkt_product_id =", value, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdNotEqualTo(Long value) {
            addCriterion("mkt_product_id <>", value, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdGreaterThan(Long value) {
            addCriterion("mkt_product_id >", value, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("mkt_product_id >=", value, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdLessThan(Long value) {
            addCriterion("mkt_product_id <", value, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdLessThanOrEqualTo(Long value) {
            addCriterion("mkt_product_id <=", value, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdIn(List<Long> values) {
            addCriterion("mkt_product_id in", values, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdNotIn(List<Long> values) {
            addCriterion("mkt_product_id not in", values, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdBetween(Long value1, Long value2) {
            addCriterion("mkt_product_id between", value1, value2, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andMktProductIdNotBetween(Long value1, Long value2) {
            addCriterion("mkt_product_id not between", value1, value2, "mktProductId");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountIsNull() {
            addCriterion("fund_transfer_amount is null");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountIsNotNull() {
            addCriterion("fund_transfer_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_amount =", value, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountNotEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_amount <>", value, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountGreaterThan(BigDecimal value) {
            addCriterion("fund_transfer_amount >", value, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_amount >=", value, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountLessThan(BigDecimal value) {
            addCriterion("fund_transfer_amount <", value, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_amount <=", value, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountIn(List<BigDecimal> values) {
            addCriterion("fund_transfer_amount in", values, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountNotIn(List<BigDecimal> values) {
            addCriterion("fund_transfer_amount not in", values, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_transfer_amount between", value1, value2, "fundTransferAmount");
            return (Criteria) this;
        }

        public Criteria andFundTransferAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_transfer_amount not between", value1, value2, "fundTransferAmount");
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

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andPayMethodIsNull() {
            addCriterion("pay_method is null");
            return (Criteria) this;
        }

        public Criteria andPayMethodIsNotNull() {
            addCriterion("pay_method is not null");
            return (Criteria) this;
        }

        public Criteria andPayMethodEqualTo(String value) {
            addCriterion("pay_method =", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotEqualTo(String value) {
            addCriterion("pay_method <>", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodGreaterThan(String value) {
            addCriterion("pay_method >", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodGreaterThanOrEqualTo(String value) {
            addCriterion("pay_method >=", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLessThan(String value) {
            addCriterion("pay_method <", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLessThanOrEqualTo(String value) {
            addCriterion("pay_method <=", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLike(String value) {
            addCriterion("pay_method like", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotLike(String value) {
            addCriterion("pay_method not like", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodIn(List<String> values) {
            addCriterion("pay_method in", values, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotIn(List<String> values) {
            addCriterion("pay_method not in", values, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodBetween(String value1, String value2) {
            addCriterion("pay_method between", value1, value2, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotBetween(String value1, String value2) {
            addCriterion("pay_method not between", value1, value2, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescIsNull() {
            addCriterion("pay_method_desc is null");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescIsNotNull() {
            addCriterion("pay_method_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescEqualTo(String value) {
            addCriterion("pay_method_desc =", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescNotEqualTo(String value) {
            addCriterion("pay_method_desc <>", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescGreaterThan(String value) {
            addCriterion("pay_method_desc >", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescGreaterThanOrEqualTo(String value) {
            addCriterion("pay_method_desc >=", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescLessThan(String value) {
            addCriterion("pay_method_desc <", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescLessThanOrEqualTo(String value) {
            addCriterion("pay_method_desc <=", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescLike(String value) {
            addCriterion("pay_method_desc like", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescNotLike(String value) {
            addCriterion("pay_method_desc not like", value, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescIn(List<String> values) {
            addCriterion("pay_method_desc in", values, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescNotIn(List<String> values) {
            addCriterion("pay_method_desc not in", values, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescBetween(String value1, String value2) {
            addCriterion("pay_method_desc between", value1, value2, "payMethodDesc");
            return (Criteria) this;
        }

        public Criteria andPayMethodDescNotBetween(String value1, String value2) {
            addCriterion("pay_method_desc not between", value1, value2, "payMethodDesc");
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

        public Criteria andFrozenTokenIsNull() {
            addCriterion("frozen_token is null");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenIsNotNull() {
            addCriterion("frozen_token is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenEqualTo(String value) {
            addCriterion("frozen_token =", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenNotEqualTo(String value) {
            addCriterion("frozen_token <>", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenGreaterThan(String value) {
            addCriterion("frozen_token >", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenGreaterThanOrEqualTo(String value) {
            addCriterion("frozen_token >=", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenLessThan(String value) {
            addCriterion("frozen_token <", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenLessThanOrEqualTo(String value) {
            addCriterion("frozen_token <=", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenLike(String value) {
            addCriterion("frozen_token like", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenNotLike(String value) {
            addCriterion("frozen_token not like", value, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenIn(List<String> values) {
            addCriterion("frozen_token in", values, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenNotIn(List<String> values) {
            addCriterion("frozen_token not in", values, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenBetween(String value1, String value2) {
            addCriterion("frozen_token between", value1, value2, "frozenToken");
            return (Criteria) this;
        }

        public Criteria andFrozenTokenNotBetween(String value1, String value2) {
            addCriterion("frozen_token not between", value1, value2, "frozenToken");
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

        public Criteria andTransferTransNoIsNull() {
            addCriterion("transfer_trans_no is null");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoIsNotNull() {
            addCriterion("transfer_trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoEqualTo(String value) {
            addCriterion("transfer_trans_no =", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoNotEqualTo(String value) {
            addCriterion("transfer_trans_no <>", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoGreaterThan(String value) {
            addCriterion("transfer_trans_no >", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_trans_no >=", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoLessThan(String value) {
            addCriterion("transfer_trans_no <", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoLessThanOrEqualTo(String value) {
            addCriterion("transfer_trans_no <=", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoLike(String value) {
            addCriterion("transfer_trans_no like", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoNotLike(String value) {
            addCriterion("transfer_trans_no not like", value, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoIn(List<String> values) {
            addCriterion("transfer_trans_no in", values, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoNotIn(List<String> values) {
            addCriterion("transfer_trans_no not in", values, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoBetween(String value1, String value2) {
            addCriterion("transfer_trans_no between", value1, value2, "transferTransNo");
            return (Criteria) this;
        }

        public Criteria andTransferTransNoNotBetween(String value1, String value2) {
            addCriterion("transfer_trans_no not between", value1, value2, "transferTransNo");
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

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
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

        public Criteria andExpiryTimeIsNull() {
            addCriterion("expiry_time is null");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIsNotNull() {
            addCriterion("expiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeEqualTo(Date value) {
            addCriterion("expiry_time =", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotEqualTo(Date value) {
            addCriterion("expiry_time <>", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeGreaterThan(Date value) {
            addCriterion("expiry_time >", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expiry_time >=", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLessThan(Date value) {
            addCriterion("expiry_time <", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("expiry_time <=", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIn(List<Date> values) {
            addCriterion("expiry_time in", values, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotIn(List<Date> values) {
            addCriterion("expiry_time not in", values, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("expiry_time between", value1, value2, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("expiry_time not between", value1, value2, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeIsNull() {
            addCriterion("status_expiry_time is null");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeIsNotNull() {
            addCriterion("status_expiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeEqualTo(Date value) {
            addCriterion("status_expiry_time =", value, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeNotEqualTo(Date value) {
            addCriterion("status_expiry_time <>", value, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeGreaterThan(Date value) {
            addCriterion("status_expiry_time >", value, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("status_expiry_time >=", value, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeLessThan(Date value) {
            addCriterion("status_expiry_time <", value, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("status_expiry_time <=", value, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeIn(List<Date> values) {
            addCriterion("status_expiry_time in", values, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeNotIn(List<Date> values) {
            addCriterion("status_expiry_time not in", values, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("status_expiry_time between", value1, value2, "statusExpiryTime");
            return (Criteria) this;
        }

        public Criteria andStatusExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("status_expiry_time not between", value1, value2, "statusExpiryTime");
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