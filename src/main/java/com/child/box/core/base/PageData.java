package com.child.box.core.base;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

public class PageData extends HashMap implements Map,Serializable {

	private static final long serialVersionUID = 1L;

	Map<Object, Object> map = null;
	HttpServletRequest request;

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if(name.startsWith("param['")&&name.endsWith("']")) {
				continue;
			}
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				value = "";
				for (int i = 0; i < values.length; i++) {
					value = value + values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}
	
	

	public PageData() {
		map = new HashMap();
	}
	
	public void setApiUpdDef(){
		  map.put("updated_at", new Timestamp(System.currentTimeMillis()));
		 
	    }
	
	
	  public void setUpdDef(Long id){
		
		      map.put("update_user", id);
		  setApiUpdDef();
	    }
	    
    public void setAddDef(Long id){
    	
			 map.put("create_user", id);
		setApiAddDef();
    }
    
    public void setApiAddDef(){
    	 map.put("created_at", new Timestamp(System.currentTimeMillis()));
    	 map.put("updated_at", map.get("created_at"));
    	 map.put("status", ApiCons.STATUS_SUC);
    }
	

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	
	public void apiFormatString(){  
		
		Iterator<Entry<Object, Object>> entries = this.map.entrySet().iterator();
		  
		while (entries.hasNext()) {  
		  
		    Entry<Object, Object> entry = entries.next();
		  
		     if(entry.getValue()==null){
		    	 put(entry.getKey(), "");
		     }else{
		    	 put(entry.getKey(), String.valueOf(entry.getValue()));
		     }
		}  
	}
	
	
      public JSONObject apiFormatJson(){  
		
		Iterator<Entry<Object, Object>> entries = this.map.entrySet().iterator();
		JSONObject json = new JSONObject();
		while (entries.hasNext()) {  
		  
		    Entry<Object, Object> entry = entries.next();
		  
		     if(entry.getValue()==null){
		    	 json.put(String.valueOf(entry.getKey()), "");
		     }else{
		    	 json.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
		     }
		}  
		
		return json;
	}

	public String getString(Object key) {
		if(get(key)==null){
			return "";
		}
		return String.valueOf(get(key));
	}
	
	public Long getLong(Object key) {
		if(get(key)==null){
			return null;
		}
		return Long.valueOf(getString(key));
	}
	public Integer getInt(Object key) {
		if(get(key)==null){
			return null;
		}
		return Integer.valueOf(getString(key));
	}
	public <T> List<T> getList(Object key, Class<T> clazz) {
		return (List<T>) get(key);
	}
	public <T> T[] getArray(Object key, Class<T> clazz) {
		
		return (T[]) get(key);
	}
	public List<PageData> getPageDataList(Object key) {
		return (List<PageData>) get(key);
	}
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	public PageData set(Object key, Object value) {
		put(key, value);
		return this;
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}
	@Override
	public void clear() {
		map.clear();
	}
	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}
	@Override
	public Set entrySet() {
		return map.entrySet();
	}
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}
	@Override
	public Set keySet() {
		return map.keySet();
	}
	@Override
	public void putAll(Map t) {
		map.putAll(t);
	}
	@Override
	public int size() {
		return map.size();
	}
	@Override
	public Collection values() {
		return map.values();
	}

	public Map<Object, Object> getMap() {
		return map;
	}

	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Integer) {
			Integer id = (Integer) obj;
			if(id.equals(this.getInt("id"))) {
				return true;
			}
		}
		if (this == obj){
			return true;
		}
		if (!super.equals(obj)){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		PageData other = (PageData) obj;
		if (map == null) {
			if (other.map != null){
				return false;}
		} else if (!map.equals(other.map)){
			return false;}
		if (request == null) {
			if (other.request != null){
				return false;}
		} else if (!request.equals(other.request)){
			return false;}
		return true;
	}
	
	
	
	
}
