package com.sunlei.schoolshop.Message.enums;

/**
 * @author 孙磊
 */

public enum  CommErrorCodeAndMsg {

        Network_error("9999","网络错误");

        private String code;
        private String msg;
        CommErrorCodeAndMsg(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }


    }


