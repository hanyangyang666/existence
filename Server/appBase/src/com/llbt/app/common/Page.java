package com.llbt.app.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.google.common.collect.Lists;

/**
 * 分页查询结果封装.
 * 
 * @param <T>
 *            Page中记录的类型.
 * 
 * @author 邓重阳
 */
public class Page<T> implements Iterable<T> {
	/**
	 * 起始页
	 */
	protected int startRow = 0;
	/**
	 * 结束页
	 */
	protected int endRow = 10;
	/**
	 * 当前页码
	 */
	protected int pageNo = 1;
	/**
	 * 分页大小
	 */
	protected int pageSize = 10;

	protected boolean countTotal = true;

	protected String orderBy = null;
	protected String orderDir = null;
	/**
	 * 总页数
	 */
	private int totalPage = 1;
	/**
	 * 总记录数
	 */
	protected int totalCount = -1;
    /**
     * 分页数据
     */
	protected List<T> result = null;

	public Page() {
	}

	public Page(SearchCondition searchCondition, int totalCount) {
		this(searchCondition.getPageNumber(), searchCondition.getPageSize(), totalCount);
	}

	public Page(int pageNumber, int pageSize, int totalCount) {
		this(pageNumber, pageSize, totalCount, new ArrayList<T>(0));
	}

	public Page(int pageNumber, int pageSize, int totalCount, List<T> result) {
		if (pageSize < 0)
			throw new IllegalArgumentException("[pageSize] must great or equal than zero");
		this.pageSize = pageSize;
		//this.pageNo = PageUtils.computePageNumber(pageNumber, pageSize, totalCount);
		this.pageNo = pageNumber;
		//this.totalCount = totalCount;
		setTotalCount(totalCount);
		setResult(result);
	}

	/**
	 * 获得当前页的页号, 序号从1开始, 默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号, 序号从1开始, 低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
		// 根据pageNo计算_startRow和_endRow
		this.endRow = this.pageNo * this.pageSize;
		this.startRow = this.endRow - this.pageSize + 1;
	}

	/**
	 * 获得每页的记录数量, 默认为10.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量, 低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 获得排序字段, 无默认值. 多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段, 多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获得排序方向, 无默认值.
	 */
	public String getOrderDir() {
		return orderDir;
	}

	/**
	 * 是否默认计算总记录数.
	 */
	public boolean isCountTotal() {
		return countTotal;
	}

	/**
	 * 设置是否默认计算总记录数.
	 */
	public void setCountTotal(boolean countTotal) {
		this.countTotal = countTotal;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param orderDir
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrderDir(final String orderDir) {
		String lowcaseOrderDir = StringUtils.lowerCase(orderDir);

		// 检查order字符串的合法值
		String[] orderDirs = StringUtils.split(lowcaseOrderDir, ',');
		for (String orderDirStr : orderDirs) {
			if (!StringUtils.equals(Sort.DESC, orderDirStr) && !StringUtils.equals(Sort.ASC, orderDirStr)) {
				throw new IllegalArgumentException("排序方向" + orderDirStr + "不是合法值");
			}
		}

		this.orderDir = lowcaseOrderDir;
	}

	/**
	 * 获得排序参数.
	 */
	public List<Sort> getSort() {
		String[] orderBys = StringUtils.split(orderBy, ',');
		String[] orderDirs = StringUtils.split(orderDir, ',');
		Validate.isTrue(orderBys.length == orderDirs.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

		List<Sort> orders = Lists.newArrayList();
		for (int i = 0; i < orderBys.length; i++) {
			orders.add(new Sort(orderBys[i], orderDirs[i]));
		}

		return orders;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(orderDir));
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始.
	 */
	public int getOffset() {
		return ((pageNo - 1) * pageSize);
	}

	public static class Sort {
		public static final String ASC = "asc";
		public static final String DESC = "desc";

		private final String property;
		private final String dir;

		public Sort(String property, String dir) {
			this.property = property;
			this.dir = dir;
		}

		public String getProperty() {
			return property;
		}

		public String getDir() {
			return dir;
		}
	}

	/**
	 * @return startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return endRow
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	/**
	 * @return totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * get totalitems
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * set totalitems
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		// 根据总记录数、分页大小 计算页数 1：利用整除特性 方法 2： this.totalPage = (int)
		// Math.ceil((double) this.totalRecord / this.pageSize);
		this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
	}

	/**
	 * 获取当前页的首条数据的行编码
	 * 
	 * @return 当前页的首条数据的行编码
	 */
	public int getThisPageFirstElementNumber() {
		return getPageNo() * getPageSize() + 1;
	}

	/**
	 * 获取当前页的末条数据的行编码
	 * 
	 * @return 当前页的末条数据的行编码
	 */
	public int getThisPageLastElementNumber() {
		int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
		return getPageNo() < fullPage ? getTotalCount() : fullPage;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 实现Iterable接口, 可以for(Object item : page)遍历使用
	 */
	@Override
	public Iterator<T> iterator() {
		return result.iterator();
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean hasNextPage() {
		return (getPageNo()  < getTotalPage());
	}

	/**
	 * 是否最后一页.
	 */
	public boolean isLastPage() {
		return !hasNextPage();
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (hasNextPage()) {
			return getPageNo() + 1;
		} else {
			return getPageNo();
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean hasPrePage() {
		return (getPageNo() > 1);
	}

	/**
	 * 是否第一页.
	 */
	public boolean isFirstPage() {
		return !hasPrePage();
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (hasPrePage()) {
			return getPageNo() - 1;
		} else {
			return getPageNo();
		}
	}

	/**
	 * 获取最后一页页码，也就是总页数
	 * 
	 * @return 最后一页页码
	 */
	public int getLastPageNumber() {
		//return PageUtils.computeLastPageNumber(totalCount, pageSize);
		return getTotalPage();
	}

	/**
	 * 计算以当前页为中心的页面列表,如"首页,23,24,25,26,27,末页"
	 * 
	 * @param count
	 *            需要计算的列表大小
	 * @return pageNo列表
	 */
	public List<Integer> getSlider(int count) {
		int halfSize = count / 2;
		int totalPage = getTotalPage();

		int startPageNo = Math.max(getPageNo() - halfSize, 1);
		int endPageNo = Math.min(startPageNo + count - 1, totalPage);

		if (endPageNo - startPageNo < count) {
			startPageNo = Math.max(endPageNo - count, 1);
		}

		List<Integer> result = Lists.newArrayList();
		for (int i = startPageNo; i <= endPageNo; i++) {
			result.add(i);
		}
		return result;
	}

	/**
	 * 得到数据库的第一条记录号
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return PageUtils.getFirstResult(pageNo, pageSize);
	}
}
