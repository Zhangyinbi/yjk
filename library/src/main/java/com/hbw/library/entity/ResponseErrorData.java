package com.hbw.library.entity;

import java.io.Serializable;

/**
 * Created by Youjk on 2015/10/13.
 */
public class ResponseErrorData implements Serializable {
    public String status;
    public String error_code;
    public Messages message;

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

    public class Messages implements Serializable {

        private String error_code;
        private String msg;

        public Messages() {
        }

        @Override
        public String toString() {
            return "Messages{" +
                    "error_code='" + error_code + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
