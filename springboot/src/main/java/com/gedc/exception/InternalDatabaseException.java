/*
 * Copyright (c) 2018 micware Ltd.
 */

package com.gedc.exception;

/**
 * DB操作不正（ERRORレベル）のエラー.
 */
public class InternalDatabaseException extends BaseException {

    // serialVersionUID
    private static final long serialVersionUID = 1887181619527245246L;

    /**
     * コンストラクタ.
     */
    public InternalDatabaseException() {
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     */
    public InternalDatabaseException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージのフォーマット
     * @param args    パラメーター
     */
    public InternalDatabaseException(String message, Object... args) {
        super(message, args);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     * @param cause   内部エラー
     */
    public InternalDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ.
     *
     * @param cause 内部エラー
     */
    public InternalDatabaseException(Throwable cause) {
        super(cause);
    }

}
