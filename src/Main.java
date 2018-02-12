import java.util.Iterator;
import java.util.Set;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
	
	public static void main(String[] args) {
		
		SQLrequest request = new SQLrequest();
		String output = request.getUserList(1);
		System.out.println(output);
		System.out.println("below is json");
		

		JSONParser JsonParser = new JSONParser();
		JSONArray object;
		try {
			object = (JSONArray) JsonParser.parse(output);
			Iterator iterator = object.listIterator();
			while(iterator.hasNext()){
				JSONObject aaa = (JSONObject) iterator.next();
				Set<String> key = aaa.keySet();
				Iterator<String> newIT = key.iterator();
				while(newIT.hasNext()) {
					String temp = (String) aaa.get(newIT.next());
					if(temp==null) {
						System.out.println("null");
					}else if(temp.equals("")){
						System.out.println("empty");
					}
					else {
						System.out.println(temp);
					}
					
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		    JSONArray json = (JSONArray) JsonParser.parse(str);
		    Iterator<JSONObject> iterator =  json.listiterator();

		    System.out.println("Fields:");
		    while (iterator.hasNext() ){
		       System.out.println(String.format("public String %s;", iterator.next()));
		    }

		    System.out.println("public void Parse (String str){");
		    System.out.println("JSONObject json = new JSONObject(str);");

		    iterator  = json.listIterator();
		    while (iterator.hasNext() ){
		       String key = iterator.next();
		       System.out.println(String.format("this.%s = json.getString(\"%s\");",key,key ));

		    System.out.println("}");
		    
		    */
	}
}
