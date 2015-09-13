package FaxAPI_JavaConsole;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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
        	String senderName = "SeeSharpConsoleFax";
			String senderCompanyName = "DotNetGroup Org";
			String faxSubject = "Send Fax Simple Model with CSharp Console";
			String faxNotes = "Send Fax Simple Model with CSharp Console";

			String recipientName = "OFX";
			String faxNumber = "1(720)4039716";

			String queryString = "senderName=" + senderName + "&senderCompanyName=" + senderCompanyName + "&faxSubject=" + faxSubject + "&faxNotes=" + faxNotes + "&recipientName=" + recipientName + "&recipientFaxNo=" + faxNumber;
			String encodedQueryString = URLEncoder.encode(queryString, "UTF-8");
			encodedQueryString = encodedQueryString.replace("%26", "&");
			encodedQueryString = encodedQueryString.replace("%3d", "=");
			encodedQueryString = encodedQueryString.replace("+", "%20");
			String FaxUrl = "https://api.onlinefaxes.com/v2/fax/async/sendfax/simplemodel?" + encodedQueryString;
			try {
				MultipartUploadUtility multipartUpload = new MultipartUploadUtility(FaxUrl, aouthRequestHeaderValue, charset);
				multipartUpload.addFilePart("fileUpload", uploadFile1);
				multipartUpload.addFilePart("fileUpload", uploadFile2);
	 
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
