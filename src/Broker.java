import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Broker{
	
	private HashMap<String,Subscription> subscribers;
	private ArrayList<ConnectionListener> listeners;
	private ArrayList<Thread> tListeners;
	
	Broker(ArrayList<Integer> ports) throws IOException{
		this.listeners = new ArrayList<ConnectionListener>();
		this.tListeners = new ArrayList<Thread>();
		for (Integer port: ports){
			this.listeners.add(new ConnectionListener(port));
			this.tListeners.add(new Thread(this.listeners.get(this.listeners.size() - 1)));
			tListeners.get(this.tListeners.size() - 1).start();
		}
	}
	
}
