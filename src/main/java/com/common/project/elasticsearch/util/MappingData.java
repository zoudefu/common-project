package com.common.project.elasticsearch.util;

/**
 * program: esdemo
 * description: mapping注解对应的数据载体类
 * author: X-Pacific zhang
 * create: 2019-01-29 15:09
 **/
public class MappingData {

    String field_name;
    /**
     * 数据类型（包含 关键字类型）
     *
     * @return
     */
    String datatype;
    /**
     * 间接关键字
     *
     * @return
     */
    boolean keyword;

    /**
     * 关键字忽略字数
     *
     * @return
     */
    int ignore_above;
    /**
     * 是否支持ngram，高效全文搜索提示
     *
     * @return
     */
    boolean ngram;
    /**
     * 是否支持suggest，高效前缀搜索提示
     *
     * @return
     */
    boolean suggest;
    /**
     * 索引分词器设置
     *
     * @return
     */
    String analyzer;
    /**
     * 搜索内容分词器设置
     *
     * @return
     */
    String search_analyzer;
    //    /**
    //     * 是否分析字段
    //     * @return
    //     */
    //    String analyzedtype;

    private boolean allow_search;

    private String copy_to;

    private String null_value;

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String fieldName) {
        this.field_name = fieldName;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public boolean isKeyword() {
        return keyword;
    }

    public void setKeyword(boolean keyword) {
        this.keyword = keyword;
    }

    public int getIgnore_above() {
        return ignore_above;
    }

    public void setIgnore_above(int ignoreAbove) {
        this.ignore_above = ignoreAbove;
    }

    public boolean isNgram() {
        return ngram;
    }

    public void setNgram(boolean ngram) {
        this.ngram = ngram;
    }

    public boolean isSuggest() {
        return suggest;
    }

    public void setSuggest(boolean suggest) {
        this.suggest = suggest;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }

    public String getSearch_analyzer() {
        return search_analyzer;
    }

    public void setSearch_analyzer(String searchAnalyzer) {
        this.search_analyzer = searchAnalyzer;
    }

    //    public String getAnalyzedtype() {
    //        return analyzedtype;
    //    }
    //
    //    public void setAnalyzedtype(String analyzedtype) {
    //        this.analyzedtype = analyzedtype;
    //    }

    public String getCopy_to() {
        return copy_to;
    }

    public void setCopy_to(String copyTo) {
        this.copy_to = copyTo;
    }

    public boolean isAllow_search() {
        return allow_search;
    }

    public void setAllow_search(boolean allowSearch) {
        this.allow_search = allowSearch;
    }

    public String getNull_value() {
        return null_value;
    }

    public void setNull_value(String nullValue) {
        this.null_value = nullValue;
    }
}
