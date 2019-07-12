package com.gedc.constant;

/**
 * 全システム共通の定数クラス.
 */
public class CommonConstant {

    // 共通エラーコード
    /**
     * サーバーメンテナンス中です.
     */
    public static final String ACE0001_MAINTAINING = "ACE0001";

    /**
     * 指定されたパラメータが不正です.
     */
    public static final String ACE0002_INVALID_PARAMETER = "ACE0002";

    /**
     * サーバー異常が発生しました.
     */
    public static final String ACE9998_SYSTEM_FAILED = "ACE9998";

    /**
     * 予期せぬエラーが発生しました.
     */
    public static final String ACE9999_SYSTEM_FAILED = "ACE9999";

    /**
     * 指定のrecidが登録されていません.
     */
    public static final String ABE1201_RECID_NOT_EXIST = "ABE1201";

    /**
     * Recidのパラメータ名.
     */
    public static final String HEADER_KEY_RECID = "X-Recid";

    /**
     * X-recidヘッダの正規表現式.
     */
    public static final String HEADER_RECID_REGEX = "[0-9]{1,9}";

    /**
     * GB→MB、MB→KB、KB→byteの変換用.
     */
    public static final int VOLUME_CONVERSION_CAPACITY = 1024;

    /**
     * GB→MB、MB→Byteの変換用.
     */
    public static final int REMAINING_VOLUME_CONVERSION_MB_UNIT = 10;

    /**
     * 日本円の先頭表示キャラクター.
     */
    public static final String CHARACTER_JP_MONEY = "¥";

    /**
     * 体験購入可能場合の販売額表示内容.
     */
    public static final String EXPERIENCE_PRICE = CHARACTER_JP_MONEY + "0(体験購入プラン)";

    /**
     * MB容量用表示内容.
     */
    public static final String VOLUME_MB = "MB";

    /**
     * GB容量用表示内容.
     */
    public static final String VOLUME_GB = "GB";

    /**
     * TB容量用表示内容.
     */
    public static final String VOLUME_TB = "TB";

    /**
     * 販売数量単位コード: 2(GB).
     */
    public static final String SALES_QUANTITY_UNIT_GB = "2";

    /**
     * 販売数量単位コード: 3(TB).
     */
    public static final String SALES_QUANTITY_UNIT_TB = "3";

    /**
     * ソリューションID.
     */
    public static final String SOLUTION_IDS = "s-WFI001P000";
    /**
     * 不詳細なタグを返す.
     * 0：返却しない(Default）,
     * 1：返却する.
     */
    public static final int DETAILED_RETURN_FLAG = 0;
}
