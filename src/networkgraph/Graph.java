package networkgraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph
{	
	// Network vertexes = this vertex + adjacency links
	public class Vertex implements Comparable<Vertex>
	{
	    public int name;											
	    public List<Edge> adjacencies;								
	    public double minDistance = Double.POSITIVE_INFINITY; 
	    public Vertex previous;	
	    public int m_CPU;
	    
	    public Vertex(int argName) { name = argName; }
	    public int compareTo(Vertex other)
	    {
	        return Double.compare(minDistance, other.minDistance);
	    }
	}
	
	// Network links
	public class Edge
	{
	    public Vertex target;
	    public double weight;
	    public double bandwidth;
	    
	    public Edge(Vertex argTarget, double w, double bw)
	    { target = argTarget; weight = w; bandwidth = bw; }
	}
	
	public static class VertexComparator implements Comparator<Vertex>{
		@Override
		public int compare(Vertex x, Vertex y)
		{
			if (x.m_CPU < y.m_CPU)
				return 1;
			if (x.m_CPU > y.m_CPU)
				return -1;
			return 0;
		}
	}
	
	// Array of vertexes
	public PriorityQueue<Vertex> vertex;
	public int graphsize;
	
	public int deadline;
	public long maptime;
	public int duration;
	public double revenue;
	
	public Graph(int size)
	{
		vertex = new PriorityQueue<Vertex>(size, new VertexComparator());
		graphsize = size;
	}
	public Graph(String filename) throws IOException
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(filename));
		graphsize = scanner.nextInt();
		
		// missing duration
		duration = scanner.nextInt();
		// missing deadline
		deadline = scanner.nextInt();
		
		vertex = new PriorityQueue<Vertex>(graphsize, new VertexComparator());
		for (int i = 0; i < graphsize; i++)
		{
			Vertex tmp = new Vertex(i);
			tmp.adjacencies = new ArrayList<Edge>();
			tmp.m_CPU = scanner.nextInt();
			
			vertex.add(tmp);
		}

		for (Vertex u : vertex)
		{
			for (Vertex v : vertex)
			{
				int constraint = scanner.nextInt();
				if ((v.name>u.name) && (constraint != -1))
				{
					u.adjacencies.add(new Edge(v, 0, constraint));
					v.adjacencies.add(new Edge(u, 0, constraint));
					revenue += constraint;
				}
			}
		}
		maptime = System.currentTimeMillis();
	}
	
	public double getRevenue() {
		return revenue;
	}
	
	public void debug_printVertex() {
		for (int i = 0; i<vertex.size(); i++)
		{
			System.out.println(vertex.poll().name);
		}
	}
}