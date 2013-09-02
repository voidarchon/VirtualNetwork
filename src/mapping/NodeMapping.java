package mapping;

import java.io.IOException;
import java.util.Arrays;

import networkgraph.Graph;
import networkgraph.SubstrateNetwork;
import networkgraph.VirtualNetwork;
import networkgraph.Graph.Vertex;

public class NodeMapping {
	private static int[] m_result;
	private static boolean m_isOK;
	/*
	public static int substractConstraints(List<List<Vertex>> A, Graph graph, int constraint)
	{
		int returnpath = -1;
		boolean check;
		for (int k = 0; k < A.size(); k++)
		{
			check = true;
			List<Vertex> vlist = A.get(k);
			for (int i = 0; i < vlist.size()-1; i++)
			{
				for (Edge e: vlist.get(i).adjacencies)
				{
					if (e.target == vlist.get(i+1))
					{
						
						System.out.print(vlist.get(i).name + " - " + e.target.name + ": " + e.weight + " | " + constraint);
						if (e.weight < constraint)
						{
							System.out.printf("\nPath #%d - Failed!\n", k+1);
							// return returnpath;
							check = false;
							break;
						}
						else 
						{
							// e.weight -=constraint;
							System.out.println(" (" + (e.weight - constraint) + ")");
						}
					}
				}
			}
			if (check)
				System.out.printf("Path #%d - OK!\n", k+1 );
			if (returnpath < 0 && check) 
			{
				returnpath = k;
				System.out.println("=> Use path#" + (k+1));
				for (int i = 0; i < vlist.size()-1; i++)
				{
					for (Edge e: vlist.get(i).adjacencies)
						if (e.target == vlist.get(i+1))
							e.weight -=constraint;
					for (Edge e: vlist.get(i+1).adjacencies)
						if (e.target == vlist.get(i))
							e.weight -=constraint;
				}
			}
		}
		return returnpath;
	}
	*/
	
	public static boolean mapVN2SN(VirtualNetwork virtual, SubstrateNetwork substrate) throws IOException
	{
		/*
		List<List<Vertex>> LResult = new ArrayList<List<Vertex>>();
		System.out.println("mapping...");
		int[] temp = new int[virtual.size];
		for (int i = 0; i < virtual.size; i++)
			for (int j = 0; j < substrate.size; j++)
				if (substrate.node[j].label == subnet.node[i].label)
				{
					substrate.node[j].CPUresource -= virtual.node[i].CPUresource;
					virtual.node[i].mappedlabel = substrate.node[j].label;
					System.out.println(virtual.node[i].label + " - " + substrate.node[j].label);
					temp[virtual.node[i].label] = substrate.node[j].label;
				}
		System.out.println("done!");
		Graph graph = new Graph("substratelinks.txt");
		for (int i = 0; i < virtual.size; i++)
			for (int j = i; j < virtual.size; j++)
			{
				if (virtual.matrix[i][j] > 0)
				{
					System.out.println(temp[i] + " - " + temp[j] + ": " + virtual.matrix[i][j]);
					LResult = dijkstra.getKShortestPath(graph, graph.vertex[temp[i]], graph.vertex[temp[j]], 3);
					substractConstraints(LResult, graph, virtual.matrix[i][j]);
				}
			}
		return true;
		*/
		Vertex[] vir_tmp = virtual.vertex.toArray(new Vertex[virtual.vertex.size()]);
		Arrays.sort(vir_tmp, new Graph.VertexComparator());
		int vir_size = virtual.vertex.size();
		Vertex[] sub_tmp = substrate.vertex.toArray(new Vertex[substrate.vertex.size()]);
		Arrays.sort(sub_tmp, new Graph.VertexComparator());
		
		m_result = new int[vir_size];
		m_isOK = true;
		
		for (int i = 0; i < vir_size; i++)
		{
			if (vir_tmp[i].m_CPU > sub_tmp[i].m_CPU)
			{
				m_isOK = false;
				return false;
			}
			m_result[i] = sub_tmp[i].name;
		}
		return true;
	}
	
	public int getMappingResult(int virnode) {
		if (m_isOK == true)
			return m_result[virnode];
		else return -1;
	}
}
