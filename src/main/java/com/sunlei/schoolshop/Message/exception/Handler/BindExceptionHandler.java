//package com.sunlei.schoolshop.Message.exception.Handler;
//
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@ControllerAdvice
//public class BindExceptionHandler {
//    private static final Logger LOGGER = LoggerFactory.getLogger(BindExceptionHandler.class);
//
//    /**
//     * 定义参数异常处理器.
//     *
//     * @param e 当前平台异常参数对象.
//     * @return org.springframework.http.ResponseEntity
//     */
//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Map<String, Object>> validateErrorHandler(BindException e) {
//        Map<String, Object> errorMap = new HashMap<>(2);
//        BindingResult bindingResult = e.getBindingResult();
//        if (bindingResult.hasErrors()) {
//            List<FieldError> errorList = bindingResult.getFieldErrors();
//            String errorMsg = "[字段:" + errorList.get(0).getField() + "]错误，原因:" + errorList.get(0).getDefaultMessage();
//            errorMap.put("message", errorMsg);
//            errorMap.put("code", "0010");
//            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
//        }
//        LOGGER.error("[服务] - [捕获参数异常。异常信息:{}]", JSON.toJSONString(bindingResult));
//        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
//    }
//}
