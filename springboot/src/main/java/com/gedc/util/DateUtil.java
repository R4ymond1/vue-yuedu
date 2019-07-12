package com.gedc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日付に関するメソッドのユーティリティ.
 */
public class DateUtil {

    /**
     * 日付フォーマット（yyyyMMdd）.
     */
    public static final String DATE_FMT_YYYYMMDD_NOSIGN = "yyyyMMdd";
    /**
     * 日付フォーマット（yyyy年MM月dd日）.
     */
    public static final String DATE_FMT_YYYYMMDD_YMD = "yyyy年MM月dd日";
    /**
     * 日付フォーマット（yyyyMMddHHmmssSSS）.
     */
    public static final String DATE_FORMAT_yMdHmsS = "yyyyMMddHHmmssSSS";
    /**
     * 日付フォーマット（yyyyMMddHHmmss）.
     */
    public static final String DATE_FORMAT_yMdHms = "yyyyMMddHHmmss";
    /**
     * 日付フォーマット（yyyyMMddHHmm）.
     */
    public static final String DATE_FORMAT_yMdHm = "yyyyMMddHHmm";

    /**
     * 指定日付の表示フォーマット.
     *
     * @param date 指定日付
     * @return 変換後の日付文字列
     */
    public static String changeDate2String(final Date date, final String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 指定年月日文字列(yyyyMMdd)の日付に転換する.
     *
     * @param dateTime 指定年月日文字列(yyyyMMdd)
     * @return Date 日付
     */
    public static Date changeString2Date(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FMT_YYYYMMDD_NOSIGN);
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 指定年月日文字列の日付に転換する.
     *
     * @param dateTime 指定年月日文字列
     * @return Date 日付
     */
    public static Date changeString2Date(String dateTime, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 指定日時の文字列(yyyyMMdd)の日付の表示フォーマット.
     *
     * @param dateTime 指定年月日文字列(yyyyMMdd)
     * @return 指定日付の年月日(yyyy年MM月dd日)
     */
    public static String changeStringToYMD(String dateTime) {
        // try {
        Date date = changeString2Date(dateTime);
        return changeDate2String(date, DATE_FMT_YYYYMMDD_YMD);
        // } catch (Exception e) {
        // throw new ServiceException("指定した日時の文字列のフォーマットが不正。", dateTime);
        // }
    }

    /**
     * アプリケーションサーバー現在日時を取得する.
     *
     * @return 現在日時
     */
    public static Date getNowTime() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * 検査時間フォーマット.
     *
     * @param date   指定の日時
     * @param format 指定のフォーマット
     * @return 検査結果
     */
    public static boolean checkDateFormat(String date, String format) {
        if (date == null || format == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date parseDate = null;
        try {
            parseDate = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }

        if (!date.equals(sdf.format(parseDate))) {
            return false;
        }
        return true;
    }
}
