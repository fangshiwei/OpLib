package com.oprisklib.util.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class CacheManager {
	 private static HashMap<String, Object> cacheMap = new HashMap<String, Object>(); 
	  
	    private CacheManager() { 
	        super(); 
	    } 
 
	    public static boolean getSimpleFlag(String key){ 
	        try{ 
	            return (Boolean) cacheMap.get(key); 
	        }catch(NullPointerException e){ 
	            return false; 
	        } 
	    } 
	    public static long getServerStartdt(String key){ 
	        try { 
	            return (Long)cacheMap.get(key); 
	        } catch (Exception ex) { 
	            return 0; 
	        } 
	    } 
 
	    public synchronized static boolean setSimpleFlag(String key,boolean flag){ 
	        if (flag && getSimpleFlag(key)) { 
	            return false; 
	        }else{ 
	            cacheMap.put(key, flag); 
	            return true; 
	        } 
	    } 
	    public synchronized static boolean setSimpleFlag(String key,long serverbegrundt){ 
	        if (cacheMap.get(key) == null) { 
	            cacheMap.put(key,serverbegrundt); 
	            return true; 
	        }else{ 
	            return false; 
	        } 
	    } 
	 
	  
	    private synchronized static Cache getCache(String key) { 
	        return (Cache) cacheMap.get(key); 
	    } 

 
	    private synchronized static boolean hasCache(String key) { 
	        return cacheMap.containsKey(key); 
	    } 

 
	    public synchronized static void clearAll() { 
	        cacheMap.clear(); 
	    } 

 
	    public synchronized static void clearAll(String type) { 
	        Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
	        String key; 
	        ArrayList<String> arr = new ArrayList<String>(); 
	        try { 
	            while (i.hasNext()) { 
	                Entry<String, Object> entry = (Entry<String, Object>) i.next(); 
	                key = (String) entry.getKey(); 
	                if (key.startsWith(type)) {  
	                    arr.add(key); 
	                } 
	            } 
	            for (int k = 0; k < arr.size(); k++) { 
	                clearOnly(arr.get(k)); 
	            } 
	        } catch (Exception ex) { 
	            ex.printStackTrace(); 
	        } 
	    } 
	  
	    public synchronized static void clearOnly(String key) { 
	        cacheMap.remove(key); 
	    } 
	  
	    public synchronized static void putCache(String key, Cache obj) { 
	        cacheMap.put(key, obj); 
	    } 
	  
	    public static Cache getCacheInfo(String key) { 
	 
	        if (hasCache(key)) { 
	            Cache cache = getCache(key); 
	            if (cacheExpired(cache)) {  
	                cache.setExpired(true); 
	            } 
	            return cache; 
	        }else 
	            return null; 
	    } 
	  
	    public static void putCacheInfo(String key, Cache obj, long dt,boolean expired) { 
	        Cache cache = new Cache(); 
	        cache.setKey(key); 
	        cache.setTimeOut(dt + System.currentTimeMillis());  
	        cache.setValue(obj); 
	        cache.setExpired(expired); 
	        cacheMap.put(key, cache); 
	    } 
 
	    public static void putCacheInfo(String key,Cache obj,long dt){ 
	        Cache cache = new Cache(); 
	        cache.setKey(key); 
	        cache.setTimeOut(dt+System.currentTimeMillis()); 
	        cache.setValue(obj); 
	        cache.setExpired(false); 
	        cacheMap.put(key,cache); 
	    } 

 
	    public static boolean cacheExpired(Cache cache) { 
	        if (null == cache) { 
	            return false; 
	        } 
	        long nowDt = System.currentTimeMillis();  
	        long cacheDt = cache.getTimeOut();  
	        if (cacheDt <= 0||cacheDt>nowDt) {
	            return false; 
	        } else {  
	            return true; 
	        } 
	    } 
	 
	     
	    public static int getCacheSize() { 
	        return cacheMap.size(); 
	    } 
	 
	     
	    public static int getCacheSize(String type) { 
	        int k = 0; 
	        Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
	        String key; 
	        try { 
	            while (i.hasNext()) { 
	                Entry<String, Object> entry = (Entry<String, Object>) i.next(); 
	                key = (String) entry.getKey(); 
	                if (key.indexOf(type) != -1) {  
	                    k++; 
	                } 
	            } 
	        } catch (Exception ex) { 
	            ex.printStackTrace(); 
	        } 
	 
	        return k; 
	    } 

 
	    @SuppressWarnings("finally")
		public static ArrayList<String> getCacheAllkey() { 
	        ArrayList<String> a = new ArrayList<String>(); 
	        try { 
	            Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
	            while (i.hasNext()) { 
	                Entry<String, Object> entry = (Entry<String, Object>) i.next(); 
	                a.add((String) entry.getKey()); 
	            } 
	        } catch (Exception ex) {} 
	        finally { 
	            return a; 
	        } 
	    } 

 
	    @SuppressWarnings("finally")
		public static ArrayList<String> getCacheListkey(String type) { 
	        ArrayList<String> a = new ArrayList<String>(); 
	        String key; 
	        try { 
	            Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
	            while (i.hasNext()) { 
	                Entry<String, Object> entry = (Entry<String, Object>) i.next(); 
	                key = (String) entry.getKey(); 
	                if (key.indexOf(type) != -1) { 
	                    a.add(key); 
	                } 
	            } 
	        } catch (Exception ex) {} 
	        finally { 
	            return a; 
	        } 
	    } 
}
