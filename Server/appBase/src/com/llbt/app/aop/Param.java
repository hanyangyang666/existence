package com.llbt.app.aop;

import java.lang.annotation.Annotation;  
/** 
 * 方法参数类 
 * @author 邓重阳
 * @date 2016年12月28日 
 */  
public class Param {  
	/**简单名字**/ 
    private String simpleName; 
    /**名字**/ 
    private String name;
    /**类型**/  
    private Class<?> type; 
    /**值**/  
    private Object value; 
    /**注解**/  
    private Annotation anno; 
      
    public Param() {  
        super();  
    }  
      
    public Param(String simpleName,String name, Class<?> type, Object value, Annotation anno) {  
        super();  
        this.simpleName = simpleName;  
        this.name = name;  
        this.type = type;  
        this.value = value;  
        this.anno = anno;  
    }  
  
    public String getName() {  
        return name;  
    }  
      
    public void setName(String name) {  
        this.name = name;  
    }  
      
    public Class<?> getType() {  
        return type;  
    }  
      
    public void setType(Class<?> type) {  
        this.type = type;  
    }  
      
    public Object getValue() {  
        return value;  
    }  
      
    public void setValue(Object value) {  
        this.value = value;  
    }  
      
    public Annotation getAnno() {  
        return anno;  
    }  
  
    public void setAnno(Annotation anno) {  
        this.anno = anno;  
    }  
  
    public String getSimpleName() {  
        return simpleName;  
    }  
  
    public void setSimpleName(String simpleName) {  
        this.simpleName = simpleName;  
    }  
  
    @Override  
    public String toString() {  
        return "Param [simpleName=" + simpleName + ", name=" + name + ", type=" + type + ", value=" + value + ", anno="  
                + anno + "]";  
    }  
}  