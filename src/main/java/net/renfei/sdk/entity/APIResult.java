package net.renfei.sdk.entity;

import net.renfei.sdk.comm.StateCode;

/**
 * API统一响应返回对象
 *
 * @author RenFei
 */
public final class APIResult {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 数据负载对象
     */
    private Object data;

    private APIResult() {
        this.timestamp = System.currentTimeMillis();
    }

    public APIResult build(Builder builder) {
        APIResult apiResult = new APIResult();
        apiResult.code = builder.code;
        apiResult.message = builder.message;
        apiResult.data = builder.data;
        return apiResult;
    }

    public APIResult Success() {
        APIResult apiResult = new APIResult();
        apiResult.code = StateCode.OK.getCode();
        apiResult.message = StateCode.OK.getDescribe();
        return apiResult;
    }

    public static class Builder {
        private StateCode stateCode;
        private Integer code;
        private String message;
        private Object data;

        public Builder code(StateCode stateCode) {
            this.stateCode = stateCode;
            this.code = stateCode.getCode();
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
