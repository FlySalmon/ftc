package com.eif.ftc.dal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberFundAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberFundAccountExample() {
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

        public Criteria andFundAccUuidIsNull() {
            addCriterion("fund_acc_uuid is null");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidIsNotNull() {
            addCriterion("fund_acc_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidEqualTo(String value) {
            addCriterion("fund_acc_uuid =", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidNotEqualTo(String value) {
            addCriterion("fund_acc_uuid <>", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidGreaterThan(String value) {
            addCriterion("fund_acc_uuid >", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidGreaterThanOrEqualTo(String value) {
            addCriterion("fund_acc_uuid >=", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidLessThan(String value) {
            addCriterion("fund_acc_uuid <", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidLessThanOrEqualTo(String value) {
            addCriterion("fund_acc_uuid <=", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidLike(String value) {
            addCriterion("fund_acc_uuid like", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidNotLike(String value) {
            addCriterion("fund_acc_uuid not like", value, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidIn(List<String> values) {
            addCriterion("fund_acc_uuid in", values, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidNotIn(List<String> values) {
            addCriterion("fund_acc_uuid not in", values, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidBetween(String value1, String value2) {
            addCriterion("fund_acc_uuid between", value1, value2, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFundAccUuidNotBetween(String value1, String value2) {
            addCriterion("fund_acc_uuid not between", value1, value2, "fundAccUuid");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoIsNull() {
            addCriterion("ftc_create_acc_no is null");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoIsNotNull() {
            addCriterion("ftc_create_acc_no is not null");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoEqualTo(String value) {
            addCriterion("ftc_create_acc_no =", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoNotEqualTo(String value) {
            addCriterion("ftc_create_acc_no <>", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoGreaterThan(String value) {
            addCriterion("ftc_create_acc_no >", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoGreaterThanOrEqualTo(String value) {
            addCriterion("ftc_create_acc_no >=", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoLessThan(String value) {
            addCriterion("ftc_create_acc_no <", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoLessThanOrEqualTo(String value) {
            addCriterion("ftc_create_acc_no <=", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoLike(String value) {
            addCriterion("ftc_create_acc_no like", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoNotLike(String value) {
            addCriterion("ftc_create_acc_no not like", value, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoIn(List<String> values) {
            addCriterion("ftc_create_acc_no in", values, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoNotIn(List<String> values) {
            addCriterion("ftc_create_acc_no not in", values, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoBetween(String value1, String value2) {
            addCriterion("ftc_create_acc_no between", value1, value2, "ftcCreateAccNo");
            return (Criteria) this;
        }

        public Criteria andFtcCreateAccNoNotBetween(String value1, String value2) {
            addCriterion("ftc_create_acc_no not between", value1, value2, "ftcCreateAccNo");
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

        public Criteria andChannelNoIsNull() {
            addCriterion("channel_no is null");
            return (Criteria) this;
        }

        public Criteria andChannelNoIsNotNull() {
            addCriterion("channel_no is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNoEqualTo(Integer value) {
            addCriterion("channel_no =", value, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoNotEqualTo(Integer value) {
            addCriterion("channel_no <>", value, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoGreaterThan(Integer value) {
            addCriterion("channel_no >", value, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_no >=", value, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoLessThan(Integer value) {
            addCriterion("channel_no <", value, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoLessThanOrEqualTo(Integer value) {
            addCriterion("channel_no <=", value, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoIn(List<Integer> values) {
            addCriterion("channel_no in", values, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoNotIn(List<Integer> values) {
            addCriterion("channel_no not in", values, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoBetween(Integer value1, Integer value2) {
            addCriterion("channel_no between", value1, value2, "channelNo");
            return (Criteria) this;
        }

        public Criteria andChannelNoNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_no not between", value1, value2, "channelNo");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIsNull() {
            addCriterion("transaction_account is null");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIsNotNull() {
            addCriterion("transaction_account is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountEqualTo(String value) {
            addCriterion("transaction_account =", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountNotEqualTo(String value) {
            addCriterion("transaction_account <>", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountGreaterThan(String value) {
            addCriterion("transaction_account >", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_account >=", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountLessThan(String value) {
            addCriterion("transaction_account <", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountLessThanOrEqualTo(String value) {
            addCriterion("transaction_account <=", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountLike(String value) {
            addCriterion("transaction_account like", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountNotLike(String value) {
            addCriterion("transaction_account not like", value, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIn(List<String> values) {
            addCriterion("transaction_account in", values, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountNotIn(List<String> values) {
            addCriterion("transaction_account not in", values, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountBetween(String value1, String value2) {
            addCriterion("transaction_account between", value1, value2, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountNotBetween(String value1, String value2) {
            addCriterion("transaction_account not between", value1, value2, "transactionAccount");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoIsNull() {
            addCriterion("ta_company_no is null");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoIsNotNull() {
            addCriterion("ta_company_no is not null");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoEqualTo(Long value) {
            addCriterion("ta_company_no =", value, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoNotEqualTo(Long value) {
            addCriterion("ta_company_no <>", value, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoGreaterThan(Long value) {
            addCriterion("ta_company_no >", value, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoGreaterThanOrEqualTo(Long value) {
            addCriterion("ta_company_no >=", value, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoLessThan(Long value) {
            addCriterion("ta_company_no <", value, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoLessThanOrEqualTo(Long value) {
            addCriterion("ta_company_no <=", value, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoIn(List<Long> values) {
            addCriterion("ta_company_no in", values, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoNotIn(List<Long> values) {
            addCriterion("ta_company_no not in", values, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoBetween(Long value1, Long value2) {
            addCriterion("ta_company_no between", value1, value2, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andTaCompanyNoNotBetween(Long value1, Long value2) {
            addCriterion("ta_company_no not between", value1, value2, "taCompanyNo");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNull() {
            addCriterion("account_status is null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNotNull() {
            addCriterion("account_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusEqualTo(Integer value) {
            addCriterion("account_status =", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotEqualTo(Integer value) {
            addCriterion("account_status <>", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThan(Integer value) {
            addCriterion("account_status >", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_status >=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThan(Integer value) {
            addCriterion("account_status <", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThanOrEqualTo(Integer value) {
            addCriterion("account_status <=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIn(List<Integer> values) {
            addCriterion("account_status in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotIn(List<Integer> values) {
            addCriterion("account_status not in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusBetween(Integer value1, Integer value2) {
            addCriterion("account_status between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("account_status not between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andTaAccountIsNull() {
            addCriterion("ta_account is null");
            return (Criteria) this;
        }

        public Criteria andTaAccountIsNotNull() {
            addCriterion("ta_account is not null");
            return (Criteria) this;
        }

        public Criteria andTaAccountEqualTo(String value) {
            addCriterion("ta_account =", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountNotEqualTo(String value) {
            addCriterion("ta_account <>", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountGreaterThan(String value) {
            addCriterion("ta_account >", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ta_account >=", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountLessThan(String value) {
            addCriterion("ta_account <", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountLessThanOrEqualTo(String value) {
            addCriterion("ta_account <=", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountLike(String value) {
            addCriterion("ta_account like", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountNotLike(String value) {
            addCriterion("ta_account not like", value, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountIn(List<String> values) {
            addCriterion("ta_account in", values, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountNotIn(List<String> values) {
            addCriterion("ta_account not in", values, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountBetween(String value1, String value2) {
            addCriterion("ta_account between", value1, value2, "taAccount");
            return (Criteria) this;
        }

        public Criteria andTaAccountNotBetween(String value1, String value2) {
            addCriterion("ta_account not between", value1, value2, "taAccount");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeIsNull() {
            addCriterion("ftc_create_time is null");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeIsNotNull() {
            addCriterion("ftc_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeEqualTo(Date value) {
            addCriterion("ftc_create_time =", value, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeNotEqualTo(Date value) {
            addCriterion("ftc_create_time <>", value, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeGreaterThan(Date value) {
            addCriterion("ftc_create_time >", value, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ftc_create_time >=", value, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeLessThan(Date value) {
            addCriterion("ftc_create_time <", value, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("ftc_create_time <=", value, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeIn(List<Date> values) {
            addCriterion("ftc_create_time in", values, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeNotIn(List<Date> values) {
            addCriterion("ftc_create_time not in", values, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeBetween(Date value1, Date value2) {
            addCriterion("ftc_create_time between", value1, value2, "ftcCreateTime");
            return (Criteria) this;
        }

        public Criteria andFtcCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("ftc_create_time not between", value1, value2, "ftcCreateTime");
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

        public Criteria andAccountRiskStatusIsNull() {
            addCriterion("account_risk_status is null");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusIsNotNull() {
            addCriterion("account_risk_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusEqualTo(Integer value) {
            addCriterion("account_risk_status =", value, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusNotEqualTo(Integer value) {
            addCriterion("account_risk_status <>", value, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusGreaterThan(Integer value) {
            addCriterion("account_risk_status >", value, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_risk_status >=", value, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusLessThan(Integer value) {
            addCriterion("account_risk_status <", value, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusLessThanOrEqualTo(Integer value) {
            addCriterion("account_risk_status <=", value, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusIn(List<Integer> values) {
            addCriterion("account_risk_status in", values, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusNotIn(List<Integer> values) {
            addCriterion("account_risk_status not in", values, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusBetween(Integer value1, Integer value2) {
            addCriterion("account_risk_status between", value1, value2, "accountRiskStatus");
            return (Criteria) this;
        }

        public Criteria andAccountRiskStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("account_risk_status not between", value1, value2, "accountRiskStatus");
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