package edu.fdu.se.bean;

import java.util.ArrayList;
import java.util.List;

public class AndroidSDKJavaFileExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public AndroidSDKJavaFileExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
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

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileFullPathIsNull() {
            addCriterion("file_full_path is null");
            return (Criteria) this;
        }

        public Criteria andFileFullPathIsNotNull() {
            addCriterion("file_full_path is not null");
            return (Criteria) this;
        }

        public Criteria andFileFullPathEqualTo(String value) {
            addCriterion("file_full_path =", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathNotEqualTo(String value) {
            addCriterion("file_full_path <>", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathGreaterThan(String value) {
            addCriterion("file_full_path >", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathGreaterThanOrEqualTo(String value) {
            addCriterion("file_full_path >=", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathLessThan(String value) {
            addCriterion("file_full_path <", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathLessThanOrEqualTo(String value) {
            addCriterion("file_full_path <=", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathLike(String value) {
            addCriterion("file_full_path like", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathNotLike(String value) {
            addCriterion("file_full_path not like", value, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathIn(List<String> values) {
            addCriterion("file_full_path in", values, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathNotIn(List<String> values) {
            addCriterion("file_full_path not in", values, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathBetween(String value1, String value2) {
            addCriterion("file_full_path between", value1, value2, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andFileFullPathNotBetween(String value1, String value2) {
            addCriterion("file_full_path not between", value1, value2, "fileFullPath");
            return (Criteria) this;
        }

        public Criteria andSdkVersionIsNull() {
            addCriterion("sdk_version is null");
            return (Criteria) this;
        }

        public Criteria andSdkVersionIsNotNull() {
            addCriterion("sdk_version is not null");
            return (Criteria) this;
        }

        public Criteria andSdkVersionEqualTo(Integer value) {
            addCriterion("sdk_version =", value, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionNotEqualTo(Integer value) {
            addCriterion("sdk_version <>", value, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionGreaterThan(Integer value) {
            addCriterion("sdk_version >", value, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("sdk_version >=", value, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionLessThan(Integer value) {
            addCriterion("sdk_version <", value, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionLessThanOrEqualTo(Integer value) {
            addCriterion("sdk_version <=", value, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionIn(List<Integer> values) {
            addCriterion("sdk_version in", values, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionNotIn(List<Integer> values) {
            addCriterion("sdk_version not in", values, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionBetween(Integer value1, Integer value2) {
            addCriterion("sdk_version between", value1, value2, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSdkVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("sdk_version not between", value1, value2, "sdkVersion");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIsNull() {
            addCriterion("sub_category is null");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIsNotNull() {
            addCriterion("sub_category is not null");
            return (Criteria) this;
        }

        public Criteria andSubCategoryEqualTo(String value) {
            addCriterion("sub_category =", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotEqualTo(String value) {
            addCriterion("sub_category <>", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryGreaterThan(String value) {
            addCriterion("sub_category >", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("sub_category >=", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLessThan(String value) {
            addCriterion("sub_category <", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLessThanOrEqualTo(String value) {
            addCriterion("sub_category <=", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLike(String value) {
            addCriterion("sub_category like", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotLike(String value) {
            addCriterion("sub_category not like", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIn(List<String> values) {
            addCriterion("sub_category in", values, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotIn(List<String> values) {
            addCriterion("sub_category not in", values, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryBetween(String value1, String value2) {
            addCriterion("sub_category between", value1, value2, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotBetween(String value1, String value2) {
            addCriterion("sub_category not between", value1, value2, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryIsNull() {
            addCriterion("sub_sub_category is null");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryIsNotNull() {
            addCriterion("sub_sub_category is not null");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryEqualTo(String value) {
            addCriterion("sub_sub_category =", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryNotEqualTo(String value) {
            addCriterion("sub_sub_category <>", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryGreaterThan(String value) {
            addCriterion("sub_sub_category >", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("sub_sub_category >=", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryLessThan(String value) {
            addCriterion("sub_sub_category <", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryLessThanOrEqualTo(String value) {
            addCriterion("sub_sub_category <=", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryLike(String value) {
            addCriterion("sub_sub_category like", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryNotLike(String value) {
            addCriterion("sub_sub_category not like", value, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryIn(List<String> values) {
            addCriterion("sub_sub_category in", values, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryNotIn(List<String> values) {
            addCriterion("sub_sub_category not in", values, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryBetween(String value1, String value2) {
            addCriterion("sub_sub_category between", value1, value2, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryNotBetween(String value1, String value2) {
            addCriterion("sub_sub_category not between", value1, value2, "subSubCategory");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathIsNull() {
            addCriterion("sub_sub_category_path is null");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathIsNotNull() {
            addCriterion("sub_sub_category_path is not null");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathEqualTo(String value) {
            addCriterion("sub_sub_category_path =", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathNotEqualTo(String value) {
            addCriterion("sub_sub_category_path <>", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathGreaterThan(String value) {
            addCriterion("sub_sub_category_path >", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathGreaterThanOrEqualTo(String value) {
            addCriterion("sub_sub_category_path >=", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathLessThan(String value) {
            addCriterion("sub_sub_category_path <", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathLessThanOrEqualTo(String value) {
            addCriterion("sub_sub_category_path <=", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathLike(String value) {
            addCriterion("sub_sub_category_path like", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathNotLike(String value) {
            addCriterion("sub_sub_category_path not like", value, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathIn(List<String> values) {
            addCriterion("sub_sub_category_path in", values, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathNotIn(List<String> values) {
            addCriterion("sub_sub_category_path not in", values, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathBetween(String value1, String value2) {
            addCriterion("sub_sub_category_path between", value1, value2, "subSubCategoryPath");
            return (Criteria) this;
        }

        public Criteria andSubSubCategoryPathNotBetween(String value1, String value2) {
            addCriterion("sub_sub_category_path not between", value1, value2, "subSubCategoryPath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated do_not_delete_during_merge Tue Oct 31 09:29:10 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table android_sdk_java_file
     *
     * @mbg.generated Tue Oct 31 09:29:10 CST 2017
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