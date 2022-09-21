package fr.gameurduxvi.meneliaapi;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {
	public JSONObject JSONOpener(String fileName) {
		try {
			Object obj = new JSONParser().parse(new FileReader(fileName));
			JSONObject jo = (JSONObject) obj;
			
			return jo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject JSONParser(String JSONString) {
		try {
			Object obj = new JSONParser().parse(JSONString);
			JSONObject jo = (JSONObject) obj;
			return jo;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String JSONReader(JSONObject JSONObject, String line) {
		return JSONObject.get(line).toString();
	}
	
	@SuppressWarnings("unchecked")
	public String JSONMapReaderToString(JSONObject JSONObject, String line) {
		try {
			Map<String, String> jo = ((Map<String, String>)JSONObject);
			
			Iterator<Entry<String, String>> itr1 = jo.entrySet().iterator(); 
			while (itr1.hasNext()) {
				Entry<String, String> pair = itr1.next();
				if(pair.getKey().equals(line)) {
					return pair.getValue();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Integer JSONMapReaderToInteger(JSONObject JSONObject, String line) {
		try {
			Map<String, Long> jo = ((Map<String, Long>)JSONObject);
			
			Iterator<Entry<String, Long>> itr1 = jo.entrySet().iterator(); 
			while (itr1.hasNext()) {
				Entry<String, Long> pair = itr1.next();
				if(pair.getKey().equals(line)) {
					return (int) (double) pair.getValue();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Long JSONMapReaderToLong(JSONObject JSONObject, String line) {
		try {
			Map<String, Long> jo = ((Map<String, Long>)JSONObject);
			
			Iterator<Entry<String, Long>> itr1 = jo.entrySet().iterator(); 
			while (itr1.hasNext()) {
				Entry<String, Long> pair = itr1.next();
				if(pair.getKey().equals(line)) {
					return pair.getValue();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JSONObject> JSONArrayReaderToJSONOject(Object Object) {	
		ArrayList<JSONObject> list = new ArrayList<>();
		JSONArray ar = (JSONArray) Object;
		Iterator<JSONObject> iterator = ar.iterator();
		while(iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> JSONArrayReaderToString(Object Object) {	
		ArrayList<String> list = new ArrayList<>();
		JSONArray ar = (JSONArray) Object;
		Iterator<String> iterator = ar.iterator();
		while(iterator.hasNext()) {
			list.add(iterator.next().toString());
		}
		return list;
	}
}
