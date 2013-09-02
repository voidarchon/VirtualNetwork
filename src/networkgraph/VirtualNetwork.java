package networkgraph;

import java.io.IOException;

public class VirtualNetwork extends Graph {

	public VirtualNetwork(int size) {
		super(size);
	}

	public VirtualNetwork(String filename)
			throws IOException {
		super(filename);
	}
}


