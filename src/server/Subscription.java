package server;

import java.net.Socket;

public class Subscription {
	private String argument;
	private Socket socket;
	
	public String getArgument(){
		return this.argument;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public Subscription(String a, Socket s){
		this.argument = a;
		this.socket = s;
	}
}
