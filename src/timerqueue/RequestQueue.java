package timerqueue;

import java.util.PriorityQueue;

import networkgraph.Graph;
import networkgraph.GraphComparator;

public class RequestQueue {
	public PriorityQueue<Graph> m_graphqueue;
	private int m_size;
	
	public RequestQueue() {
		m_graphqueue = new PriorityQueue<Graph>(10, new GraphComparator());
		m_size = 0;
	}
	
	public int getSize() {
		return m_size;
	}

	
	
	public Graph getFirstGraph() {
		Graph rgraph =  m_graphqueue.poll();
		m_size = m_graphqueue.size();
		return rgraph;
	}
	
	public void pushGraph(Graph graph) {
		m_graphqueue.add(graph);
		m_size = m_graphqueue.size();
	}
	
	
}