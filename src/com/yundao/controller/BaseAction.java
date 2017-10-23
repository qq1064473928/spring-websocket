package com.yundao.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/1/19.
 */
public class BaseAction {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response){
        System.out.println( "first" );
        this.request = request;
        this.response = response;
        session = request.getSession();
    }
}
