/*
 * Copyright (c) 2018 micware Ltd.
 */

package com.gedc.exception;

/**
 * サービスエラー.
 */
public class ServiceException extends BaseException {

    // serialVersionUID
    private static final long serialVersionUID = -1627976933759383743L;

    /**
     * コンストラクタ.
     */
    public ServiceException() {
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージのフォーマット
     * @param args    パラメーター
     */
    public ServiceException(String message, Object... args) {
        super(message, args);
    }

    /**
     * コンストラクタ.
     *
     * @param cause 内部エラー
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     * @param cause   内部エラー
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
