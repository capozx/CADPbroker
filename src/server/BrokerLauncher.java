package server;
import java.io.IOException;
import java.util.ArrayList;

public class BrokerLauncher {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> ports = new ArrayList<Integer>();
		ports.add(9101);
		Broker b = new Broker(ports);
		Thread tB = new Thread(b);
		tB.start();
	}

}
