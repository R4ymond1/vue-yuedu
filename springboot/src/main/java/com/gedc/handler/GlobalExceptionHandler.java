package com.gedc.handler;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gedc.constant.CommonConstant;
import com.gedc.constant.ErrorId;
import com.gedc.exception.DataValidationException;
import com.gedc.exception.InvalidParameterException;
import com.gedc.response.ResponseResult;
import com.gedc.util.ResponseResultUtil;

/**
 * グローバルエラーハンドリング処理.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class.getName());

    /**
     * 共通レスポンスユーティリティ.
     */
    @Autowired
    private ResponseResultUtil responseResultUtil;
    /**
     * メッセージリソース.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * アプリケーションのバージョン.
     */
    @Value("${spring.application.version}")
    private String appVersion;

    /**
     * パラメーター不正エラーのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult<Void> invalidParameterExceptionHandler(HttpServletResponse response,
            InvalidParameterException ex) {
        setLogPattern(ErrorId.MSGE008.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                messageSource.getMessage("MSGE008", null, null));
        logger.warn(ex.getMessage());
        return responseResultUtil.getErrorResponseResult(CommonConstant.ACE0002_INVALID_PARAMETER);
    }

    /**
     * リクエストボディーが必要時の空白/nullエラーのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult<Void> httpMessageNotReadableExceptionHandler(HttpServletResponse response,
            HttpMessageNotReadableException ex) {
        setLogPattern(ErrorId.MSGE008.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                messageSource.getMessage("MSGE008", null, null));
        logger.warn(ex.getMessage());
        return responseResultUtil.getErrorResponseResult(CommonConstant.ACE0002_INVALID_PARAMETER);
    }

    /**
     * 外部APIをコールする時xxxエラーのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Void> restClientExceptionHandler(HttpServletResponse response, RestClientException ex) {
        if (ex instanceof HttpClientErrorException) {
            String[] messageParams = { "404 not found, required error, validation error" };
            setLogPattern(ErrorId.MSGE001.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                    messageSource.getMessage("MSGE001", messageParams, null));
            logger.fatal(ex.toString(), ex);
            return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
        } else {
            String[] messageParams = { "outside 404 not found, required error, validation error" };
            setLogPattern(ErrorId.MSGE005.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                    messageSource.getMessage("MSGE001", messageParams, null));
            logger.error(ex.toString(), ex);
            return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
        }

    }

    /**
     * データバリデーションエラーのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(DataValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Void> dataValidationExceptionHandler(HttpServletResponse response,
            DataValidationException ex) {
        String[] messageParams = { "outside the data range" };
        setLogPattern(ErrorId.MSGE007.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                messageSource.getMessage("MSGE001", messageParams, null));
        logger.error(ex.getMessage(), ex);
        return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
    }

    /**
     * ハンドラ無し(間違いurl等)のエラーのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseResult<Void> noHandlerFoundExceptionHandler(HttpServletResponse response,
            NoHandlerFoundException ex) {
        // ログアプリケーションのバージョン、を設定する
        ThreadContext.put("uuid", UUID.randomUUID().toString());
        ThreadContext.put("X-AppVer", appVersion);
        setLogPattern(ErrorId.MSGE011.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                messageSource.getMessage("MSGE011", null, null));
        logger.error(ex.getMessage(), ex);
        return responseResultUtil.getErrorResponseResult(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
    }

    /**
     * SQLのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Void> dataAccessExceptionHandler(HttpServletResponse response, DataAccessException ex) {
        if (ex instanceof DuplicateKeyException) {
            String[] messageParams = { "the duplicateKey error" };
            setLogPattern(ErrorId.MSGE006.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                    messageSource.getMessage("MSGE004", messageParams, null));
            logger.error(ex.getMessage(), ex);
            return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
        } else if (ex instanceof QueryTimeoutException) {
            String[] messageParams = { "outside the duplicateKey error" };
            setLogPattern(ErrorId.MSGE004.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                    messageSource.getMessage("MSGE004", messageParams, null));
            logger.fatal(ex.getMessage(), ex);
            return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
        } else {
            String[] messageParams = { "outside the duplicateKey error" };
            setLogPattern(ErrorId.MSGE004.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                    messageSource.getMessage("MSGE004", messageParams, null));
            logger.fatal(ex.getMessage(), ex);
            return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
        }
    }

    /**
     * エラーのハンドリング.
     *
     * @param response レスポンス
     * @param ex       ハンドリングエラー
     * @return 戻り値
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Void> exceptionHandler(HttpServletResponse response, Exception ex) {
        setLogPattern(ErrorId.MSGE012.getErrorId(), ex.getMessage(), ex.getClass().getName(),
                messageSource.getMessage("MSGE012", null, null));
        logger.error(ex.getMessage(), ex);
        return responseResultUtil.getErrorResponseResult(CommonConstant.ACE9999_SYSTEM_FAILED);
    }

    /**
     * ログ印刷情報を設定する.
     *
     * @param errorId       エラーID
     * @param exceptionMsg  例外メッセージ
     * @param exception     例外型名
     * @param detailedValue 自由領域(数値)
     */
    private void setLogPattern(String errorId, String exceptionMsg, String exception, String detailedValue) {
        ThreadContext.put("X-ErrorId", errorId);
        ThreadContext.put("X-ExceptionMsg", exceptionMsg);
        ThreadContext.put("X-Exception", exception);
        ThreadContext.put("X-DetailedValue", detailedValue);
    }

}
