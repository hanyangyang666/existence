package com.llbt.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@SuppressWarnings("unchecked")
public class XmlUtil {
	
	public static Map<String,String> xmlToMap(String xml,String elementName) throws Exception{
		
		System.out.println("2级节点========="+elementName);
		
		Map<String,String> map=new HashMap<String,String>();
		Document doc=DocumentHelper.parseText(xml);
		Element element=doc.getRootElement().element("PerAccValiAns").element(elementName);
		if(element!=null){
			List<Element> list=element.elements();
			for(int i=0;i<list.size();i++){
				Element e=list.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		Element inf=doc.getRootElement().element("PerAccValiAns").element("RtnResInf");
		if(inf!=null){
			List<Element> list=inf.elements();
			for(int i=0;i<list.size();i++){
				Element e=list.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		
		return map;
	}
	
	public static Map<String,String> xmlToList(String xml,String elementName) throws Exception{
		
		System.out.println("2级节点========="+elementName);
		
		Map<String,String> map=new HashMap<String,String>();
		Document doc=DocumentHelper.parseText(xml);
		Element element=doc.getRootElement().element(elementName);
		if(element!=null){
			List<Element> list=element.elements();
			for(int i=0;i<list.size();i++){
				Element e=list.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		Element inf=doc.getRootElement().element("RtnResInf");
		if(inf!=null){
			List<Element> list=inf.elements();
			for(int i=0;i<list.size();i++){
				Element e=list.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		
		return map;
	}
	
	
public static Map<String,String> xmlToMap2(String xml,String elementName) throws Exception{
		
		System.out.println("2级节点========="+elementName);
		
		Map<String,String> map=new HashMap<String,String>();
		Document doc=DocumentHelper.parseText(xml);
		Element element=doc.getRootElement().element("PerAccVali").element(elementName);
		if(element!=null){
			List<Element> list=element.elements();
			for(int i=0;i<list.size();i++){
				Element e=list.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		Element inf=doc.getRootElement().element("PerAccVali").element("RtnResInf");
		if(inf!=null){
			List<Element> list=inf.elements();
			for(int i=0;i<list.size();i++){
				Element e=list.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <PerAccValiAns> <PerAccInfo> <AccNum>20735085694 </AccNum> <PerAccState>8</PerAccState> <MonPaySum>1,643.28</MonPaySum> <UnitProp>0.06</UnitProp> <IndiProp>0.06</IndiProp> <AccBankCode>02</AccBankCode> <LastDrawAmt>0</LastDrawAmt> <LastBusiAmt>0</LastBusiAmt> <LastBusiMonth>2011-11-01</LastBusiMonth> <LastDrawDate>1899-12-31</LastDrawDate> <BaseNumber>13,694</BaseNumber> <Balance>1,368.35</Balance> <UnitAccNum>1000122111  </UnitAccNum> <UnitAccName>广东瀚诚律师事务所                                                              </UnitAccName> <CardStat>2</CardStat> <CardChange>1</CardChange> </PerAccInfo> <RtnResInf> <RtnCd>I0000</RtnCd> <RtnInf>处理成功</RtnInf> </RtnResInf> </PerAccValiAns>";
		
		Map<String,String> map=XmlUtil.xmlToMap(xml,"PerAccInfo");
		
		System.out.println(map);
	}
}
