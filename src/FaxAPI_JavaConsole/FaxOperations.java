package FaxAPI_JavaConsole;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

public class FaxOperations {
	public void SendFaxSimpleModel() throws UnsupportedEncodingException{
		String charset = "UTF-8";
		
        File uploadFile1 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//SampleFaxDoc.pdf");
        File uploadFile2 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//TestFaxFromJapan.txt");
        
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
				MultipartUploadUtility multipartUpload = new MultipartUploadUtility(FaxUrl, aouthRequestHeaderValue, charset);
				
				multipartUpload.addFilePart("fileUpload", uploadFile1);
				multipartUpload.addFilePart("fileUpload", uploadFile2);
				
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
		
        File uploadFile1 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//SampleFaxDoc.docx");
        File uploadFile2 = new File("C://Users//Snow Attitudes//EclipseWorkspace//Fax-API-V2-Java-Desktop-Samples//src//resource//SampleFaxDocs//TestFaxFromBulgaria.txt");
        
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
				MultipartUploadUtility multipartUpload = new MultipartUploadUtility(FaxUrl, aouthRequestHeaderValue, charset);
				
				multipartUpload.addJsonField("SenderDetail", jsonSenderDetail);
				multipartUpload.addJsonField("RecipientList", jsonRecipientList);
				
				
				multipartUpload.addFilePart("fileUpload", uploadFile1);
				multipartUpload.addFilePart("fileUpload", uploadFile2);
	            
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
}