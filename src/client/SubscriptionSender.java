package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import common.Event;

public class SubscriptionSender {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Socket s = new Socket("127.0.0.1",Integer.parseInt(args[0]));
		Event a = new Event("calcio");
		// get outputstream
		OutputStream os = s.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(a);
		
		while (true){
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			common.Event event = (common.Event)ois.readObject();
			System.out.println("received: " + event.getArgument() + " " + event.getPayload());
		}
	}

}
