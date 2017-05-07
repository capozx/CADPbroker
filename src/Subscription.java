
public class Subscription {
	private String argument;
	private String ipSubscriber;
	
	public String getArgument(){
		return this.argument;
	}
	
	public String getipSubscriber(){
		return this.ipSubscriber;
	}
	
	public Subscription(String a, String i){
		this.argument = a;
		this.ipSubscriber = i;
	}
}
