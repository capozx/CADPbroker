package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import common.Event;

public class Broker implements Runnable{
	
	public HashMap<String,Subscription> subscribers;
	public LinkedList<Event> toBePublished;
	private ArrayList<ConnectionListener> listeners;
	private ArrayList<Thread> tListeners;
	
	Broker(ArrayList<Integer> ports) throws IOException{
		this.subscribers = new HashMap<String, Subscription>();
		this.toBePublished = new LinkedList<common.Event>();
		this.listeners = new ArrayList<ConnectionListener>();
		this.tListeners = new ArrayList<Thread>();
		for (Integer port: ports){
			this.listeners.add(new ConnectionListener(port,subscribers,toBePublished));
			this.tListeners.add(new Thread(this.listeners.get(this.listeners.size() - 1)));
			tListeners.get(this.tListeners.size() - 1).start();
		}
		System.out.println("Notice: toBePublished hashCode from Broker class is " + this.toBePublished.hashCode() );
		System.out.println("Notice: is Broker list the same of ConnectionListerner? " + Boolean.toString(this.toBePublished == this.listeners.get(0).toBePublished));
	}
	
	private void send(Subscription s,Event a) throws IOException{
		// get outputstream
		OutputStream os = s.getSocket().getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(a);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Broker Thread started.");
		while(true){
			synchronized(this.toBePublished){
				while(this.toBePublished.isEmpty()){
					try {
						System.out.println("Waiting...");
						this.toBePublished.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("Sending publication...");
				Event e = toBePublished.removeLast();
				try {
					send(subscribers.get(e.getArgument()),e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
}
