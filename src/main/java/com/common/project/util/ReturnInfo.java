package com.common.project.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author cuiwei
 */
@ToString
public class ReturnInfo<T> implements Serializable {

    private static final int SUCCESS = 200;
    private static final int FAIL = 1;
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String msg = "success";

    @Getter
    @Setter
    private int code = SUCCESS;

    @Getter
    @Setter
    private T data;

    @Getter
    @Setter
    private Long timestamp = System.currentTimeMillis();

    public ReturnInfo() {
        super();
    }

    public ReturnInfo(T data) {
        super();
        this.data = data;
    }

    public ReturnInfo(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public ReturnInfo(int code, T data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ReturnInfo(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

}
