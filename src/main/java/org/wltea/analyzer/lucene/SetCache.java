package org.wltea.analyzer.lucene;

import java.util.HashMap;
import java.util.Map;


public class SetCache
{

	private Map<String, Object> setchache = new HashMap<>();

	public Object getSetchache(String key)
	{
		return setchache.get(key);
	}

	public void setSetchache(String key, Object value)
	{
		this.setchache.put(key, value);
	}

	private SetCache()
	{
		super();
	}
	
	private  static final SetCache Instance = new SetCache();
	
	public static SetCache GetInstance(){
		return Instance;
	}
}
