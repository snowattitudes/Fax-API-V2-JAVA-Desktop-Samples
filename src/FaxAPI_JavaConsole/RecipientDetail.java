package FaxAPI_JavaConsole;

public class RecipientDetail {
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	private String FaxNumber;
	public final String getFaxNumber()
	{
		return FaxNumber;
	}
	public final void setFaxNumber(String value)
	{
		FaxNumber = value;
	}

}
