package com.gdcp.filters;

/**
 * <p>Description:
 *   本类在作为一个过滤器(需要配置web.xml)， 处理每个提交的动作，将提交内容做一个中文编码转换
 * </p>
 * @author 刘伯睿
 * @version 1.0
 */

import java.io.IOException;
import javax.servlet.*;

public class SetCharacterEncodingFilter implements Filter {

    public SetCharacterEncodingFilter() {
        encoding = "UTF-8";

        filterConfig = null;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, res);
    }

    public void destroy() {
        encoding = null;
        filterConfig = null;
    }

    protected String encoding;
    protected FilterConfig filterConfig;
}
