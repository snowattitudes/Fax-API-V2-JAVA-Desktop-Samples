package FaxAPI_JavaConsole;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.json.*;
import javax.json.JsonArray;
import javax.json.JsonObject;

import com.google.gson.*;

public class FaxOperations {
	public void SendFaxSimpleModel() throws UnsupportedEncodingException{
		String charset = "UTF-8";
       
        AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(ACCESS_TOKEN != null){
        	
        	String aouthRequestHeaderValue = ACCESS_TOKEN;
        	String senderName = "Japano JAVAConsoleFax";
			String senderCompanyName = "Japan JAVAGroup Org";
			String faxSubject = "Japaneze Send Fax Simple Model with JAVA Console";
			String faxNotes = "Japaneze Send Fax Simple Model with JAVA Console";

			String recipientName = "OFX";
			String faxNumber = "1(720)4039716";

			String queryString = "senderName=" + senderName + "&senderCompanyName=" + senderCompanyName + "&faxSubject=" + faxSubject + "&faxNotes=" + faxNotes + "&recipientName=" + recipientName + "&recipientFaxNo=" + faxNumber;
			String encodedQueryString = URLEncoder.encode(queryString, "UTF-8");
			encodedQueryString = encodedQueryString.replace("%26", "&");
			encodedQueryString = encodedQueryString.replace("%3D", "=");//if c# it will be %3d
			encodedQueryString = encodedQueryString.replace("+", "%20");
			String FaxUrl = "https://api.onlinefaxes.com/v2/fax/async/sendfax/simplemodel?" + encodedQueryString;
			System.out.println("SERVER FaxUrl: " + FaxUrl);
			try {
				//get file type here http://www.freeformatter.com/mime-types-list.html
				File uploadFile1 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//SampleFaxDoc.pdf");
				String uploadFileType1 = "application/pdf";
		        File uploadFile2 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//TestFaxFromJapan.txt");
		        String uploadFileType2 = "text/plain";
		        
				MultipartUploadUtility multipartUpload = new MultipartUploadUtility(FaxUrl, aouthRequestHeaderValue, charset);
				
				multipartUpload.addFilePart("fileUpload", uploadFile1, uploadFileType1);
				multipartUpload.addFilePart("fileUpload", uploadFile2, uploadFileType2);
				
				System.out.println("SERVER File1: " + uploadFile1.getAbsolutePath());
				System.out.println("SERVER File2: " + uploadFile2.getAbsolutePath());
				System.out.println("SERVER File2 Canonical: " + uploadFile2.getCanonicalPath());
	 
	            List<String> response = multipartUpload.finish();
	             
	            System.out.println("SERVER REPLIED:");
	             
	            for (String line : response) {
	                System.out.println(line);
	            }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
   
        
	}
	
	public void SendFaxComplexModel() throws UnsupportedEncodingException{
		String charset = "UTF-8";
	
        AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(ACCESS_TOKEN != null){
        	
        	String aouthRequestHeaderValue = ACCESS_TOKEN;
        	
        	SenderDetail senderDtl = new SenderDetail();
        	
        	senderDtl.Id = 0;
        	senderDtl.Name = "Bulgaria JAVAConsoleFax";
        	senderDtl.Company = "Bulgaria JAVAGroup Org";
        	senderDtl.Subject = "Bulgaria Fax Complex Model with JAVA Console";
        	senderDtl.Notes = "Bulgaria Fax Complex Model with JAVA Console";
        	
        	       	
        	RecipientDetail recDtl_1 = new RecipientDetail();
        	RecipientDetail recDtl_2 = new RecipientDetail();

        	recDtl_1.Name = "OFX";
        	recDtl_1.FaxNumber = "1(720)4039716";
        	
        	recDtl_2.Name = "Google";
        	recDtl_2.FaxNumber = "1(720)4039716";
        	
        	List<RecipientDetail> recpArray = new ArrayList<RecipientDetail>();
        	recpArray.add(recDtl_1);
        	recpArray.add(recDtl_2);

        	
        	Gson gson = new Gson();
        	String jsonSenderDetail = gson.toJson(senderDtl);
        	String jsonRecipientList = gson.toJson(recpArray);
        	System.out.println("SERVER SenderDetail: " + jsonSenderDetail);
        	System.out.println("SERVER RecipientList: " + jsonRecipientList);
			
			String FaxUrl = "https://api.onlinefaxes.com/v2/fax/async/sendfax/complexmodel";
			System.out.println("SERVER FaxUrl: " + FaxUrl);
			try {
				//get file type here http://www.freeformatter.com/mime-types-list.html
				File uploadFile1 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//SampleFaxDoc.docx");
		        String uploadFileType1 = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		        File uploadFile2 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//TestFaxFromBulgaria.txt");
		        String uploadFileType2 = "text/plain";
		        
				MultipartUploadUtility multipartUpload = new MultipartUploadUtility(FaxUrl, aouthRequestHeaderValue, charset);
				
				multipartUpload.addJsonField("SenderDetail", jsonSenderDetail);
				multipartUpload.addJsonField("RecipientList", jsonRecipientList);
				
				
				multipartUpload.addFilePart("fileUpload", uploadFile1, uploadFileType1);
				multipartUpload.addFilePart("fileUpload", uploadFile2, uploadFileType2);
	            
				System.out.println("SERVER File1: " + uploadFile1.getAbsolutePath());
				System.out.println("SERVER File2: " + uploadFile2.getAbsolutePath());
				
	            List<String> response = multipartUpload.finish();
	             
	            System.out.println("SERVER REPLIED:");
	             
	            for (String line : response) {
	                System.out.println(line);
	            }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	
	public void GetFaxStatus(){
		
		String oauthHeader;
		String FAX_ID = "208121775329";
		String urlParameters  = FAX_ID;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String baseUrl        = "https://api.onlinefaxes.com/v2";
		String faxUrl 		  = baseUrl + "/fax/async/getfaxstatus?faxId=" + urlParameters;
		
		AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(ACCESS_TOKEN !=null){
        	
        	oauthHeader = ACCESS_TOKEN;
        	URL url = null;
    		HttpURLConnection conn;
    		try {
    			
    			url = new URL( faxUrl );
    			conn = (HttpURLConnection) url.openConnection();
    			conn.setDoOutput( true );
    			conn.setInstanceFollowRedirects( false );
    			conn.setRequestMethod( "POST" );
    			conn.setRequestProperty("Authorization", "ofx " + oauthHeader);
    			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
    			conn.setRequestProperty( "charset", "utf-8");
    			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
    			conn.setUseCaches( false );
    			DataOutputStream wr = new DataOutputStream( conn.getOutputStream()); 
    		
    			wr.write( postData );
    			wr.close();
    			//Get Response  
    			InputStream is = conn.getInputStream();
    			System.out.println("Input Stream:" + is.toString());
    			JsonReader rdr = Json.createReader(is);
    			JsonObject obj = rdr.readObject();
    			String response = obj.getString("Status");
    			System.out.println("Status:" + response);
    		}catch (MalformedURLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (ProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else {
        	System.out.println("Error Getting Access Token");
        }
		
		
	}
	
	public void GetFaxDetail(){
		
		String oauthHeader;
		String FAX_ID = "208122688772";
		String urlParameters  = FAX_ID;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String baseUrl        = "https://api.onlinefaxes.com/v2";
		String faxUrl 		  = baseUrl + "/fax/async/getfaxdetail?faxId=" + urlParameters;
		
		AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(ACCESS_TOKEN !=null){
        	
        	oauthHeader = ACCESS_TOKEN;
        	URL url = null;
    		HttpURLConnection conn;
    		try {
    			
    			url = new URL( faxUrl );
    			conn = (HttpURLConnection) url.openConnection();
    			conn.setDoOutput( true );
    			conn.setInstanceFollowRedirects( false );
    			conn.setRequestMethod( "POST" );
    			conn.setRequestProperty("Authorization", "ofx " + oauthHeader);
    			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
    			conn.setRequestProperty( "charset", "utf-8");
    			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
    			conn.setUseCaches( false );
    			DataOutputStream wr = new DataOutputStream( conn.getOutputStream()); 
    		
    			wr.write( postData );
    			wr.close();
    			//Get Response  
    			InputStream is = conn.getInputStream();
    			System.out.println("Input Stream:" + is.toString());
    			JsonReader rdr = Json.createReader(is);
    			JsonArray results = rdr.readArray();
    			
    			for (JsonObject response : results.getValuesAs(JsonObject.class)){
    				System.out.println("Attempt:");
    				System.out.println(response.getInt("Attempt"));
    				System.out.println("\n Elapsed Time: ");
    				System.out.println(response.getString("ElapsedTime" ));
    				System.out.println("\n Pages: ");
    				System.out.println(response.getString("Pages" ));
    				System.out.println("\n Date Time: ");
    				System.out.println(response.getString("DateTime" ));
    				System.out.println("\n Status: ");
    				System.out.println(response.getString("Reason" ));
    				System.out.println("-----------");
    			}
    			
    		}catch (MalformedURLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (ProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else {
        	System.out.println("Error Getting Access Token");
        }
	}
	
	public void DownloadFax(){
		
		String oauthHeader;
		String FAX_ID = "208121775329";
		String urlParameters  = FAX_ID;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String baseUrl        = "https://api.onlinefaxes.com/v2";
		String faxUrl 		  = baseUrl + "/fax/async/downloadfaxfile?faxId=" + urlParameters;
		
		AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(ACCESS_TOKEN !=null){
        	
        	oauthHeader = ACCESS_TOKEN;
			URL url = null;
			HttpURLConnection conn;
			try {
				url = new URL( faxUrl );
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				conn.setRequestProperty("Authorization", "ofx " + oauthHeader);
				conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				DataOutputStream wr = new DataOutputStream( conn.getOutputStream()); 
			
				wr.write( postData );
				wr.close();
				//Get Response  
    			InputStream is = conn.getInputStream();
    			System.out.println("Input Stream:" + is.toString());
    			JsonReader rdr = Json.createReader(is);
    			JsonObject obj = rdr.readObject();
    			String response = obj.getString("Status");
    			System.out.println("Status:" + response);
			  
			}catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	public void DeleteFax(){
		
		String oauthHeader;
		String FAX_ID = "208121761779";
		String urlParameters  = FAX_ID;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String baseUrl        = "https://api.onlinefaxes.com/v2";
		String faxUrl 		  = baseUrl + "/fax/async/deletefax?faxId=" + urlParameters;
		
		AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(ACCESS_TOKEN !=null){
        	
        	oauthHeader = ACCESS_TOKEN;
        	URL url = null;
    		HttpURLConnection conn;
    		try {
    			
    			url = new URL( faxUrl );
    			conn = (HttpURLConnection) url.openConnection();
    			conn.setDoOutput( true );
    			conn.setInstanceFollowRedirects( false );
    			conn.setRequestMethod( "POST" );
    			conn.setRequestProperty( "Accept", "*/*"); 
    			conn.setRequestProperty("Authorization", "ofx " + oauthHeader);
    			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
    			
    			conn.setRequestProperty( "charset", "utf-8");
    			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
    			conn.setUseCaches( false );
    			DataOutputStream wr = new DataOutputStream( conn.getOutputStream()); 
    		
    			wr.write( postData );
    			wr.close();
    			//Get Response  
    			InputStream is = conn.getInputStream();
    			System.out.println("Input Stream:" + is.toString());
    			JsonReader rdr = Json.createReader(is);
    			JsonObject obj = rdr.readObject();
    			String response = obj.getString("Status");
    			System.out.println("Status:" + response);
    		}catch (MalformedURLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (ProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else {
        	System.out.println("Error Getting Access Token");
        }
		
	}
	
	public void GetFaxList(){
		
		String oauthHeader;
		String FOLDER_ID = "1007"; //deleted list (fewer items for demo)
		Boolean IS_DOWNLOADED = false;
		String urlParameters  = "folderId=" + FOLDER_ID + "&isdownloaded=" + IS_DOWNLOADED;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String baseUrl        = "https://api.onlinefaxes.com/v2";
		String faxUrl 		  = baseUrl + "/fax/async/getfaxlist?" + urlParameters;
		
		AccessToken accessToken = new AccessToken();
        String getTokenUrl = "https://api.onlinefaxes.com/v2/oauth2/token";
        String ACCESS_TOKEN = null;
        try {
			ACCESS_TOKEN = accessToken.GetAccessToken(getTokenUrl);
			System.out.println("SERVER ACCESS_TOKEN: " + ACCESS_TOKEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(ACCESS_TOKEN !=null){
        	
        	oauthHeader = ACCESS_TOKEN;
        	URL url = null;
    		HttpURLConnection conn;
    		try {
    			
    			url = new URL( faxUrl );
    			conn = (HttpURLConnection) url.openConnection();
    			conn.setDoOutput( true );
    			conn.setInstanceFollowRedirects( false );
    			conn.setRequestMethod( "POST" );
    			conn.setRequestProperty("Authorization", "ofx " + oauthHeader);
    			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
    			conn.setRequestProperty( "charset", "utf-8");
    			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
    			conn.setUseCaches( false );
    			DataOutputStream wr = new DataOutputStream( conn.getOutputStream()); 
    		
    			wr.write( postData );
    			wr.close();
    			//Get Response  
    			InputStream is = conn.getInputStream();
    			System.out.println("Input Stream:" + is.toString());
    			JsonReader rdr = Json.createReader(is);
    			JsonArray results = rdr.readArray();
    			
    			for (JsonObject response : results.getValuesAs(JsonObject.class)){
    				System.out.println(response.getInt("ItemCount"));
    				System.out.println(response.getInt("RowNum" ));
    				System.out.println(response.getString("UserMsgId" ));
    				System.out.println(response.getInt("MsgNo" ));
    				System.out.println(response.getInt("FolderId" ));
    				System.out.println(response.getString("CreatedDate" ));
    				System.out.println(response.getString("SenderName" ));
    				System.out.println(response.getBoolean("IsRead" ));
    				System.out.println(response.getString("Subject" ));
    				System.out.println(response.getString("FullPath" ));
    				System.out.println(response.getString("RecpName" ));
    				System.out.println(response.getString("RecpAddress" ));
    				System.out.println(response.getString("MsgStatus" ));
    				System.out.println("-----------");
    			}
    		}catch (MalformedURLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (ProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else {
        	System.out.println("Error Getting Access Token");
        }
		
		
	}
	
}
		
		