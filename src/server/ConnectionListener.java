package server;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import common.Event;



public class ConnectionListener implements Runnable{
	
	ServerSocket serverSocket;
	public HashMap<String,Subscription> subscribers;
	public LinkedList<Event> toBePublished;
	
	ConnectionListener(Integer portNumber, HashMap<String,Subscription> s, LinkedList<Event> toBePublished) throws IOException{
		this.serverSocket = new ServerSocket(portNumber);
		this.subscribers = s;
		this.toBePublished = toBePublished;
		System.out.println("Notice: toBePublished hashCode from ConnectionListener class is " + this.toBePublished.hashCode() );
	}

	@Override
	public void run() {
		System.out.println("ConnectionListener is running on port " + this.serverSocket.getLocalPort());
		// TODO Auto-generated method stub
		while (true){
			try {
				Socket s = this.serverSocket.accept();
				SocketAddress remote = s.getRemoteSocketAddress();
				System.out.println("accepted connection from: " + remote.toString());
				// get inputstream...
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				common.Event event = (common.Event)ois.readObject();
				if(event.getPayload() == null){
					System.out.println("This is a subscription.");
					Subscription sb = new Subscription(event.getArgument(),s);
					addSubscriber(sb);
				} else{
					System.out.println("This is a publication.");
					synchronized(toBePublished){
						toBePublished.add(event);
						toBePublished.notify();
					}		
				}
				System.out.println("toBePublished: " + this.toBePublished);
				System.out.println("subscriber: " + this.subscribers);
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void addSubscriber(Subscription s){
		subscribers.put(s.getArgument(), s);
	}
	
	
}
