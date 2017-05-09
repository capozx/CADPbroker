package common;
import java.io.Serializable;

public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7747255928388532605L;
	private String argument;
	private String payload;
	
	public String getPayload(){
		return this.payload;
	}
	
	public String getArgument(){
		return this.argument;
	}
	
	
	public Event( String a, String p){
		this.argument = a;
		this.payload = p;
	}
	
	public Event(String a){
		this.argument = a;
	}
}
