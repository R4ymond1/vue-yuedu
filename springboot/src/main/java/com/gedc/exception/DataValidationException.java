package com.gedc.exception;

/**
 * データバリデーションエラー.
 */
public class DataValidationException extends BaseException {

    // serialVersionUID
    private static final long serialVersionUID = 4131717897234431444L;

    /**
     * コンストラクタ.
     */
    public DataValidationException() {
        super();
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージ
     */
    public DataValidationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param message エラーメッセージのフォーマット
     * @param args    パラメーター
     */
    public DataValidationException(String message, Object... args) {
        super(message, args);
    }

    /**
     * コンストラクタ.
     *
     * @param cause 内部エラー
     */
    public DataValidationException(Throwable cause) {
        super(cause);
    }
}
