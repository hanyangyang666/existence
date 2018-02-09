package com.llbt.app.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * 查询条件封装VO
 * 
 * @author 邓重阳
 */
public class SearchCondition extends BaseEntity {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -7764073067458411323L;

	/**
	 * 排序标识
	 */
	public static final String ORDER_DESC = "DESC";

	/**
	 * 页号(从1开始)
	 */
	protected Integer pageNumber = Integer.valueOf(1);

	/**
	 * 记录开发位置(从0开始)
	 */
	protected Integer startIndex = Integer.valueOf(0);

	/**
	 * 页显示记录数量
	 */
	protected Integer pageSize = Integer.valueOf(10);
	/**
	 * 排序项目
	 */
	protected List<SortInfo> sortInfos = new ArrayList<SortInfo>();

	/**
	 * 最大记录数
	 */
	protected Integer maxResults = null;

	protected String sortName = null;

	protected String sortOrder = ORDER_DESC;

	/**
	 * “或”条件
	 */
	private SearchCondition orCondition = null;

	/**
	 * 开始时间。用于任意某个时间类字段的查询
	 */
	private Date startTime = null;
	/**
	 * 结束时间。用于任意某个时间类字段的查询
	 */
	private Date endTime = null;

	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * @return the sortInfos
	 */
	public List<SortInfo> getSortInfos() {
		return sortInfos;
	}

	/**
	 * @param sortInfos
	 *            the sortInfos to set
	 */
	public void setSortInfos(List<SortInfo> sortInfos) {
		this.sortInfos = sortInfos;
	}

	/**
	 * 
	 * 根据排序信息构造SQL 排序字符串<br>
	 * 
	 * @return SQL语法的排序字符串（例如：<code>order by id desc,name asc<code>）
	 */
	public String getOrderBys() {
		if (sortInfos != null && !sortInfos.isEmpty()) {
			StringBuilder orderByBuilder = new StringBuilder(" order by ");
			for (SortInfo sortInfo : sortInfos) {
				orderByBuilder.append(sortInfo.getColumnName());
				orderByBuilder.append(" ");
				orderByBuilder.append(sortInfo.getSortOrder());
				orderByBuilder.append(",");
			}
			return StringUtils.removeEnd(orderByBuilder.toString(), ",");
		}
		return StringUtils.EMPTY;
	}

	/**
	 * @return sortName
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * @param sortName
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
		SortInfo sortInfo = new SortInfo();
		sortInfo.setColumnName(sortName);
		getSortInfos().add(sortInfo);
	}

	/**
	 * @return sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the maxResults
	 */
	public Integer getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public SearchCondition getOrCondition() {
		return orCondition;
	}

	public void setOrCondition(SearchCondition orCondition) {
		this.orCondition = orCondition;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
