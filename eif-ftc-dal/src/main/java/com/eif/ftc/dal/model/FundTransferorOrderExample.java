package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundTransferorOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundTransferorOrderExample() {
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

        public Criteria andTransferModeIsNull() {
            addCriterion("transfer_mode is null");
            return (Criteria) this;
        }

        public Criteria andTransferModeIsNotNull() {
            addCriterion("transfer_mode is not null");
            return (Criteria) this;
        }

        public Criteria andTransferModeEqualTo(Integer value) {
            addCriterion("transfer_mode =", value, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeNotEqualTo(Integer value) {
            addCriterion("transfer_mode <>", value, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeGreaterThan(Integer value) {
            addCriterion("transfer_mode >", value, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_mode >=", value, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeLessThan(Integer value) {
            addCriterion("transfer_mode <", value, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_mode <=", value, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeIn(List<Integer> values) {
            addCriterion("transfer_mode in", values, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeNotIn(List<Integer> values) {
            addCriterion("transfer_mode not in", values, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeBetween(Integer value1, Integer value2) {
            addCriterion("transfer_mode between", value1, value2, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferModeNotBetween(Integer value1, Integer value2) {
            addCriterion("transfer_mode not between", value1, value2, "transferMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeIsNull() {
            addCriterion("transfer_transaction_mode is null");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeIsNotNull() {
            addCriterion("transfer_transaction_mode is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeEqualTo(Integer value) {
            addCriterion("transfer_transaction_mode =", value, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeNotEqualTo(Integer value) {
            addCriterion("transfer_transaction_mode <>", value, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeGreaterThan(Integer value) {
            addCriterion("transfer_transaction_mode >", value, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_transaction_mode >=", value, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeLessThan(Integer value) {
            addCriterion("transfer_transaction_mode <", value, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_transaction_mode <=", value, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeIn(List<Integer> values) {
            addCriterion("transfer_transaction_mode in", values, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeNotIn(List<Integer> values) {
            addCriterion("transfer_transaction_mode not in", values, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeBetween(Integer value1, Integer value2) {
            addCriterion("transfer_transaction_mode between", value1, value2, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andTransferTransactionModeNotBetween(Integer value1, Integer value2) {
            addCriterion("transfer_transaction_mode not between", value1, value2, "transferTransactionMode");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoIsNull() {
            addCriterion("fund_transferor_order_no is null");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoIsNotNull() {
            addCriterion("fund_transferor_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoEqualTo(String value) {
            addCriterion("fund_transferor_order_no =", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoNotEqualTo(String value) {
            addCriterion("fund_transferor_order_no <>", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoGreaterThan(String value) {
            addCriterion("fund_transferor_order_no >", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("fund_transferor_order_no >=", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoLessThan(String value) {
            addCriterion("fund_transferor_order_no <", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoLessThanOrEqualTo(String value) {
            addCriterion("fund_transferor_order_no <=", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoLike(String value) {
            addCriterion("fund_transferor_order_no like", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoNotLike(String value) {
            addCriterion("fund_transferor_order_no not like", value, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoIn(List<String> values) {
            addCriterion("fund_transferor_order_no in", values, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoNotIn(List<String> values) {
            addCriterion("fund_transferor_order_no not in", values, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoBetween(String value1, String value2) {
            addCriterion("fund_transferor_order_no between", value1, value2, "fundTransferorOrderNo");
            return (Criteria) this;
        }

        public Criteria andFundTransferorOrderNoNotBetween(String value1, String value2) {
            addCriterion("fund_transferor_order_no not between", value1, value2, "fundTransferorOrderNo");
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

        public Criteria andOriginalAssetTotalValueIsNull() {
            addCriterion("original_asset_total_value is null");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueIsNotNull() {
            addCriterion("original_asset_total_value is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueEqualTo(BigDecimal value) {
            addCriterion("original_asset_total_value =", value, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueNotEqualTo(BigDecimal value) {
            addCriterion("original_asset_total_value <>", value, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueGreaterThan(BigDecimal value) {
            addCriterion("original_asset_total_value >", value, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_asset_total_value >=", value, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueLessThan(BigDecimal value) {
            addCriterion("original_asset_total_value <", value, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_asset_total_value <=", value, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueIn(List<BigDecimal> values) {
            addCriterion("original_asset_total_value in", values, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueNotIn(List<BigDecimal> values) {
            addCriterion("original_asset_total_value not in", values, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_asset_total_value between", value1, value2, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetTotalValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_asset_total_value not between", value1, value2, "originalAssetTotalValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueIsNull() {
            addCriterion("original_asset_surplus_value is null");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueIsNotNull() {
            addCriterion("original_asset_surplus_value is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueEqualTo(BigDecimal value) {
            addCriterion("original_asset_surplus_value =", value, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueNotEqualTo(BigDecimal value) {
            addCriterion("original_asset_surplus_value <>", value, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueGreaterThan(BigDecimal value) {
            addCriterion("original_asset_surplus_value >", value, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_asset_surplus_value >=", value, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueLessThan(BigDecimal value) {
            addCriterion("original_asset_surplus_value <", value, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_asset_surplus_value <=", value, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueIn(List<BigDecimal> values) {
            addCriterion("original_asset_surplus_value in", values, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueNotIn(List<BigDecimal> values) {
            addCriterion("original_asset_surplus_value not in", values, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_asset_surplus_value between", value1, value2, "originalAssetSurplusValue");
            return (Criteria) this;
        }

        public Criteria andOriginalAssetSurplusValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_asset_surplus_value not between", value1, value2, "originalAssetSurplusValue");
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

        public Criteria andFundTransferPrincipalIsNull() {
            addCriterion("fund_transfer_principal is null");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalIsNotNull() {
            addCriterion("fund_transfer_principal is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_principal =", value, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalNotEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_principal <>", value, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalGreaterThan(BigDecimal value) {
            addCriterion("fund_transfer_principal >", value, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_principal >=", value, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalLessThan(BigDecimal value) {
            addCriterion("fund_transfer_principal <", value, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_principal <=", value, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalIn(List<BigDecimal> values) {
            addCriterion("fund_transfer_principal in", values, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalNotIn(List<BigDecimal> values) {
            addCriterion("fund_transfer_principal not in", values, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_transfer_principal between", value1, value2, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferPrincipalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_transfer_principal not between", value1, value2, "fundTransferPrincipal");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestIsNull() {
            addCriterion("fund_transfer_interest is null");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestIsNotNull() {
            addCriterion("fund_transfer_interest is not null");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_interest =", value, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestNotEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_interest <>", value, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestGreaterThan(BigDecimal value) {
            addCriterion("fund_transfer_interest >", value, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_interest >=", value, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestLessThan(BigDecimal value) {
            addCriterion("fund_transfer_interest <", value, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fund_transfer_interest <=", value, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestIn(List<BigDecimal> values) {
            addCriterion("fund_transfer_interest in", values, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestNotIn(List<BigDecimal> values) {
            addCriterion("fund_transfer_interest not in", values, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_transfer_interest between", value1, value2, "fundTransferInterest");
            return (Criteria) this;
        }

        public Criteria andFundTransferInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fund_transfer_interest not between", value1, value2, "fundTransferInterest");
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

        public Criteria andAnnualYieldIsNull() {
            addCriterion("annual_yield is null");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldIsNotNull() {
            addCriterion("annual_yield is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldEqualTo(BigDecimal value) {
            addCriterion("annual_yield =", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotEqualTo(BigDecimal value) {
            addCriterion("annual_yield <>", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldGreaterThan(BigDecimal value) {
            addCriterion("annual_yield >", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("annual_yield >=", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldLessThan(BigDecimal value) {
            addCriterion("annual_yield <", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldLessThanOrEqualTo(BigDecimal value) {
            addCriterion("annual_yield <=", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldIn(List<BigDecimal> values) {
            addCriterion("annual_yield in", values, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotIn(List<BigDecimal> values) {
            addCriterion("annual_yield not in", values, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("annual_yield between", value1, value2, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("annual_yield not between", value1, value2, "annualYield");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeIsNull() {
            addCriterion("transferor_fee is null");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeIsNotNull() {
            addCriterion("transferor_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeEqualTo(BigDecimal value) {
            addCriterion("transferor_fee =", value, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeNotEqualTo(BigDecimal value) {
            addCriterion("transferor_fee <>", value, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeGreaterThan(BigDecimal value) {
            addCriterion("transferor_fee >", value, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transferor_fee >=", value, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeLessThan(BigDecimal value) {
            addCriterion("transferor_fee <", value, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transferor_fee <=", value, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeIn(List<BigDecimal> values) {
            addCriterion("transferor_fee in", values, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeNotIn(List<BigDecimal> values) {
            addCriterion("transferor_fee not in", values, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transferor_fee between", value1, value2, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransferorFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transferor_fee not between", value1, value2, "transferorFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeIsNull() {
            addCriterion("transferee_fee is null");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeIsNotNull() {
            addCriterion("transferee_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeEqualTo(BigDecimal value) {
            addCriterion("transferee_fee =", value, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeNotEqualTo(BigDecimal value) {
            addCriterion("transferee_fee <>", value, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeGreaterThan(BigDecimal value) {
            addCriterion("transferee_fee >", value, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transferee_fee >=", value, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeLessThan(BigDecimal value) {
            addCriterion("transferee_fee <", value, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transferee_fee <=", value, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeIn(List<BigDecimal> values) {
            addCriterion("transferee_fee in", values, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeNotIn(List<BigDecimal> values) {
            addCriterion("transferee_fee not in", values, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transferee_fee between", value1, value2, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andTransfereeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transferee_fee not between", value1, value2, "transfereeFee");
            return (Criteria) this;
        }

        public Criteria andFeeRuleIsNull() {
            addCriterion("fee_rule is null");
            return (Criteria) this;
        }

        public Criteria andFeeRuleIsNotNull() {
            addCriterion("fee_rule is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRuleEqualTo(String value) {
            addCriterion("fee_rule =", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleNotEqualTo(String value) {
            addCriterion("fee_rule <>", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleGreaterThan(String value) {
            addCriterion("fee_rule >", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleGreaterThanOrEqualTo(String value) {
            addCriterion("fee_rule >=", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleLessThan(String value) {
            addCriterion("fee_rule <", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleLessThanOrEqualTo(String value) {
            addCriterion("fee_rule <=", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleLike(String value) {
            addCriterion("fee_rule like", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleNotLike(String value) {
            addCriterion("fee_rule not like", value, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleIn(List<String> values) {
            addCriterion("fee_rule in", values, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleNotIn(List<String> values) {
            addCriterion("fee_rule not in", values, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleBetween(String value1, String value2) {
            addCriterion("fee_rule between", value1, value2, "feeRule");
            return (Criteria) this;
        }

        public Criteria andFeeRuleNotBetween(String value1, String value2) {
            addCriterion("fee_rule not between", value1, value2, "feeRule");
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