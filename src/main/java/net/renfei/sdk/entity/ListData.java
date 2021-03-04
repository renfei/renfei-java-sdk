package net.renfei.sdk.entity;

import java.util.List;

/**
 * <p>Title: ListData</p>
 * <p>Description: 分页数据</p>
 *
 * @author RenFei
 */
public final class ListData<T> {
    /**
     * 数据负载
     */
    private List<T> data;
    /**
     * 总数
     */
    private Long total;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 显示行数
     */
    private Integer showRows;

    /**
     * 扩展内容
     */
    private Object extra;

    ListData() {
    }

    ListData(List<T> data) {
        this.data = data;
    }

    ListData(long total, int currentPage, int showRows) {
        this.currentPage = currentPage;
        this.showRows = showRows;
        this.total = total;
    }

    ListData(List<T> data, long total, int currentPage, int showRows) {
        this.data = data;
        this.currentPage = currentPage;
        this.showRows = showRows;
        this.total = total;
    }

    ListData(List<T> data, long total, int currentPage, int showRows, Object extra) {
        this.data = data;
        this.currentPage = currentPage;
        this.showRows = showRows;
        this.total = total;
        this.extra = extra;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getShowRows() {
        return showRows;
    }

    public void setShowRows(Integer showRows) {
        this.showRows = showRows;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
