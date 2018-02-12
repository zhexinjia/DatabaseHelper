
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


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
	public String getUserList(int hospitalID){
		String sql = "sql=select * from nurse where hosID = " + hospitalID +";";
		String output = sendGet(urlGet, sql);
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
