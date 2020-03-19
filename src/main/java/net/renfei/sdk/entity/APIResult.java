package net.renfei.sdk.entity;

import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.EncryptionUtils;
import net.renfei.sdk.utils.RandomStringUtils;
import net.renfei.sdk.utils.StringUtils;

import java.util.Arrays;

/**
 * API统一响应返回对象
 *
 * @author RenFei
 */
public final class APIResult<T> {
    /**
     * 状态枚举
     */
    private StateCode stateCode;
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
    private Integer timestamp;
    /**
     * 签名，将时间戳+随机字符串字典排序后SHA1
     */
    private String signature;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 数据负载对象
     */
    private T data;

    /**
     * 私有构造防止直接实例化
     */
    private APIResult() {
        signature();
    }

    /**
     * 通过Builder构造
     *
     * @param builder 构造器
     */
    private APIResult(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
        this.stateCode = builder.stateCode;
        signature();
    }

    /**
     * 获取一个构造器
     *
     * @return 构造器
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 直接返回成功状态
     *
     * @return APIResult
     */
    public static APIResult success() {
        APIResult apiResult = new APIResult();
        apiResult.stateCode = StateCode.OK;
        apiResult.code = StateCode.OK.getCode();
        apiResult.message = StateCode.OK.getDescribe();
        return apiResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        if (BeanUtils.isEmpty(message)) {
            return stateCode.getDescribe();
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * APIResult的构造器
     *
     * @param <T> 数据类型
     */
    public static class Builder<T> {
        private StateCode stateCode;
        private Integer code;
        private String message;
        private T data;

        public Builder code(StateCode stateCode) {
            this.stateCode = stateCode;
            this.code = stateCode.getCode();
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        /**
         * 通过构造方法创建
         *
         * @return APIResult对象
         */
        public APIResult<T> build() {
            return new APIResult<>(this);
        }
    }

    /**
     * 对消息进行签名
     */
    private void signature() {
        this.signature = StringUtils.signature(this.timestamp.toString(), this.nonce);
    }
}
