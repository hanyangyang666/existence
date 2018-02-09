package com.llbt.app.common;

import java.io.Serializable;
import java.util.List;



/**
 * 公共CRUD
 * 
 * @author 邓重阳
 * @version 1.0
 */
public interface MyBatisDao<E extends BaseEntity, ID extends Serializable>{

	/**
     * 依据ID查询记录
     * 
     * @param id
     * @return
     */
    public  E getById(ID id);

    /**
     * 获取所有记录
     * 
     * @return
     */
    public  List<E> getAll();

    /**
     * 依据ID删除记录
     * 
     * @param id
     * @return
     */
    public  int delete(ID id);

    /**
     * 符合条件删除记录
     * 
     * @param entity
     * @return
     */
    public  int delete(E entity);

    /**
     * 更新记录
     * 
     * @param entity
     * @return
     */
    public  int update(E entity);

    /**
     * 新增记录
     * 
     * @param entity
     * @return
     */
    public  int insert(E entity);

    /**
     * 使用SearchCondition复合条件查询记录
     * 
     * @param statementName
     * @param searchCondition
     * @return
     */
    public  List<E> search(E entity, SearchCondition searchCondition);

    /**
     * 使用Entity复合条件查询记录
     * 
     * @param statementName
     * @param searchCondition
     * @return
     */
    public  List<E> search(E entity);

    /**
     * 分页查询
     * 
     * @param statementName
     * @param searchCondition
     * @return
     */
    public Page<E> pageQuery(String statementName, SearchCondition searchCondition);

    /**
     * 依据ID集合批量删除记录
     * 
     * @param id
     * @return
     */
    public  int deleteByIds(List<ID> ids);
    
    /**
     * 按条件删除
     * 
     * @param <SC>
     * @param searchCondition
     * @return
     * @author 邓重阳
     */
    public  <SC extends SearchCondition> int deleteBySearchCondition(SC searchCondition);

    /**
     * 新增记录-返回主键
     * 
     * @param entity
     * @return 主键ID
     */
    public abstract String insertForReturnPk(E entity);
    

}