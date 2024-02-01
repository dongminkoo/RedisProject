package com.personal.redisProject.utils;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_MEMEBER_ID = "LOGIN_MEMEBER_ID";
    private static final String LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID";
    private SessionUtil(){

    }
    public static String getLoginMemberId(HttpSession session){
        return (String) session.getAttribute(LOGIN_MEMEBER_ID);
    }
    public static String getLoginAdminId(HttpSession session){
        return (String) session.getAttribute(LOGIN_ADMIN_ID);
    }
    public static void setLoginMemberId(HttpSession session, String id){
        session.setAttribute(LOGIN_MEMEBER_ID, id);
    }
    public static void setLoginAdminId(HttpSession session, String id){
        session.setAttribute(LOGIN_ADMIN_ID, id);
    }
    public static void clear(HttpSession session){
        session.invalidate();
    }
}
