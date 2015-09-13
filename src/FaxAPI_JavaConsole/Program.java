package FaxAPI_JavaConsole;

import java.io.UnsupportedEncodingException;
import java.util.*;
public class Program {

	public static void main(String[] args) {
		FaxOperations faxSimpleModel = new FaxOperations();
		//FaxOperations faxCompleModel = new FaxOperations();

		System.out.println("Sending Fax Simple Model ...");
		try {
			faxSimpleModel.SendFaxSimpleModel();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter any key to Send Fax Complex Model");
		//new Scanner(System.in).nextLine();
		System.out.println("Sending Fax Complex Model ...");
		//faxCompleModel.SendFaxComplexModel();
	}

}
