
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//FIXME:should we store test scores, answers in json format?


/*
 * https://stackoverflow.com/questions/35840169/parse-json-without-object-name-in-java
 * JSON example:
 * 
 * {
 * 	"users":[
 * 		{"id": "1", "name":"ben", "department":"icu","level":"N0"},
 * 		{"id": "2", "name":"benjamin", "department":"icu","level":"N0"},
 * 		{"id": "3", "name":"sam", "department":"icu","level":"N0"},
 * 	]
 * }
 * 
 */
public class SQLrequest {
	String urlGet = "http://zhexinj.cn/API/getdb.php";
	String urlSend = "http://zhexinj.cn/API/sendPost.php";
	JSONParser parser = new JSONParser();
	public SQLrequest() {
	}
	
	/*
	 * @param: hospital's id
	 * @return: all user's personal information(ArrayList of String array)
	 * exam:
	 * id   name   department   level   hometown
	 * 1		ben		icu			N1		Eugene
	 * 2		jim		oicu			N0		Portland
	 */

	//以下所有是get
	
	//返回所有护士档案
	public ArrayList<Map<String, String>> getUserList(int hospitalID){
		String sql = "sql=select * from nurse where hosID = " + hospitalID +";";
		String output = sendGet(urlGet, sql);
		return jsonToList(output);
	}
	
	//返回所有科室
	public ArrayList<Map<String, String>> getDepartmentList(int hospitalID){
		return null;
	}
	
	//所有试卷
	public ArrayList<Map<String, String>> getTestList(int hosID){
		return null;
	}
	//所有已经发布试卷
	
	//所有学习
	
	//所有已经发布学习
	
	//所有培训
	
	//所有会议？？怎么实现的
	
	//会议记录？？实现？
	
	//test-history
	//参加本次考试所有护士的成绩及答案score, test result
	//
	public ArrayList<Map<String, String>> getExamScores(int hosID, int testID){
		return null;
	}
	
	//所有用户学习信息，param：hosID，testID
	
	//所有用户培训信息
	
	//会议信息
	
	//考核信息
	
	//所有用户积分
	
	//排班表
	public ArrayList<Map<String, String>> getSchedule(int hosID, int departmentID, String date){
		return null;
	}
	
	//以下所有是send,个别function需要分开写方便insert，update多个
	//可以只保留arrayList的多个insert，update
	
	//单个insert sql statement
	public String insertSQL(int hospitalID, Map<String, String> map) {
		return null;
	}
	//单个insert
	public boolean addNewUser(Map<String, String> map, int hospitalID) {
		String sql = insertSQL(hospitalID, map);
		return this.sendPost(urlSend, sql);
	}
	//多个insert
	public boolean addUsers(int hosID, ArrayList<Map<String, String>> list) {
		String sql = "";
		for(int i = 0; i<list.size(); i++) {
			sql+=insertSQL(hosID, list.get(i));
		}
		return this.sendPost(urlSend, sql);
	}
	
	
	
	
	
	
	
	
	//convert json array to arraylist
	private ArrayList<Map<String, String>> jsonToList(String json){
		ArrayList<Map<String, String>> output = new ArrayList<Map<String, String>>();
		JSONArray objectArray = new JSONArray();
		try {
			objectArray = (JSONArray) parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<?> arrayIterator = objectArray.iterator();
		while (arrayIterator.hasNext()) {
			Object object = arrayIterator.next();
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>)object;
			output.add(map);
		}
		return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//FIXME: security issue in future
	/*
	 * @param: sql statement
	 * return: boolean value check send success
	 */
	public Boolean sendPost(String url, String param) {
		String result = "";
        BufferedReader in = null;
        byte[] postData = param.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        try {
            URL realUrl = new URL(url);
            //打开和URL之间的连接            
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoOutput( true );
            connection.setInstanceFollowRedirects( false );
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            connection.setRequestProperty( "charset", "utf-8");
            connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            connection.setUseCaches( false );
            connection.connect();
            
            try( DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            	   wr.write( postData );
            	}
            
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            
            String line;
            
            while ((line = in.readLine()) != null) {
                result += line;
            }
            
            
            //result = in.readLine();
        } catch (Exception e) {
        		//FIXME: add POPUP
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        
        //使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (result.equals("success")) {
        		return true;
        }
        return false;
	}
	
	/*
	 * @param: sql statement
	 * @return: JSON formated string from executing SQL statement
	 */
	public String sendGet(String url, String param) {
		String result = "";
        BufferedReader in = null;
        byte[] postData = param.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        try {
            URL realUrl = new URL(url);
            //打开和URL之间的连接            
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoOutput( true );
            connection.setInstanceFollowRedirects( false );
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            connection.setRequestProperty( "charset", "utf-8");
            connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            connection.setUseCaches( false );
            connection.connect();
            
            try( DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            	   wr.write( postData );
            	}
            
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            
            String line;
            
            while ((line = in.readLine()) != null) {
                result += line;
            }
            
            //result = in.readLine();
        } catch (Exception e) {
        		//FIXME: add POPUP
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        
        //使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	

}
