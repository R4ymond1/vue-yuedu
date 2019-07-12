/*
 * Copyright (c) 2018 micware Ltd.
 */

package com.gedc.exception;

/**
 * パラメーター不正エラー.
 */
public class InvalidParameterException extends BaseException {

    // serialVersionUID
    private static final long serialVersionUID = -2288263672336079928L;

    /**
     * コンストラクタ.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     */
    public InvalidParameterException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージのフォーマット
     * @param args    パラメーター
     */
    public InvalidParameterException(String message, Object... args) {
        super(message, args);
    }
}
