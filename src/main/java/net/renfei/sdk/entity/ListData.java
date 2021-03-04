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
     * 总页数
     */
    private Integer totalPages;

    /**
     * 显示行数
     */
    private Integer showRows;

    /**
     * 扩展内容
     */
    private Object extra;

    public ListData() {
    }

    public ListData(List<T> data) {
        this(data, null, null, null, null);
    }

    public ListData(Long total, Integer currentPage, Integer showRows) {
        this(total, currentPage, showRows, null);
    }

    public ListData(Long total, Integer currentPage, Integer showRows, Integer totalPages) {
        this(null, total, currentPage, showRows, totalPages);
    }

    public ListData(List<T> data, Long total, Integer currentPage, Integer showRows, Integer totalPages) {
        this(data, total, currentPage, showRows, totalPages, null);
    }

    public ListData(List<T> data, Long total, Integer currentPage, Integer showRows, Integer totalPages, Object extra) {
        this.data = data;
        this.currentPage = currentPage;
        this.showRows = showRows;
        this.totalPages = totalPages;
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

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
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
