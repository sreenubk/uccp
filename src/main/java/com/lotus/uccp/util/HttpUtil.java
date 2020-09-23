package com.lotus.uccp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpUtil {

    public static String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            System.out.println("Session is null");
        } else {
            System.out.println("Session is Found ::-Username" + session.getAttribute("Username"));
            return (String) session.getAttribute("Username");
        }
        return null;
    }
}
