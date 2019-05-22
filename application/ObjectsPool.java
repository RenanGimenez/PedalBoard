package application;

import java.util.HashMap;

public class ObjectsPool {
	
	private static ObjectsPool instance;
	private HashMap<String,Object> pool;
	
	private ObjectsPool() {
		pool = new HashMap<>();
	}
	
	public static ObjectsPool getInstance() {
		if(instance == null)
			instance = new ObjectsPool();
		return instance;
	}
	
	public void put(String id, Object obj) {
		pool.put(id, obj);
	}
	
	public Object get(String id) {
		return pool.get(id);
	}
}
