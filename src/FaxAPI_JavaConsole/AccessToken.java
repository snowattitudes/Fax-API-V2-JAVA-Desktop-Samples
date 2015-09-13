package FaxAPI_JavaConsole;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.json.*;

public class AccessToken
{
	private String access_token;
	public  String getaccess_token()
	{
		return access_token;
	}
	public  void setaccess_token(String value)
	{
		access_token = value;
	}
	private String token_type;
	public  String gettoken_type()
	{
		return token_type;
	}
	public  void settoken_type(String value)
	{
		token_type = value;
	}
	private int expires_in;
	public  int getexpires_in()
	{
		return expires_in;
	}
	public  void setexpires_in(int value)
	{
		expires_in = value;
	}

	public String GetAccessToken(String getTokenUrl) throws IOException{
		String tokenUrl = getTokenUrl;
		Properties props = new Properties();
        FileInputStream fis;
		try {
			fis = new FileInputStream("C:\\Users\\Snow Attitudes\\EclipseWorkspace\\Fax-API-V2-Java-Desktop-Samples\\src\\resource\\properties.xml");
			//loading properties from properties file
	        try {
				props.loadFromXML(fis);
			} catch (InvalidPropertiesFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        

		String CLIENT_ID = props.getProperty("client_id");
		String CLIENT_SECRET = props.getProperty("client_secret");
		String requestBody = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&grant_type=client_credentials";
		String urlParameters  = requestBody;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = tokenUrl;
		URL url = null;
		try {
			url = new URL( request );
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
		try{
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			conn.setUseCaches( false );
			DataOutputStream wr = new DataOutputStream( conn.getOutputStream()); 
		
			wr.write( postData );
			wr.close();
			//Get Response  
			InputStream is = conn.getInputStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			String response = obj.getString("access_token");
			return response;
		   
		}	
		catch (Exception e) {
		    e.printStackTrace();
		    return null;
		} 
		finally {
		    if(conn != null) {
		    	conn.disconnect(); 
		    }
		}
	}
	
}
