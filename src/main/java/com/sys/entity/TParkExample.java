package com.sys.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TParkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TParkExample() {
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

    /**
     *
     *
     * @author wcyong
     *
     * @date 2020-02-03
     */
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

        public Criteria andPark_nameIsNull() {
            addCriterion("park_name is null");
            return (Criteria) this;
        }

        public Criteria andPark_nameIsNotNull() {
            addCriterion("park_name is not null");
            return (Criteria) this;
        }

        public Criteria andPark_nameEqualTo(String value) {
            addCriterion("park_name =", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameNotEqualTo(String value) {
            addCriterion("park_name <>", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameGreaterThan(String value) {
            addCriterion("park_name >", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameGreaterThanOrEqualTo(String value) {
            addCriterion("park_name >=", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameLessThan(String value) {
            addCriterion("park_name <", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameLessThanOrEqualTo(String value) {
            addCriterion("park_name <=", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameLike(String value) {
            addCriterion("park_name like", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameNotLike(String value) {
            addCriterion("park_name not like", value, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameIn(List<String> values) {
            addCriterion("park_name in", values, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameNotIn(List<String> values) {
            addCriterion("park_name not in", values, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameBetween(String value1, String value2) {
            addCriterion("park_name between", value1, value2, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_nameNotBetween(String value1, String value2) {
            addCriterion("park_name not between", value1, value2, "park_name");
            return (Criteria) this;
        }

        public Criteria andPark_typeIsNull() {
            addCriterion("park_type is null");
            return (Criteria) this;
        }

        public Criteria andPark_typeIsNotNull() {
            addCriterion("park_type is not null");
            return (Criteria) this;
        }

        public Criteria andPark_typeEqualTo(String value) {
            addCriterion("park_type =", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeNotEqualTo(String value) {
            addCriterion("park_type <>", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeGreaterThan(String value) {
            addCriterion("park_type >", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeGreaterThanOrEqualTo(String value) {
            addCriterion("park_type >=", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeLessThan(String value) {
            addCriterion("park_type <", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeLessThanOrEqualTo(String value) {
            addCriterion("park_type <=", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeLike(String value) {
            addCriterion("park_type like", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeNotLike(String value) {
            addCriterion("park_type not like", value, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeIn(List<String> values) {
            addCriterion("park_type in", values, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeNotIn(List<String> values) {
            addCriterion("park_type not in", values, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeBetween(String value1, String value2) {
            addCriterion("park_type between", value1, value2, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_typeNotBetween(String value1, String value2) {
            addCriterion("park_type not between", value1, value2, "park_type");
            return (Criteria) this;
        }

        public Criteria andPark_priceIsNull() {
            addCriterion("park_price is null");
            return (Criteria) this;
        }

        public Criteria andPark_priceIsNotNull() {
            addCriterion("park_price is not null");
            return (Criteria) this;
        }

        public Criteria andPark_priceEqualTo(BigDecimal value) {
            addCriterion("park_price =", value, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceNotEqualTo(BigDecimal value) {
            addCriterion("park_price <>", value, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceGreaterThan(BigDecimal value) {
            addCriterion("park_price >", value, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("park_price >=", value, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceLessThan(BigDecimal value) {
            addCriterion("park_price <", value, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("park_price <=", value, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceIn(List<BigDecimal> values) {
            addCriterion("park_price in", values, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceNotIn(List<BigDecimal> values) {
            addCriterion("park_price not in", values, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("park_price between", value1, value2, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_priceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("park_price not between", value1, value2, "park_price");
            return (Criteria) this;
        }

        public Criteria andPark_statusIsNull() {
            addCriterion("park_status is null");
            return (Criteria) this;
        }

        public Criteria andPark_statusIsNotNull() {
            addCriterion("park_status is not null");
            return (Criteria) this;
        }

        public Criteria andPark_statusEqualTo(String value) {
            addCriterion("park_status =", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusNotEqualTo(String value) {
            addCriterion("park_status <>", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusGreaterThan(String value) {
            addCriterion("park_status >", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusGreaterThanOrEqualTo(String value) {
            addCriterion("park_status >=", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusLessThan(String value) {
            addCriterion("park_status <", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusLessThanOrEqualTo(String value) {
            addCriterion("park_status <=", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusLike(String value) {
            addCriterion("park_status like", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusNotLike(String value) {
            addCriterion("park_status not like", value, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusIn(List<String> values) {
            addCriterion("park_status in", values, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusNotIn(List<String> values) {
            addCriterion("park_status not in", values, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusBetween(String value1, String value2) {
            addCriterion("park_status between", value1, value2, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_statusNotBetween(String value1, String value2) {
            addCriterion("park_status not between", value1, value2, "park_status");
            return (Criteria) this;
        }

        public Criteria andPark_desIsNull() {
            addCriterion("park_des is null");
            return (Criteria) this;
        }

        public Criteria andPark_desIsNotNull() {
            addCriterion("park_des is not null");
            return (Criteria) this;
        }

        public Criteria andPark_desEqualTo(String value) {
            addCriterion("park_des =", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desNotEqualTo(String value) {
            addCriterion("park_des <>", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desGreaterThan(String value) {
            addCriterion("park_des >", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desGreaterThanOrEqualTo(String value) {
            addCriterion("park_des >=", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desLessThan(String value) {
            addCriterion("park_des <", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desLessThanOrEqualTo(String value) {
            addCriterion("park_des <=", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desLike(String value) {
            addCriterion("park_des like", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desNotLike(String value) {
            addCriterion("park_des not like", value, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desIn(List<String> values) {
            addCriterion("park_des in", values, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desNotIn(List<String> values) {
            addCriterion("park_des not in", values, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desBetween(String value1, String value2) {
            addCriterion("park_des between", value1, value2, "park_des");
            return (Criteria) this;
        }

        public Criteria andPark_desNotBetween(String value1, String value2) {
            addCriterion("park_des not between", value1, value2, "park_des");
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

        public Criteria andOrder_idIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrder_idIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_idEqualTo(Integer value) {
            addCriterion("order_id =", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThan(Integer value) {
            addCriterion("order_id >", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThan(Integer value) {
            addCriterion("order_id <", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idIn(List<Integer> values) {
            addCriterion("order_id in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "order_id");
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