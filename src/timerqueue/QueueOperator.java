package timerqueue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import networkgraph.Graph;
import networkgraph.Graph.Vertex;

public class QueueOperator{

	private RequestQueue m_rqueue;
	
	
	public QueueOperator() throws IOException {
		m_rqueue = new RequestQueue();
	}
	
	public int addNetwork(Graph network) {
		m_rqueue.pushGraph(network);
		final Timer timer = new Timer();
		final Graph g = network;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(g.revenue);
				Vertex[] a = g.vertex.toArray(new Vertex[g.vertex.size()]);
				Arrays.sort(a, new Graph.VertexComparator());
				int c = g.vertex.size();
				for (int i = 0; i < c; i++)
					System.out.println(a[i].m_CPU + " - " + g.vertex.size());
				m_rqueue.m_graphqueue.remove(g);
				// System.out.println(" - " + m_rqueue.m_graphqueue.size());
				timer.cancel();
			}
		}, network.deadline*1000);
		return m_rqueue.getSize();
	}
}
