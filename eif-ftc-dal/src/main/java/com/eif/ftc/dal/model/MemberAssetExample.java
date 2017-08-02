package com.eif.ftc.dal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberAssetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberAssetExample() {
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

        public Criteria andMemberAssetUuidIsNull() {
            addCriterion("member_asset_uuid is null");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidIsNotNull() {
            addCriterion("member_asset_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidEqualTo(String value) {
            addCriterion("member_asset_uuid =", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidNotEqualTo(String value) {
            addCriterion("member_asset_uuid <>", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidGreaterThan(String value) {
            addCriterion("member_asset_uuid >", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidGreaterThanOrEqualTo(String value) {
            addCriterion("member_asset_uuid >=", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidLessThan(String value) {
            addCriterion("member_asset_uuid <", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidLessThanOrEqualTo(String value) {
            addCriterion("member_asset_uuid <=", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidLike(String value) {
            addCriterion("member_asset_uuid like", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidNotLike(String value) {
            addCriterion("member_asset_uuid not like", value, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidIn(List<String> values) {
            addCriterion("member_asset_uuid in", values, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidNotIn(List<String> values) {
            addCriterion("member_asset_uuid not in", values, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidBetween(String value1, String value2) {
            addCriterion("member_asset_uuid between", value1, value2, "memberAssetUuid");
            return (Criteria) this;
        }

        public Criteria andMemberAssetUuidNotBetween(String value1, String value2) {
            addCriterion("member_asset_uuid not between", value1, value2, "memberAssetUuid");
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

        public Criteria andTotalAssetIsNull() {
            addCriterion("total_asset is null");
            return (Criteria) this;
        }

        public Criteria andTotalAssetIsNotNull() {
            addCriterion("total_asset is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAssetEqualTo(BigDecimal value) {
            addCriterion("total_asset =", value, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetNotEqualTo(BigDecimal value) {
            addCriterion("total_asset <>", value, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetGreaterThan(BigDecimal value) {
            addCriterion("total_asset >", value, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_asset >=", value, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetLessThan(BigDecimal value) {
            addCriterion("total_asset <", value, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_asset <=", value, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetIn(List<BigDecimal> values) {
            addCriterion("total_asset in", values, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetNotIn(List<BigDecimal> values) {
            addCriterion("total_asset not in", values, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_asset between", value1, value2, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andTotalAssetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_asset not between", value1, value2, "totalAsset");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitIsNull() {
            addCriterion("yesterday_profit is null");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitIsNotNull() {
            addCriterion("yesterday_profit is not null");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit =", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitNotEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit <>", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitGreaterThan(BigDecimal value) {
            addCriterion("yesterday_profit >", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit >=", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitLessThan(BigDecimal value) {
            addCriterion("yesterday_profit <", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yesterday_profit <=", value, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitIn(List<BigDecimal> values) {
            addCriterion("yesterday_profit in", values, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitNotIn(List<BigDecimal> values) {
            addCriterion("yesterday_profit not in", values, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yesterday_profit between", value1, value2, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andYesterdayProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yesterday_profit not between", value1, value2, "yesterdayProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitIsNull() {
            addCriterion("total_profit is null");
            return (Criteria) this;
        }

        public Criteria andTotalProfitIsNotNull() {
            addCriterion("total_profit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalProfitEqualTo(BigDecimal value) {
            addCriterion("total_profit =", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitNotEqualTo(BigDecimal value) {
            addCriterion("total_profit <>", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitGreaterThan(BigDecimal value) {
            addCriterion("total_profit >", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_profit >=", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitLessThan(BigDecimal value) {
            addCriterion("total_profit <", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_profit <=", value, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitIn(List<BigDecimal> values) {
            addCriterion("total_profit in", values, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitNotIn(List<BigDecimal> values) {
            addCriterion("total_profit not in", values, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_profit between", value1, value2, "totalProfit");
            return (Criteria) this;
        }

        public Criteria andTotalProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_profit not between", value1, value2, "totalProfit");
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

        public Criteria andFrozenAmountIsNull() {
            addCriterion("frozen_amount is null");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountIsNotNull() {
            addCriterion("frozen_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountEqualTo(BigDecimal value) {
            addCriterion("frozen_amount =", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountNotEqualTo(BigDecimal value) {
            addCriterion("frozen_amount <>", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountGreaterThan(BigDecimal value) {
            addCriterion("frozen_amount >", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_amount >=", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountLessThan(BigDecimal value) {
            addCriterion("frozen_amount <", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_amount <=", value, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountIn(List<BigDecimal> values) {
            addCriterion("frozen_amount in", values, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountNotIn(List<BigDecimal> values) {
            addCriterion("frozen_amount not in", values, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_amount between", value1, value2, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_amount not between", value1, value2, "frozenAmount");
            return (Criteria) this;
        }

        public Criteria andFrozenShareIsNull() {
            addCriterion("frozen_share is null");
            return (Criteria) this;
        }

        public Criteria andFrozenShareIsNotNull() {
            addCriterion("frozen_share is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenShareEqualTo(BigDecimal value) {
            addCriterion("frozen_share =", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareNotEqualTo(BigDecimal value) {
            addCriterion("frozen_share <>", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareGreaterThan(BigDecimal value) {
            addCriterion("frozen_share >", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_share >=", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareLessThan(BigDecimal value) {
            addCriterion("frozen_share <", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_share <=", value, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareIn(List<BigDecimal> values) {
            addCriterion("frozen_share in", values, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareNotIn(List<BigDecimal> values) {
            addCriterion("frozen_share not in", values, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_share between", value1, value2, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andFrozenShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_share not between", value1, value2, "frozenShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountIsNull() {
            addCriterion("confirmed_add_amount is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountIsNotNull() {
            addCriterion("confirmed_add_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount =", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount <>", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountGreaterThan(BigDecimal value) {
            addCriterion("confirmed_add_amount >", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount >=", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountLessThan(BigDecimal value) {
            addCriterion("confirmed_add_amount <", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_amount <=", value, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_amount in", values, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_amount not in", values, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_amount between", value1, value2, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_amount not between", value1, value2, "confirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareIsNull() {
            addCriterion("confirmed_add_share is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareIsNotNull() {
            addCriterion("confirmed_add_share is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share =", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share <>", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareGreaterThan(BigDecimal value) {
            addCriterion("confirmed_add_share >", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share >=", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareLessThan(BigDecimal value) {
            addCriterion("confirmed_add_share <", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_add_share <=", value, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_share in", values, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_add_share not in", values, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_share between", value1, value2, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedAddShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_add_share not between", value1, value2, "confirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountIsNull() {
            addCriterion("unconfirmed_add_amount is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountIsNotNull() {
            addCriterion("unconfirmed_add_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount =", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount <>", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_add_amount >", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount >=", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountLessThan(BigDecimal value) {
            addCriterion("unconfirmed_add_amount <", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_amount <=", value, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_amount in", values, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_amount not in", values, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_amount between", value1, value2, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_amount not between", value1, value2, "unconfirmedAddAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareIsNull() {
            addCriterion("unconfirmed_add_share is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareIsNotNull() {
            addCriterion("unconfirmed_add_share is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share =", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share <>", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_add_share >", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share >=", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareLessThan(BigDecimal value) {
            addCriterion("unconfirmed_add_share <", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_add_share <=", value, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_share in", values, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_add_share not in", values, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_share between", value1, value2, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedAddShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_add_share not between", value1, value2, "unconfirmedAddShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountIsNull() {
            addCriterion("confirmed_sub_amount is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountIsNotNull() {
            addCriterion("confirmed_sub_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount =", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount <>", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountGreaterThan(BigDecimal value) {
            addCriterion("confirmed_sub_amount >", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount >=", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountLessThan(BigDecimal value) {
            addCriterion("confirmed_sub_amount <", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_amount <=", value, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_amount in", values, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_amount not in", values, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_amount between", value1, value2, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_amount not between", value1, value2, "confirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareIsNull() {
            addCriterion("confirmed_sub_share is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareIsNotNull() {
            addCriterion("confirmed_sub_share is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share =", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareNotEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share <>", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareGreaterThan(BigDecimal value) {
            addCriterion("confirmed_sub_share >", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share >=", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareLessThan(BigDecimal value) {
            addCriterion("confirmed_sub_share <", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("confirmed_sub_share <=", value, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_share in", values, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareNotIn(List<BigDecimal> values) {
            addCriterion("confirmed_sub_share not in", values, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_share between", value1, value2, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andConfirmedSubShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("confirmed_sub_share not between", value1, value2, "confirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountIsNull() {
            addCriterion("unconfirmed_sub_amount is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountIsNotNull() {
            addCriterion("unconfirmed_sub_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount =", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount <>", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount >", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount >=", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountLessThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount <", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_amount <=", value, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_amount in", values, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_amount not in", values, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_amount between", value1, value2, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_amount not between", value1, value2, "unconfirmedSubAmount");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareIsNull() {
            addCriterion("unconfirmed_sub_share is null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareIsNotNull() {
            addCriterion("unconfirmed_sub_share is not null");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share =", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareNotEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share <>", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareGreaterThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_share >", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share >=", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareLessThan(BigDecimal value) {
            addCriterion("unconfirmed_sub_share <", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unconfirmed_sub_share <=", value, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_share in", values, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareNotIn(List<BigDecimal> values) {
            addCriterion("unconfirmed_sub_share not in", values, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_share between", value1, value2, "unconfirmedSubShare");
            return (Criteria) this;
        }

        public Criteria andUnconfirmedSubShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unconfirmed_sub_share not between", value1, value2, "unconfirmedSubShare");
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