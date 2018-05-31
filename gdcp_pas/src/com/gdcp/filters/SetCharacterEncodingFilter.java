package com.gdcp.filters;

/**
 * <p>Description:
 *   ��������Ϊһ��������(��Ҫ����web.xml)�� ����ÿ���ύ�Ķ��������ύ������һ�����ı���ת��
 * </p>
 * @author �����
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
