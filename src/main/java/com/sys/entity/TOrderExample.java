package com.sys.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TOrderExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLicense_plateIsNull() {
            addCriterion("license_plate is null");
            return (Criteria) this;
        }

        public Criteria andLicense_plateIsNotNull() {
            addCriterion("license_plate is not null");
            return (Criteria) this;
        }

        public Criteria andLicense_plateEqualTo(String value) {
            addCriterion("license_plate =", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateNotEqualTo(String value) {
            addCriterion("license_plate <>", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateGreaterThan(String value) {
            addCriterion("license_plate >", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateGreaterThanOrEqualTo(String value) {
            addCriterion("license_plate >=", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateLessThan(String value) {
            addCriterion("license_plate <", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateLessThanOrEqualTo(String value) {
            addCriterion("license_plate <=", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateLike(String value) {
            addCriterion("license_plate like", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateNotLike(String value) {
            addCriterion("license_plate not like", value, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateIn(List<String> values) {
            addCriterion("license_plate in", values, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateNotIn(List<String> values) {
            addCriterion("license_plate not in", values, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateBetween(String value1, String value2) {
            addCriterion("license_plate between", value1, value2, "license_plate");
            return (Criteria) this;
        }

        public Criteria andLicense_plateNotBetween(String value1, String value2) {
            addCriterion("license_plate not between", value1, value2, "license_plate");
            return (Criteria) this;
        }

        public Criteria andPark_idIsNull() {
            addCriterion("park_id is null");
            return (Criteria) this;
        }

        public Criteria andPark_idIsNotNull() {
            addCriterion("park_id is not null");
            return (Criteria) this;
        }

        public Criteria andPark_idEqualTo(Integer value) {
            addCriterion("park_id =", value, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idNotEqualTo(Integer value) {
            addCriterion("park_id <>", value, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idGreaterThan(Integer value) {
            addCriterion("park_id >", value, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("park_id >=", value, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idLessThan(Integer value) {
            addCriterion("park_id <", value, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idLessThanOrEqualTo(Integer value) {
            addCriterion("park_id <=", value, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idIn(List<Integer> values) {
            addCriterion("park_id in", values, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idNotIn(List<Integer> values) {
            addCriterion("park_id not in", values, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idBetween(Integer value1, Integer value2) {
            addCriterion("park_id between", value1, value2, "park_id");
            return (Criteria) this;
        }

        public Criteria andPark_idNotBetween(Integer value1, Integer value2) {
            addCriterion("park_id not between", value1, value2, "park_id");
            return (Criteria) this;
        }

        public Criteria andStart_dateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStart_dateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStart_dateEqualTo(Date value) {
            addCriterion("start_date =", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateGreaterThan(Date value) {
            addCriterion("start_date >", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateLessThan(Date value) {
            addCriterion("start_date <", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateIn(List<Date> values) {
            addCriterion("start_date in", values, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "start_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEnd_dateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEnd_dateEqualTo(Date value) {
            addCriterion("end_date =", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateGreaterThan(Date value) {
            addCriterion("end_date >", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateLessThan(Date value) {
            addCriterion("end_date <", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateIn(List<Date> values) {
            addCriterion("end_date in", values, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "end_date");
            return (Criteria) this;
        }

        public Criteria andLicense_imgIsNull() {
            addCriterion("license_img is null");
            return (Criteria) this;
        }

        public Criteria andLicense_imgIsNotNull() {
            addCriterion("license_img is not null");
            return (Criteria) this;
        }

        public Criteria andLicense_imgEqualTo(String value) {
            addCriterion("license_img =", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgNotEqualTo(String value) {
            addCriterion("license_img <>", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgGreaterThan(String value) {
            addCriterion("license_img >", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgGreaterThanOrEqualTo(String value) {
            addCriterion("license_img >=", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgLessThan(String value) {
            addCriterion("license_img <", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgLessThanOrEqualTo(String value) {
            addCriterion("license_img <=", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgLike(String value) {
            addCriterion("license_img like", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgNotLike(String value) {
            addCriterion("license_img not like", value, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgIn(List<String> values) {
            addCriterion("license_img in", values, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgNotIn(List<String> values) {
            addCriterion("license_img not in", values, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgBetween(String value1, String value2) {
            addCriterion("license_img between", value1, value2, "license_img");
            return (Criteria) this;
        }

        public Criteria andLicense_imgNotBetween(String value1, String value2) {
            addCriterion("license_img not between", value1, value2, "license_img");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(Integer value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(Integer value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(Integer value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<Integer> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andStatus_cdIsNull() {
            addCriterion("status_cd is null");
            return (Criteria) this;
        }

        public Criteria andStatus_cdIsNotNull() {
            addCriterion("status_cd is not null");
            return (Criteria) this;
        }

        public Criteria andStatus_cdEqualTo(String value) {
            addCriterion("status_cd =", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdNotEqualTo(String value) {
            addCriterion("status_cd <>", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdGreaterThan(String value) {
            addCriterion("status_cd >", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdGreaterThanOrEqualTo(String value) {
            addCriterion("status_cd >=", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdLessThan(String value) {
            addCriterion("status_cd <", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdLessThanOrEqualTo(String value) {
            addCriterion("status_cd <=", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdLike(String value) {
            addCriterion("status_cd like", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdNotLike(String value) {
            addCriterion("status_cd not like", value, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdIn(List<String> values) {
            addCriterion("status_cd in", values, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdNotIn(List<String> values) {
            addCriterion("status_cd not in", values, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdBetween(String value1, String value2) {
            addCriterion("status_cd between", value1, value2, "status_cd");
            return (Criteria) this;
        }

        public Criteria andStatus_cdNotBetween(String value1, String value2) {
            addCriterion("status_cd not between", value1, value2, "status_cd");
            return (Criteria) this;
        }

        public Criteria andCount_moneyIsNull() {
            addCriterion("count_money is null");
            return (Criteria) this;
        }

        public Criteria andCount_moneyIsNotNull() {
            addCriterion("count_money is not null");
            return (Criteria) this;
        }

        public Criteria andCount_moneyEqualTo(BigDecimal value) {
            addCriterion("count_money =", value, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyNotEqualTo(BigDecimal value) {
            addCriterion("count_money <>", value, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyGreaterThan(BigDecimal value) {
            addCriterion("count_money >", value, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("count_money >=", value, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyLessThan(BigDecimal value) {
            addCriterion("count_money <", value, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("count_money <=", value, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyIn(List<BigDecimal> values) {
            addCriterion("count_money in", values, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyNotIn(List<BigDecimal> values) {
            addCriterion("count_money not in", values, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("count_money between", value1, value2, "count_money");
            return (Criteria) this;
        }

        public Criteria andCount_moneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("count_money not between", value1, value2, "count_money");
            return (Criteria) this;
        }

        public Criteria andBak1IsNull() {
            addCriterion("bak1 is null");
            return (Criteria) this;
        }

        public Criteria andBak1IsNotNull() {
            addCriterion("bak1 is not null");
            return (Criteria) this;
        }

        public Criteria andBak1EqualTo(String value) {
            addCriterion("bak1 =", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1NotEqualTo(String value) {
            addCriterion("bak1 <>", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1GreaterThan(String value) {
            addCriterion("bak1 >", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1GreaterThanOrEqualTo(String value) {
            addCriterion("bak1 >=", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1LessThan(String value) {
            addCriterion("bak1 <", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1LessThanOrEqualTo(String value) {
            addCriterion("bak1 <=", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1Like(String value) {
            addCriterion("bak1 like", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1NotLike(String value) {
            addCriterion("bak1 not like", value, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1In(List<String> values) {
            addCriterion("bak1 in", values, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1NotIn(List<String> values) {
            addCriterion("bak1 not in", values, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1Between(String value1, String value2) {
            addCriterion("bak1 between", value1, value2, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak1NotBetween(String value1, String value2) {
            addCriterion("bak1 not between", value1, value2, "bak1");
            return (Criteria) this;
        }

        public Criteria andBak2IsNull() {
            addCriterion("bak2 is null");
            return (Criteria) this;
        }

        public Criteria andBak2IsNotNull() {
            addCriterion("bak2 is not null");
            return (Criteria) this;
        }

        public Criteria andBak2EqualTo(String value) {
            addCriterion("bak2 =", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2NotEqualTo(String value) {
            addCriterion("bak2 <>", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2GreaterThan(String value) {
            addCriterion("bak2 >", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2GreaterThanOrEqualTo(String value) {
            addCriterion("bak2 >=", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2LessThan(String value) {
            addCriterion("bak2 <", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2LessThanOrEqualTo(String value) {
            addCriterion("bak2 <=", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2Like(String value) {
            addCriterion("bak2 like", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2NotLike(String value) {
            addCriterion("bak2 not like", value, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2In(List<String> values) {
            addCriterion("bak2 in", values, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2NotIn(List<String> values) {
            addCriterion("bak2 not in", values, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2Between(String value1, String value2) {
            addCriterion("bak2 between", value1, value2, "bak2");
            return (Criteria) this;
        }

        public Criteria andBak2NotBetween(String value1, String value2) {
            addCriterion("bak2 not between", value1, value2, "bak2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     *
     *
     * @author wcyong
     *
     * @date 2020-02-04
     */
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