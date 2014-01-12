package com.report.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jett.jdbc.JDBCExecutor;
import net.sf.jett.jdbc.ResultSetRow;

public class Query{
	private String query;
	private List<Query> another;
	private String name;
	
	public Query(String name, String query){
		this.name = name;
		this.query = query;
	}
	
	public Query(Query... q){
		this.another =  Arrays.asList(q);
	}
	
	public Map<String, Object> query(JDBCExecutor jdbc, String... parameter){
		HashMap<String, Object>	result = new HashMap<String, Object>();
		if(another!= null){
			for(Query q:another){
				result.putAll(q.query(jdbc));
			}
		} else {
			try {
				List<ResultSetRow> list = jdbc.execQuery(query, (Object[]) parameter);
				result.put(name, list);
				if(!list.isEmpty()){
					ResultSetRow row = list.get(0);
					result.put("s"+name, row);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
}
