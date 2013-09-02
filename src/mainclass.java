
import java.io.IOException;

import networkgraph.VirtualNetwork;

import timerqueue.*;

public class mainclass {
	public static void main(String[] args) throws IOException
	{
		QueueOperator q = new QueueOperator();
		
		q.addNetwork(new VirtualNetwork("virtual1.txt"));
		q.addNetwork(new VirtualNetwork("virtual2.txt"));
		q.addNetwork(new VirtualNetwork("virtual3.txt"));
	}
}