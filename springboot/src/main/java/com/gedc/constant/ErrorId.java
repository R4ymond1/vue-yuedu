package com.gedc.constant;

import org.springframework.lang.Nullable;

/**
 * エラーID.
 *
 */
public enum ErrorId {
    // vehicle error id
    MSGE001("FA001"), MSGE002("FA002"), MSGE003("FA003"), MSGE004("FA004"), MSGE005("ER001"), MSGE006("ER002"),
    MSGE007("ER003"), MSGE008("WA001"), MSGE009("WA002"), MSGE030("ER004")/* サーバー異常 */,
    // service error id
    MSGE010("WA002"), MSGE013("ER001"), MSGE014("FA001"), MSGE015("FA002"), MSGE016("ER002"), MSGE017("FA004"),
    MSGE018("ER004"), MSGE019("FA003"), MSGE020("ER003"), MSGE021("FA006"), MSGE022("ER005"), MSGE024("ER010"),
    MSGE025("FA007"), MSGE026("ER006"), MSGE027("FA008"), MSGE028("ER007"), MSGE031("ER011"),
    // common error id
    MSGE011("ER008")/* NoHandlerFoundException */, MSGE012("ER009")/* other Exception */,
    MSGE023("WA003")/* iccid is null */, MSGE029("WA004")/* recidデータ初期化時，主キー重複エラーが発生して */;

    /**
     * エラーID.
     */
    @Nullable
    private final String errorId;

    /**
     * インスタンス生成.
     */
    ErrorId() {
        this.errorId = null;
    }

    /**
     * インスタンス生成.
     *
     * @param errorId エラーID
     */
    ErrorId(String errorId) {
        this.errorId = errorId;
    }

    /**
     * エラーIDを取得する.
     *
     * @return エラーID
     */
    public String getErrorId() {
        return errorId;
    }
}
