package networkgraph;

import java.util.Comparator;


public class GraphComparator implements Comparator<Graph>{
	@Override
	public int compare(Graph x, Graph y)
	{
		if (x.revenue < y.revenue)
			return 1;
		if (x.revenue > y.revenue)
			return -1;
		return 0;
	}
}
