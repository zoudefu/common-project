package com.common.project.generator.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@SuppressWarnings("serial")
public class PagePojo<T> extends Page<T> {

    /**
     * 查询的起始位置，默认值为0，相当于mysql的limit的第一个参数
     */
//  @ApiModelProperty(value = "分页查询的起始位置，默认值为0")
    private Integer startPosition = 0;

    /**
     * 查询的条数，默认值为100
     */
//  @ApiModelProperty(value = "分页查询的pageSize，默认值为100")
    private Integer maxResult = 100;

    public Integer getCurrentPage(){
        return startPosition/maxResult + 1;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
        this.setCurrent(startPosition/maxResult + 1);
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
        this.setSize(maxResult);
    }

}
