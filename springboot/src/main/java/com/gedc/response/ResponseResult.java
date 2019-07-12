package com.gedc.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseResult<T> implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4435980586768118259L;

    /**
     * 異常用応答コード.
     */
    @JsonProperty(value = "error")
    private String errorCode;

    /**
     * 異常用応答メッセージ.
     */
    @JsonProperty(value = "error_description")
    private String errorMessage;

    /**
     * 応答データ.
     */
    private T data;

    public ResponseResult<T> setErrorCode(String code) {
        this.errorCode = code;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseResult<T> setErrorMessage(String message) {
        this.errorMessage = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            json = "convert error";
        }
        return json;
    }

}
