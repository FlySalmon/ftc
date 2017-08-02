package com.eif.ftc.dal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundTransferApplyToExchangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundTransferApplyToExchangeExample() {
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

        public Criteria andOpIdIsNull() {
            addCriterion("op_id is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("op_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(String value) {
            addCriterion("op_id =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(String value) {
            addCriterion("op_id <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(String value) {
            addCriterion("op_id >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(String value) {
            addCriterion("op_id >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(String value) {
            addCriterion("op_id <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(String value) {
            addCriterion("op_id <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLike(String value) {
            addCriterion("op_id like", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotLike(String value) {
            addCriterion("op_id not like", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<String> values) {
            addCriterion("op_id in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<String> values) {
            addCriterion("op_id not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(String value1, String value2) {
            addCriterion("op_id between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(String value1, String value2) {
            addCriterion("op_id not between", value1, value2, "opId");
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

        public Criteria andExchangeTransferorIdIsNull() {
            addCriterion("exchange_transferor_id is null");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdIsNotNull() {
            addCriterion("exchange_transferor_id is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdEqualTo(String value) {
            addCriterion("exchange_transferor_id =", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdNotEqualTo(String value) {
            addCriterion("exchange_transferor_id <>", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdGreaterThan(String value) {
            addCriterion("exchange_transferor_id >", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_transferor_id >=", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdLessThan(String value) {
            addCriterion("exchange_transferor_id <", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdLessThanOrEqualTo(String value) {
            addCriterion("exchange_transferor_id <=", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdLike(String value) {
            addCriterion("exchange_transferor_id like", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdNotLike(String value) {
            addCriterion("exchange_transferor_id not like", value, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdIn(List<String> values) {
            addCriterion("exchange_transferor_id in", values, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdNotIn(List<String> values) {
            addCriterion("exchange_transferor_id not in", values, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdBetween(String value1, String value2) {
            addCriterion("exchange_transferor_id between", value1, value2, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransferorIdNotBetween(String value1, String value2) {
            addCriterion("exchange_transferor_id not between", value1, value2, "exchangeTransferorId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdIsNull() {
            addCriterion("exchange_matching_id is null");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdIsNotNull() {
            addCriterion("exchange_matching_id is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdEqualTo(String value) {
            addCriterion("exchange_matching_id =", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdNotEqualTo(String value) {
            addCriterion("exchange_matching_id <>", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdGreaterThan(String value) {
            addCriterion("exchange_matching_id >", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_matching_id >=", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdLessThan(String value) {
            addCriterion("exchange_matching_id <", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdLessThanOrEqualTo(String value) {
            addCriterion("exchange_matching_id <=", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdLike(String value) {
            addCriterion("exchange_matching_id like", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdNotLike(String value) {
            addCriterion("exchange_matching_id not like", value, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdIn(List<String> values) {
            addCriterion("exchange_matching_id in", values, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdNotIn(List<String> values) {
            addCriterion("exchange_matching_id not in", values, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdBetween(String value1, String value2) {
            addCriterion("exchange_matching_id between", value1, value2, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeMatchingIdNotBetween(String value1, String value2) {
            addCriterion("exchange_matching_id not between", value1, value2, "exchangeMatchingId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdIsNull() {
            addCriterion("exchange_transferee_id is null");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdIsNotNull() {
            addCriterion("exchange_transferee_id is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdEqualTo(String value) {
            addCriterion("exchange_transferee_id =", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdNotEqualTo(String value) {
            addCriterion("exchange_transferee_id <>", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdGreaterThan(String value) {
            addCriterion("exchange_transferee_id >", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_transferee_id >=", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdLessThan(String value) {
            addCriterion("exchange_transferee_id <", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdLessThanOrEqualTo(String value) {
            addCriterion("exchange_transferee_id <=", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdLike(String value) {
            addCriterion("exchange_transferee_id like", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdNotLike(String value) {
            addCriterion("exchange_transferee_id not like", value, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdIn(List<String> values) {
            addCriterion("exchange_transferee_id in", values, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdNotIn(List<String> values) {
            addCriterion("exchange_transferee_id not in", values, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdBetween(String value1, String value2) {
            addCriterion("exchange_transferee_id between", value1, value2, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeTransfereeIdNotBetween(String value1, String value2) {
            addCriterion("exchange_transferee_id not between", value1, value2, "exchangeTransfereeId");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoIsNull() {
            addCriterion("exchange_prod_no is null");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoIsNotNull() {
            addCriterion("exchange_prod_no is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoEqualTo(String value) {
            addCriterion("exchange_prod_no =", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoNotEqualTo(String value) {
            addCriterion("exchange_prod_no <>", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoGreaterThan(String value) {
            addCriterion("exchange_prod_no >", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_prod_no >=", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoLessThan(String value) {
            addCriterion("exchange_prod_no <", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoLessThanOrEqualTo(String value) {
            addCriterion("exchange_prod_no <=", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoLike(String value) {
            addCriterion("exchange_prod_no like", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoNotLike(String value) {
            addCriterion("exchange_prod_no not like", value, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoIn(List<String> values) {
            addCriterion("exchange_prod_no in", values, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoNotIn(List<String> values) {
            addCriterion("exchange_prod_no not in", values, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoBetween(String value1, String value2) {
            addCriterion("exchange_prod_no between", value1, value2, "exchangeProdNo");
            return (Criteria) this;
        }

        public Criteria andExchangeProdNoNotBetween(String value1, String value2) {
            addCriterion("exchange_prod_no not between", value1, value2, "exchangeProdNo");
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

        public Criteria andMemberNoFromIsNull() {
            addCriterion("member_no_from is null");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromIsNotNull() {
            addCriterion("member_no_from is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromEqualTo(String value) {
            addCriterion("member_no_from =", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromNotEqualTo(String value) {
            addCriterion("member_no_from <>", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromGreaterThan(String value) {
            addCriterion("member_no_from >", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromGreaterThanOrEqualTo(String value) {
            addCriterion("member_no_from >=", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromLessThan(String value) {
            addCriterion("member_no_from <", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromLessThanOrEqualTo(String value) {
            addCriterion("member_no_from <=", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromLike(String value) {
            addCriterion("member_no_from like", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromNotLike(String value) {
            addCriterion("member_no_from not like", value, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromIn(List<String> values) {
            addCriterion("member_no_from in", values, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromNotIn(List<String> values) {
            addCriterion("member_no_from not in", values, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromBetween(String value1, String value2) {
            addCriterion("member_no_from between", value1, value2, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoFromNotBetween(String value1, String value2) {
            addCriterion("member_no_from not between", value1, value2, "memberNoFrom");
            return (Criteria) this;
        }

        public Criteria andMemberNoToIsNull() {
            addCriterion("member_no_to is null");
            return (Criteria) this;
        }

        public Criteria andMemberNoToIsNotNull() {
            addCriterion("member_no_to is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNoToEqualTo(String value) {
            addCriterion("member_no_to =", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToNotEqualTo(String value) {
            addCriterion("member_no_to <>", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToGreaterThan(String value) {
            addCriterion("member_no_to >", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToGreaterThanOrEqualTo(String value) {
            addCriterion("member_no_to >=", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToLessThan(String value) {
            addCriterion("member_no_to <", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToLessThanOrEqualTo(String value) {
            addCriterion("member_no_to <=", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToLike(String value) {
            addCriterion("member_no_to like", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToNotLike(String value) {
            addCriterion("member_no_to not like", value, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToIn(List<String> values) {
            addCriterion("member_no_to in", values, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToNotIn(List<String> values) {
            addCriterion("member_no_to not in", values, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToBetween(String value1, String value2) {
            addCriterion("member_no_to between", value1, value2, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andMemberNoToNotBetween(String value1, String value2) {
            addCriterion("member_no_to not between", value1, value2, "memberNoTo");
            return (Criteria) this;
        }

        public Criteria andTransferTimeIsNull() {
            addCriterion("transfer_time is null");
            return (Criteria) this;
        }

        public Criteria andTransferTimeIsNotNull() {
            addCriterion("transfer_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTimeEqualTo(Date value) {
            addCriterion("transfer_time =", value, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeNotEqualTo(Date value) {
            addCriterion("transfer_time <>", value, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeGreaterThan(Date value) {
            addCriterion("transfer_time >", value, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("transfer_time >=", value, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeLessThan(Date value) {
            addCriterion("transfer_time <", value, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeLessThanOrEqualTo(Date value) {
            addCriterion("transfer_time <=", value, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeIn(List<Date> values) {
            addCriterion("transfer_time in", values, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeNotIn(List<Date> values) {
            addCriterion("transfer_time not in", values, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeBetween(Date value1, Date value2) {
            addCriterion("transfer_time between", value1, value2, "transferTime");
            return (Criteria) this;
        }

        public Criteria andTransferTimeNotBetween(Date value1, Date value2) {
            addCriterion("transfer_time not between", value1, value2, "transferTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeIsNull() {
            addCriterion("exchange_execution_time is null");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeIsNotNull() {
            addCriterion("exchange_execution_time is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeEqualTo(Date value) {
            addCriterion("exchange_execution_time =", value, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeNotEqualTo(Date value) {
            addCriterion("exchange_execution_time <>", value, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeGreaterThan(Date value) {
            addCriterion("exchange_execution_time >", value, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exchange_execution_time >=", value, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeLessThan(Date value) {
            addCriterion("exchange_execution_time <", value, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeLessThanOrEqualTo(Date value) {
            addCriterion("exchange_execution_time <=", value, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeIn(List<Date> values) {
            addCriterion("exchange_execution_time in", values, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeNotIn(List<Date> values) {
            addCriterion("exchange_execution_time not in", values, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeBetween(Date value1, Date value2) {
            addCriterion("exchange_execution_time between", value1, value2, "exchangeExecutionTime");
            return (Criteria) this;
        }

        public Criteria andExchangeExecutionTimeNotBetween(Date value1, Date value2) {
            addCriterion("exchange_execution_time not between", value1, value2, "exchangeExecutionTime");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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