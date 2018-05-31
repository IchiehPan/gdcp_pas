package com.gdcp.common;

/**
 * @author 刘伯睿  2015-03-13 
 * @see 封装分页的数据
 */
import javax.servlet.http.HttpServletRequest;

import com.gdcp.common.StringUtil;

public class Page {

    private int pageSize;
    private int pageNo;
    private int total;

    public Page(HttpServletRequest request) {
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");

        if (StringUtil.isNullOrBlank(pageNoStr)) {
            pageNoStr = "1";
        }
        if (StringUtil.isNullOrBlank(pageSizeStr)) {
            pageSizeStr = "14";
        }
        pageSize = Integer.parseInt(pageSizeStr);
        pageNo = Integer.parseInt(pageNoStr);
    }

    public int getTotalPage() {
        int totalPage = 0;
        if (total % pageSize != 0) {
            totalPage = total / pageSize + 1;
        } else {
            totalPage = total / pageSize;
        }
        return totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
