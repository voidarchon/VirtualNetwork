package networkgraph;

import java.io.IOException;

public class SubstrateNetwork extends Graph {

	public SubstrateNetwork(int size) {
		super(size);
	}

	public SubstrateNetwork(String filename)
			throws IOException {
		super(filename);
	}
}
