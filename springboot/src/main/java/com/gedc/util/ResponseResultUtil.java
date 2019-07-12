package com.gedc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.gedc.response.ResponseResult;

/**
 * response result生成ユーティリティ.
 */
@Component
public class ResponseResultUtil {

    /**
     * メッセージリソース.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 成功レスポンスを生成する.
     *
     * @param data 表示するデータ
     * @return レスポンス応答
     */
    public <T> ResponseResult<T> getSuccessResponseResult(T data) {
        return getResponseResult(null, null, data);
    }

    /**
     * 成功レスポンスを生成する.
     *
     * @param code エラーコード
     * @return レスポンス応答
     */
    public <T> ResponseResult<T> getErrorResponseResult(String code) {
        String message = messageSource.getMessage(code, null, null);
        return getResponseResult(code, message, null);
    }

    /**
     * 成功レスポンスを生成する.
     *
     * @param code エラーコード
     * @param args エラーメッセージプレースホルダのパラメーター
     * @return レスポンス応答
     */
    public <T> ResponseResult<T> getErrorResponseResult(String code, Object[] args) {
        String message = messageSource.getMessage(code, args, null);
        return getResponseResult(code, message, null);
    }

    /**
     * 成功レスポンスを生成する.
     *
     * @param code    エラーコード
     * @param message メッセージ
     * @return レスポンス応答
     */
    public <T> ResponseResult<T> getErrorResponseResult(String code, String message) {
        return getResponseResult(code, message, null);
    }

    /**
     * 成功レスポンスを生成する.
     *
     * @param code    エラーコード
     * @param message メッセージ
     * @param data    表示データ
     * @return レスポンス応答
     */
    public <T> ResponseResult<T> getErrorResponseResult(String code, String message, T data) {
        return getResponseResult(code, message, data);
    }

    /**
     * レスポンスを生成する.
     *
     * @param code    エラーコード
     * @param message メッセージ
     * @param data    表示データ
     * @return レスポンス応答
     */
    private <T> ResponseResult<T> getResponseResult(String code, String message, T data) {
        return new ResponseResult<T>().setErrorCode(code).setErrorMessage(message).setData(data);
    }
}
