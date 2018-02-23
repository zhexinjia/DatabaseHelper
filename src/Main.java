import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.*;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
	
	public static void main(String[] args) {
		
		
		
		SQLrequest request = new SQLrequest();
		System.out.println(request.getUser(1));
		/*
		ArrayList<HashMap<String, String>> output = request.getUserList(1);
		
		for(HashMap<String, String> item:output) {
			Set<String> keyset = item.keySet();
			for (String key:keyset) {
				System.out.println(key);
				System.out.println(item.get(key));
			}
		}
		*/
		
		
		
		
		
		
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
