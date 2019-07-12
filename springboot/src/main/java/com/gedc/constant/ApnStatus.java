package com.gedc.constant;

/**
 * APN状態の定数定義.
 */
public interface ApnStatus {

    /**
     * {@code 0 停止} .
     */
    public static final int STOP = 0;

    /**
     * {@code 1 開始要求} .
     */
    public static final int START_REQUEST = 1;

    /**
     * {@code 2 開始} .
     */
    public static final int START = 2;

    /**
     * {@code 3 停止要求} .
     */
    public static final int STOP_REQUEST = 3;
}
