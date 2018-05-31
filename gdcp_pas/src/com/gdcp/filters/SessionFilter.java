package com.gdcp.filters;

/**
 * @author 刘伯睿   2015-03-13  
 * @see 验证是否登录的过滤器 ， 通过sesion中是否含有UserVO 来判断
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

    public SessionFilter() {
        filterConfig = null;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        String url = req.getRequestURI();
        if (session.getAttribute("userVO") != null) {
            chain.doFilter(request, response);
        } else {
            if (url.indexOf("login.jsp") != -1 || url.indexOf("2.jsp") != -1 || url.indexOf("login.action") != -1
                    || url.endsWith(".jpg") || url.toLowerCase().endsWith(".gif") || url.toLowerCase().endsWith(".css")
                    || url.toLowerCase().endsWith(".js") || url.toLowerCase().endsWith(".jpeg")) {
                chain.doFilter(request, response);
            } else {
                // res.getOutputStream().print("<script>alert(" + "aa" +
                // ")</script>");
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }

        }

    }

    public void destroy() {
        encoding = null;
        filterConfig = null;
    }

    protected String encoding;
    protected FilterConfig filterConfig;
}
