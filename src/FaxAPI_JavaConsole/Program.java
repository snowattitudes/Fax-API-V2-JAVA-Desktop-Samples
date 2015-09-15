package FaxAPI_JavaConsole;

import java.io.UnsupportedEncodingException;
import java.util.*;
public class Program {

	public static void main(String[] args) {
		FaxOperations faxSimpleModel = new FaxOperations();
		FaxOperations faxCompleModel = new FaxOperations();

		/*System.out.println("Sending Fax Simple Model ...");
		try {
			faxSimpleModel.SendFaxSimpleModel();
		} catch (UnsupportedEncodingException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter any key to Send Fax Complex Model");
		//new Scanner(System.in).nextLine();
		System.out.println("Sending Fax Complex Model ...");
		try {
			faxCompleModel.SendFaxComplexModel();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println("Getting Fax Status using faxId of 208121774837 ...");
		faxCompleModel.GetFaxStatus();
		
		System.out.println("Getting Fax Detail using faxId of 208121774837 ...");
		faxCompleModel.GetFaxDetail();
		
		System.out.println("Get Download URL for faxId of 208121774837 ...");
		faxCompleModel.DownloadFax();
		
		System.out.println("Delete Fax for faxId of 208121513082 ...");
		faxCompleModel.DeleteFax();
		
		
	}

}
