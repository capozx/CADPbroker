import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class ConnectionListener implements Runnable{
	
	ServerSocket serverSocket;
	
	ConnectionListener(Integer portNumber) throws IOException{
		this.serverSocket = new ServerSocket(portNumber);
	}

	@Override
	public void run() {
		System.out.println("ConnectionLister is running on port " + this.serverSocket.getLocalPort());
		// TODO Auto-generated method stub
		while (true){
			try {
				Socket s = this.serverSocket.accept();
				SocketAddress remote = s.getRemoteSocketAddress();
				System.out.println("accpted connection from: " + remote.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
