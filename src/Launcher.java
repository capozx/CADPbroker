import java.io.IOException;
import java.util.ArrayList;

public class Launcher {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> ports = new ArrayList<Integer>();
		ports.add(8080);
		ports.add(21);
		Broker b = new Broker(ports);
	}

}
