
public class Event {
	private String payload;
	private String argument;
	
	public String getPayload(){
		return this.payload;
	}
	
	public String getArgument(){
		return this.argument;
	}
	 
	public Event(String p, String a){
		this.payload = p;
		this.argument = a;
	}
}
