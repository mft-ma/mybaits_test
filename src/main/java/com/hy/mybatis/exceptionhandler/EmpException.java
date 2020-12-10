package com.hy.mybatis.exceptionhandler;

public class EmpException extends Exception {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmpException(){}

    public EmpException(String message){
        this.message=message;
    }

}
