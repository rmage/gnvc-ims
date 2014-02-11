

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Util {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static class Pair<K, V>{
		public K first;
		public V second;
		public Pair(K k, V v) {
			first = k;
			second = v;
		}
	}
    
    public static <A, B> Map<A, B> asMap(Pair<A, B>... e){
    	HashMap<A, B> map = new HashMap<A, B>();
    	for(Pair<A, B> i:e){
    		map.put(i.first, i.second);
    	}
    	return map;
    }
    
    public static <K, V> Pair<K, V> p(K i, V i2){
    	return new Pair<K, V>(i, i2);
    }
    
	static <T> Object fill(Class<T> f, String name) {
		Random r = new Random();
		if(f == Boolean.class){
			return r.nextBoolean();
		}else if(f == String.class){
			return name+" "+r.nextInt(1000);
		}else if(f == Double.class){
			return r.nextInt(1000)+r.nextDouble();
		}else if(f == Double.class){
			return r.nextInt(1000)*r.nextDouble();
		}else if(f == Date.class){
			return new Date(r.nextLong());
		}else if(f == int.class || f == Integer.class){
			return r.nextInt(1000);
		} else if(!f.isInterface()){
			try{
				T item = f.newInstance();
				for(Field f1:f.getDeclaredFields()){
					if(!Modifier.isStatic(f.getModifiers())){
						if(!f1.isAccessible()) f1.setAccessible(true);
						if(f1.get(item) == null)	f1.set(item, fill(f1.getType(), f.getName()));
					}
				}
				return item;
			}catch(Exception e){}
		} else {
			Map<String, Object> result = new HashMap<String, Object>();
			for(Method m:f.getDeclaredMethods()){
				if(m.getName().startsWith("get")){
					result.put(m.getName().substring(3), fill(m.getReturnType(), m.getName().substring(3)));
				}
			}
			return result;
		}
		return null;
	}

	public static <T> List<T> createList(Class<T> cls, int size){
		ArrayList<T> list = new ArrayList<T>();
		try{
			for(int x = 0; x< size; ++x){
				T item = cls.newInstance();
				for(Field f:cls.getDeclaredFields()){
					if(!Modifier.isStatic(f.getModifiers())){
						if(!f.isAccessible()) f.setAccessible(true);
						if(f.get(item) == null)	f.set(item, fill(f.getType(), f.getName()));
					}
				}
				list.add(item);
			}
		}catch(Exception e){}
		return list;
	}

	public static <T> List<Map<String, Object>> createList2(Class<T> cls, int size){
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			for(int x = 0; x< size; ++x){
				list.add(createMap(cls));
			}
		}catch(Exception e){}
		return list;
	}
	
	
	public static Map<String, Object> createMap(Class<?> cls){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			for(Method m:cls.getDeclaredMethods()){
				if(m.getName().startsWith("get")){
					result.put(m.getName().substring(3), fill(m.getReturnType(), m.getName().substring(3)));
				}
			}
			for(Field f:cls.getDeclaredFields()){
				if(!Modifier.isStatic(f.getModifiers())){
					if(!f.isAccessible()) f.setAccessible(true);
					result.put(f.getName(), fill(f.getType(), f.getName()));
				}
			}
		}catch(Exception e){}
		return result;
	}
	

	public static Double Sum(List<Map<String, Object>> list, String type){
		Double result = 0.0;
		for(Map<String, Object> i:list){
			if((""+i.get("type")).startsWith(type))	result += Double.valueOf(i.get("data").toString());
		}
		System.out.println("SUM:"+type+":"+list.size()+":"+result);
		return result!=0?result:null;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> SumType(List<Map<String, Object>> list){
		Map<String, Object> temp = new HashMap<String, Object>();
		for(Map<String, Object> i:list){
			String t = ""+i.get("type");
			Double sum = (Double) temp.get(t);
			if(sum == null)	sum = 0.0;
			sum += Double.valueOf(""+i.get("data"));
			temp.put(t, sum);
			
			t = (""+i.get("type")).split(" ")[0];
			sum = (Double) temp.get(t);
			if(sum == null)	sum = 0.0;
			sum += Double.valueOf(""+i.get("data"));
			temp.put(t, sum);
		}
		System.out.println("SUM:"+temp);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Entry<String, Object> m:temp.entrySet()){
			result.add(asMap(p("type", (Object) m.getKey()), p("data", m.getValue())));
		}
		return result;
	}

	public static Double Sum(List<Map<String, Object>> list, String type, String data_name){
		Double result = 0.0;
		for(Map<String, Object> i:list){
			if((""+i.get("type")).startsWith(type))	result += Double.valueOf(i.get(data_name).toString());
		}
		System.out.println("SUM:"+data_name+":"+type+":"+list.size()+":"+result);
		return result!=0?result:null;
	}
    
    public static Double IF(List<Map<String, Object>> list, String type, String data_name){
		for(Map<String, Object> i:list){
			if((""+i.get("type")).startsWith(type))	return Double.valueOf(i.get(data_name).toString());
		}
		return 0.0;
	}

	public static String date(){
		return new SimpleDateFormat("MMM d, yyyy").format(new Date());
	}
	
	public static String date(String format){
		return new SimpleDateFormat(format).format(new Date());
	}

	public static String date(String date, String format){
		Date d = new Date();
		try {d = inputDateFormat.parse(date);
		} catch (Exception e) {
			try {d = dateFormat.parse(date);
			} catch(Exception f){}
		}
		SimpleDateFormat f2 = new SimpleDateFormat(format);
		return f2.format(d);
	}
}
