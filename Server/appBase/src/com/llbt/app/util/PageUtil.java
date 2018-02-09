package com.llbt.app.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class PageUtil {
	
	/**
	 * 分页公用方法
	 * @param listSize	数据数量
	 * @param currentPage	当前页数,从1开始计数，为0零默认为1（开始第一页）
	 * @param pageItems		每页显示数量，为0零默认为全部
	 * @return
	 */
	public static List<Element> page(List<Element> element,int currentPage,int pageItems){
		List<Element> map = new ArrayList<Element>();
		pageItems = pageItems==0?element.size():pageItems;
		currentPage=currentPage==0?1:currentPage;
		int pagei = (currentPage-1)*pageItems;	//当前页起始号
		int pagelg = pagei+pageItems-1;//当前页结束号
		pagelg=pagelg>=element.size()?element.size()-1:pagelg;
		for(int i=pagei;i<=pagelg;i++){
			Element ele = element.get(i);
			map.add(ele);
		}
		return map;
	}
}
