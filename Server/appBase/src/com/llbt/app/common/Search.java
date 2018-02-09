/* Copyright 2009 The Revere Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.llbt.app.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.llbt.app.common.Page.Sort;
import com.mysql.jdbc.Field;
import com.sun.net.httpserver.Filter;

/**
 * A convenient fully-featured implementation of ISearch and IMutableSearch for
 * general use in Java code.
 * 
 * @author dwolverton
 */
public class Search implements IMutableSearch, Serializable {

    private static final long serialVersionUID = 1L;

    protected int firstResult = -1; // -1 stands for unspecified

    protected int maxResults = -1; // -1 stands for unspecified

    /**
     * 页码从0开始、-1表示未定义
     */
    protected int page = -1; // -1 stands for unspecified

    protected Class<?> searchClass;

    protected List<Filter> filters = new ArrayList<Filter>();

    protected boolean disjunction;

    protected List<Sort> sorts = new ArrayList<Sort>();

    protected List<Field> fields = new ArrayList<Field>();

    protected boolean distinct;

    protected List<String> fetches = new ArrayList<String>();

    protected int resultMode = RESULT_AUTO;

    protected boolean cacheable = true;

    public Search() {

    }

    public Search(Class<?> searchClass) {
        this.searchClass = searchClass;
    }

    public Search setSearchClass(Class<?> searchClass) {
        this.searchClass = searchClass;
        return this;
    }

    public Class<?> getSearchClass() {
        return searchClass;
    }

	@Override
	public int getFirstResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxResults() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Filter> getFilters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisjunction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Sort> getSorts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Field> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDistinct() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getFetches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getResultMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getCacheable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IMutableSearch setFirstResult(int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setMaxResults(int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setPage(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setFilters(List<Filter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setDisjunction(boolean disjunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setSorts(List<Sort> sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setFields(List<Field> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setDistinct(boolean distinct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setFetches(List<String> fetches) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMutableSearch setResultMode(int resultMode) {
		// TODO Auto-generated method stub
		return null;
	}


}
