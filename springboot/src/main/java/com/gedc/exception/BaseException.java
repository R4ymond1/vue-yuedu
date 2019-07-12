/*
 * Copyright (c) 2018 micware Ltd.
 */

package com.gedc.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ベースエラー.
 */
public class BaseException extends RuntimeException {

    // serialVersionUID
    private static final long serialVersionUID = -6760146919971565029L;

    /**
     * コンストラクタ.
     */
    public BaseException() {
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージのフォーマット
     * @param args    パラメーター
     */
    public BaseException(String message, Object... args) {
        super(createMessage(message, args));
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     * @param cause   内部エラー
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ.
     *
     * @param cause 内部エラー
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * メッセージフォーマットにプレースホルダはパラメーター値を書き換える処理.
     *
     * @param message メッセージフォーマット
     * @param args    パラメーター
     * @return String 変換後のメッセージ
     */
    private static String createMessage(String message, Object... args) {
        StringBuilder sb = new StringBuilder(message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (Object arg : args) {
                sb.append(objectMapper.writeValueAsString(arg));
            }
        } catch (JsonProcessingException e) {
            // do nothing
        }

        return sb.toString();
    }
}
