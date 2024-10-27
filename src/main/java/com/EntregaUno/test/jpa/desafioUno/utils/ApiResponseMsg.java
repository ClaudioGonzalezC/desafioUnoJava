package com.EntregaUno.test.jpa.desafioUno.utils;

import lombok.Data;

@Data
public class ApiResponseMsg {

    private String msg;
    private Object data;

    public ApiResponseMsg(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

}