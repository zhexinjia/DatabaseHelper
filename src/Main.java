import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.*;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {
	
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException {
		String[] fields = {"姓名", "电话", "备注"};
		String[] keys = {"name", "phone", "comment"};
		String path = "/Users/Ben/Desktop/write.xlsx";
		try {
			FTPtool.sshSftp("101.200.39.52", "zhexinj", "Aaaxtt123321", 22);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		ArrayList<HashMap<String, String>> temp = null;
		try {
			temp = ExcelHelper.readXLSXFile("/Users/Ben/Desktop/test.xlsx", new String[]{"name", "phone", "comment"});
			for (HashMap<String, String> map:temp) {
				//System.out.println(map.entrySet().toString());
				Set<String> keyset = map.keySet();
				for(String key:keyset) {
					System.out.print(key + ": ");
					System.out.print(map.get(key) + "   ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ExcelHelper.writeXLSXFile(temp, fields, keys, path);
		*/
		
		

		

	}
}
