//package com.caox.valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.converter.HttpMessageConversionException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.List;
//
///**
// * @author : nazi
// * @version : 1.0
// * @date : 2019/9/4 9:01
// */
//@RestControllerAdvice
//public class TreasurerExceptionHandler {
//    private static Logger LOGGER = LoggerFactory.getLogger(TreasurerExceptionHandler.class);
//
//    public TreasurerExceptionHandler() {
//    }
//    /**
//     * 校验错误拦截处理
//     * 处理{@link Valid} & {@link NotNull}
//     *
//     * @param exception 错误信息集合
//     * @return 错误信息
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public DataResult validationBodyException(MethodArgumentNotValidException exception) {
//        BindingResult result = exception.getBindingResult();
//        if (result.hasErrors()) {
//            List<ObjectError> errors = result.getAllErrors();
//            errors.forEach(p -> {
//                FieldError fieldError = (FieldError) p;
//                LOGGER.warn("Data check failure : object={}, field={}, errorMessage={}",
//                        fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
//            });
//            return DataResult.faild(TreasurerErrorCode.PARAM_MISSING.getErrorCode(),
//                    result.getFieldError() == null ? "请求参数有误" : result.getFieldError().getDefaultMessage());
//        }
//        //其他错误
//        return DataResult.faild(TreasurerErrorCode.OTHER_ERROR.getErrorCode(), TreasurerErrorCode.OTHER_ERROR.getErrorReason());
//    }
//
//    /**
//     * 参数类型转换错误 {@link HttpMessageConversionException}
//     *
//     * @param exception 错误
//     * @return          错误信息
//     */
//    @ExceptionHandler(HttpMessageConversionException.class)
//    public DataResult parameterTypeException(HttpMessageConversionException exception) {
//        LOGGER.warn("parameterTypeException {}", exception.getCause().getLocalizedMessage());
//        return DataResult.faild(TreasurerErrorCode.PARAM_MISSING.getErrorCode(), "请求参数类型有误");
//    }
//}
