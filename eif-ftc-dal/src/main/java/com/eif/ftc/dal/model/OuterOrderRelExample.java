package com.eif.ftc.dal.model;

import java.util.ArrayList;
import java.util.List;

public class OuterOrderRelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OuterOrderRelExample() {
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

        public Criteria andOuterSysNoIsNull() {
            addCriterion("outer_sys_no is null");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoIsNotNull() {
            addCriterion("outer_sys_no is not null");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoEqualTo(String value) {
            addCriterion("outer_sys_no =", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoNotEqualTo(String value) {
            addCriterion("outer_sys_no <>", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoGreaterThan(String value) {
            addCriterion("outer_sys_no >", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoGreaterThanOrEqualTo(String value) {
            addCriterion("outer_sys_no >=", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoLessThan(String value) {
            addCriterion("outer_sys_no <", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoLessThanOrEqualTo(String value) {
            addCriterion("outer_sys_no <=", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoLike(String value) {
            addCriterion("outer_sys_no like", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoNotLike(String value) {
            addCriterion("outer_sys_no not like", value, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoIn(List<String> values) {
            addCriterion("outer_sys_no in", values, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoNotIn(List<String> values) {
            addCriterion("outer_sys_no not in", values, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoBetween(String value1, String value2) {
            addCriterion("outer_sys_no between", value1, value2, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterSysNoNotBetween(String value1, String value2) {
            addCriterion("outer_sys_no not between", value1, value2, "outerSysNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoIsNull() {
            addCriterion("outer_order_no is null");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoIsNotNull() {
            addCriterion("outer_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoEqualTo(String value) {
            addCriterion("outer_order_no =", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotEqualTo(String value) {
            addCriterion("outer_order_no <>", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoGreaterThan(String value) {
            addCriterion("outer_order_no >", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("outer_order_no >=", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoLessThan(String value) {
            addCriterion("outer_order_no <", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoLessThanOrEqualTo(String value) {
            addCriterion("outer_order_no <=", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoLike(String value) {
            addCriterion("outer_order_no like", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotLike(String value) {
            addCriterion("outer_order_no not like", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoIn(List<String> values) {
            addCriterion("outer_order_no in", values, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotIn(List<String> values) {
            addCriterion("outer_order_no not in", values, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoBetween(String value1, String value2) {
            addCriterion("outer_order_no between", value1, value2, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotBetween(String value1, String value2) {
            addCriterion("outer_order_no not between", value1, value2, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoIsNull() {
            addCriterion("biz_sys_no is null");
            return (Criteria) this;
        }

        public Criteria andBizSysNoIsNotNull() {
            addCriterion("biz_sys_no is not null");
            return (Criteria) this;
        }

        public Criteria andBizSysNoEqualTo(String value) {
            addCriterion("biz_sys_no =", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoNotEqualTo(String value) {
            addCriterion("biz_sys_no <>", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoGreaterThan(String value) {
            addCriterion("biz_sys_no >", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoGreaterThanOrEqualTo(String value) {
            addCriterion("biz_sys_no >=", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoLessThan(String value) {
            addCriterion("biz_sys_no <", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoLessThanOrEqualTo(String value) {
            addCriterion("biz_sys_no <=", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoLike(String value) {
            addCriterion("biz_sys_no like", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoNotLike(String value) {
            addCriterion("biz_sys_no not like", value, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoIn(List<String> values) {
            addCriterion("biz_sys_no in", values, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoNotIn(List<String> values) {
            addCriterion("biz_sys_no not in", values, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoBetween(String value1, String value2) {
            addCriterion("biz_sys_no between", value1, value2, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizSysNoNotBetween(String value1, String value2) {
            addCriterion("biz_sys_no not between", value1, value2, "bizSysNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoIsNull() {
            addCriterion("biz_order_no is null");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoIsNotNull() {
            addCriterion("biz_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoEqualTo(String value) {
            addCriterion("biz_order_no =", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoNotEqualTo(String value) {
            addCriterion("biz_order_no <>", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoGreaterThan(String value) {
            addCriterion("biz_order_no >", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("biz_order_no >=", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoLessThan(String value) {
            addCriterion("biz_order_no <", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoLessThanOrEqualTo(String value) {
            addCriterion("biz_order_no <=", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoLike(String value) {
            addCriterion("biz_order_no like", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoNotLike(String value) {
            addCriterion("biz_order_no not like", value, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoIn(List<String> values) {
            addCriterion("biz_order_no in", values, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoNotIn(List<String> values) {
            addCriterion("biz_order_no not in", values, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoBetween(String value1, String value2) {
            addCriterion("biz_order_no between", value1, value2, "bizOrderNo");
            return (Criteria) this;
        }

        public Criteria andBizOrderNoNotBetween(String value1, String value2) {
            addCriterion("biz_order_no not between", value1, value2, "bizOrderNo");
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