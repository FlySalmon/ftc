package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TAFundCheckingAcctOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAFundCheckingAcctOrderExample() {
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

        public Criteria andAvailableVolIsNull() {
            addCriterion("available_vol is null");
            return (Criteria) this;
        }

        public Criteria andAvailableVolIsNotNull() {
            addCriterion("available_vol is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableVolEqualTo(BigDecimal value) {
            addCriterion("available_vol =", value, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolNotEqualTo(BigDecimal value) {
            addCriterion("available_vol <>", value, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolGreaterThan(BigDecimal value) {
            addCriterion("available_vol >", value, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("available_vol >=", value, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolLessThan(BigDecimal value) {
            addCriterion("available_vol <", value, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolLessThanOrEqualTo(BigDecimal value) {
            addCriterion("available_vol <=", value, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolIn(List<BigDecimal> values) {
            addCriterion("available_vol in", values, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolNotIn(List<BigDecimal> values) {
            addCriterion("available_vol not in", values, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("available_vol between", value1, value2, "availableVol");
            return (Criteria) this;
        }

        public Criteria andAvailableVolNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("available_vol not between", value1, value2, "availableVol");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaIsNull() {
            addCriterion("total_vol_of_dist_in_ta is null");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaIsNotNull() {
            addCriterion("total_vol_of_dist_in_ta is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaEqualTo(BigDecimal value) {
            addCriterion("total_vol_of_dist_in_ta =", value, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaNotEqualTo(BigDecimal value) {
            addCriterion("total_vol_of_dist_in_ta <>", value, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaGreaterThan(BigDecimal value) {
            addCriterion("total_vol_of_dist_in_ta >", value, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_vol_of_dist_in_ta >=", value, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaLessThan(BigDecimal value) {
            addCriterion("total_vol_of_dist_in_ta <", value, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_vol_of_dist_in_ta <=", value, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaIn(List<BigDecimal> values) {
            addCriterion("total_vol_of_dist_in_ta in", values, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaNotIn(List<BigDecimal> values) {
            addCriterion("total_vol_of_dist_in_ta not in", values, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_vol_of_dist_in_ta between", value1, value2, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTotalVolOfDistInTaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_vol_of_dist_in_ta not between", value1, value2, "totalVolOfDistInTa");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdIsNull() {
            addCriterion("transaction_account_id is null");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdIsNotNull() {
            addCriterion("transaction_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdEqualTo(String value) {
            addCriterion("transaction_account_id =", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdNotEqualTo(String value) {
            addCriterion("transaction_account_id <>", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdGreaterThan(String value) {
            addCriterion("transaction_account_id >", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_account_id >=", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdLessThan(String value) {
            addCriterion("transaction_account_id <", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdLessThanOrEqualTo(String value) {
            addCriterion("transaction_account_id <=", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdLike(String value) {
            addCriterion("transaction_account_id like", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdNotLike(String value) {
            addCriterion("transaction_account_id not like", value, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdIn(List<String> values) {
            addCriterion("transaction_account_id in", values, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdNotIn(List<String> values) {
            addCriterion("transaction_account_id not in", values, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdBetween(String value1, String value2) {
            addCriterion("transaction_account_id between", value1, value2, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionAccountIdNotBetween(String value1, String value2) {
            addCriterion("transaction_account_id not between", value1, value2, "transactionAccountId");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeIsNull() {
            addCriterion("distributor_code is null");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeIsNotNull() {
            addCriterion("distributor_code is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeEqualTo(String value) {
            addCriterion("distributor_code =", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeNotEqualTo(String value) {
            addCriterion("distributor_code <>", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeGreaterThan(String value) {
            addCriterion("distributor_code >", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_code >=", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeLessThan(String value) {
            addCriterion("distributor_code <", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeLessThanOrEqualTo(String value) {
            addCriterion("distributor_code <=", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeLike(String value) {
            addCriterion("distributor_code like", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeNotLike(String value) {
            addCriterion("distributor_code not like", value, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeIn(List<String> values) {
            addCriterion("distributor_code in", values, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeNotIn(List<String> values) {
            addCriterion("distributor_code not in", values, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeBetween(String value1, String value2) {
            addCriterion("distributor_code between", value1, value2, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andDistributorCodeNotBetween(String value1, String value2) {
            addCriterion("distributor_code not between", value1, value2, "distributorCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeIsNull() {
            addCriterion("branch_code is null");
            return (Criteria) this;
        }

        public Criteria andBranchCodeIsNotNull() {
            addCriterion("branch_code is not null");
            return (Criteria) this;
        }

        public Criteria andBranchCodeEqualTo(String value) {
            addCriterion("branch_code =", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeNotEqualTo(String value) {
            addCriterion("branch_code <>", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeGreaterThan(String value) {
            addCriterion("branch_code >", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeGreaterThanOrEqualTo(String value) {
            addCriterion("branch_code >=", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeLessThan(String value) {
            addCriterion("branch_code <", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeLessThanOrEqualTo(String value) {
            addCriterion("branch_code <=", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeLike(String value) {
            addCriterion("branch_code like", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeNotLike(String value) {
            addCriterion("branch_code not like", value, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeIn(List<String> values) {
            addCriterion("branch_code in", values, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeNotIn(List<String> values) {
            addCriterion("branch_code not in", values, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeBetween(String value1, String value2) {
            addCriterion("branch_code between", value1, value2, "branchCode");
            return (Criteria) this;
        }

        public Criteria andBranchCodeNotBetween(String value1, String value2) {
            addCriterion("branch_code not between", value1, value2, "branchCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeIsNull() {
            addCriterion("fund_code is null");
            return (Criteria) this;
        }

        public Criteria andFundCodeIsNotNull() {
            addCriterion("fund_code is not null");
            return (Criteria) this;
        }

        public Criteria andFundCodeEqualTo(String value) {
            addCriterion("fund_code =", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotEqualTo(String value) {
            addCriterion("fund_code <>", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeGreaterThan(String value) {
            addCriterion("fund_code >", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fund_code >=", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeLessThan(String value) {
            addCriterion("fund_code <", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeLessThanOrEqualTo(String value) {
            addCriterion("fund_code <=", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeLike(String value) {
            addCriterion("fund_code like", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotLike(String value) {
            addCriterion("fund_code not like", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeIn(List<String> values) {
            addCriterion("fund_code in", values, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotIn(List<String> values) {
            addCriterion("fund_code not in", values, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeBetween(String value1, String value2) {
            addCriterion("fund_code between", value1, value2, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotBetween(String value1, String value2) {
            addCriterion("fund_code not between", value1, value2, "fundCode");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdIsNull() {
            addCriterion("ta_account_id is null");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdIsNotNull() {
            addCriterion("ta_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdEqualTo(String value) {
            addCriterion("ta_account_id =", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdNotEqualTo(String value) {
            addCriterion("ta_account_id <>", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdGreaterThan(String value) {
            addCriterion("ta_account_id >", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("ta_account_id >=", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdLessThan(String value) {
            addCriterion("ta_account_id <", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdLessThanOrEqualTo(String value) {
            addCriterion("ta_account_id <=", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdLike(String value) {
            addCriterion("ta_account_id like", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdNotLike(String value) {
            addCriterion("ta_account_id not like", value, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdIn(List<String> values) {
            addCriterion("ta_account_id in", values, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdNotIn(List<String> values) {
            addCriterion("ta_account_id not in", values, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdBetween(String value1, String value2) {
            addCriterion("ta_account_id between", value1, value2, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTaAccountIdNotBetween(String value1, String value2) {
            addCriterion("ta_account_id not between", value1, value2, "taAccountId");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateIsNull() {
            addCriterion("transaction_cfm_date is null");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateIsNotNull() {
            addCriterion("transaction_cfm_date is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateEqualTo(String value) {
            addCriterion("transaction_cfm_date =", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateNotEqualTo(String value) {
            addCriterion("transaction_cfm_date <>", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateGreaterThan(String value) {
            addCriterion("transaction_cfm_date >", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_cfm_date >=", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateLessThan(String value) {
            addCriterion("transaction_cfm_date <", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateLessThanOrEqualTo(String value) {
            addCriterion("transaction_cfm_date <=", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateLike(String value) {
            addCriterion("transaction_cfm_date like", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateNotLike(String value) {
            addCriterion("transaction_cfm_date not like", value, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateIn(List<String> values) {
            addCriterion("transaction_cfm_date in", values, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateNotIn(List<String> values) {
            addCriterion("transaction_cfm_date not in", values, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateBetween(String value1, String value2) {
            addCriterion("transaction_cfm_date between", value1, value2, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andTransactionCfmDateNotBetween(String value1, String value2) {
            addCriterion("transaction_cfm_date not between", value1, value2, "transactionCfmDate");
            return (Criteria) this;
        }

        public Criteria andShareClassIsNull() {
            addCriterion("share_class is null");
            return (Criteria) this;
        }

        public Criteria andShareClassIsNotNull() {
            addCriterion("share_class is not null");
            return (Criteria) this;
        }

        public Criteria andShareClassEqualTo(String value) {
            addCriterion("share_class =", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassNotEqualTo(String value) {
            addCriterion("share_class <>", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassGreaterThan(String value) {
            addCriterion("share_class >", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassGreaterThanOrEqualTo(String value) {
            addCriterion("share_class >=", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassLessThan(String value) {
            addCriterion("share_class <", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassLessThanOrEqualTo(String value) {
            addCriterion("share_class <=", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassLike(String value) {
            addCriterion("share_class like", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassNotLike(String value) {
            addCriterion("share_class not like", value, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassIn(List<String> values) {
            addCriterion("share_class in", values, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassNotIn(List<String> values) {
            addCriterion("share_class not in", values, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassBetween(String value1, String value2) {
            addCriterion("share_class between", value1, value2, "shareClass");
            return (Criteria) this;
        }

        public Criteria andShareClassNotBetween(String value1, String value2) {
            addCriterion("share_class not between", value1, value2, "shareClass");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIsNull() {
            addCriterion("detail_flag is null");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIsNotNull() {
            addCriterion("detail_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDetailFlagEqualTo(String value) {
            addCriterion("detail_flag =", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotEqualTo(String value) {
            addCriterion("detail_flag <>", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagGreaterThan(String value) {
            addCriterion("detail_flag >", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagGreaterThanOrEqualTo(String value) {
            addCriterion("detail_flag >=", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLessThan(String value) {
            addCriterion("detail_flag <", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLessThanOrEqualTo(String value) {
            addCriterion("detail_flag <=", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLike(String value) {
            addCriterion("detail_flag like", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotLike(String value) {
            addCriterion("detail_flag not like", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIn(List<String> values) {
            addCriterion("detail_flag in", values, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotIn(List<String> values) {
            addCriterion("detail_flag not in", values, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagBetween(String value1, String value2) {
            addCriterion("detail_flag between", value1, value2, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotBetween(String value1, String value2) {
            addCriterion("detail_flag not between", value1, value2, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeIsNull() {
            addCriterion("undistribute_monetary_income is null");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeIsNotNull() {
            addCriterion("undistribute_monetary_income is not null");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeEqualTo(BigDecimal value) {
            addCriterion("undistribute_monetary_income =", value, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeNotEqualTo(BigDecimal value) {
            addCriterion("undistribute_monetary_income <>", value, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeGreaterThan(BigDecimal value) {
            addCriterion("undistribute_monetary_income >", value, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("undistribute_monetary_income >=", value, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeLessThan(BigDecimal value) {
            addCriterion("undistribute_monetary_income <", value, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("undistribute_monetary_income <=", value, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeIn(List<BigDecimal> values) {
            addCriterion("undistribute_monetary_income in", values, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeNotIn(List<BigDecimal> values) {
            addCriterion("undistribute_monetary_income not in", values, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("undistribute_monetary_income between", value1, value2, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("undistribute_monetary_income not between", value1, value2, "undistributeMonetaryIncome");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagIsNull() {
            addCriterion("undistribute_monetary_income_flag is null");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagIsNotNull() {
            addCriterion("undistribute_monetary_income_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagEqualTo(String value) {
            addCriterion("undistribute_monetary_income_flag =", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagNotEqualTo(String value) {
            addCriterion("undistribute_monetary_income_flag <>", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagGreaterThan(String value) {
            addCriterion("undistribute_monetary_income_flag >", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("undistribute_monetary_income_flag >=", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagLessThan(String value) {
            addCriterion("undistribute_monetary_income_flag <", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagLessThanOrEqualTo(String value) {
            addCriterion("undistribute_monetary_income_flag <=", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagLike(String value) {
            addCriterion("undistribute_monetary_income_flag like", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagNotLike(String value) {
            addCriterion("undistribute_monetary_income_flag not like", value, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagIn(List<String> values) {
            addCriterion("undistribute_monetary_income_flag in", values, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagNotIn(List<String> values) {
            addCriterion("undistribute_monetary_income_flag not in", values, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagBetween(String value1, String value2) {
            addCriterion("undistribute_monetary_income_flag between", value1, value2, "undistributeMonetaryIncomeFlag");
            return (Criteria) this;
        }

        public Criteria andUndistributeMonetaryIncomeFlagNotBetween(String value1, String value2) {
            addCriterion("undistribute_monetary_income_flag not between", value1, value2, "undistributeMonetaryIncomeFlag");
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