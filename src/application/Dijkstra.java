package application;

import javafx.scene.control.ComboBox;

public class Dijkstra {

	private Graph graph;
	private TableEntry[] table;

	public boolean dijkstra(Vertex source, Vertex target, ComboBox<String> cmboFilter) {
		String selectedWeight = cmboFilter.getValue().toLowerCase();

		initializeTable();
		TableEntry.setWeightType(selectedWeight);

		if (target.equals(source)) {
			return false;
		}

		table[source.getNumber()].setDistance(0);

		for (;;) {
			// Get the vertex with the smallest unknown distance
			Vertex currentVertex = smallestUnknownDistanceVertex();

			// If reached the target vertex, we can stop and return true which the
			// path found
			if (currentVertex.equals(target)) {
				return true;
			}

			// Mark the current vertex as known
			TableEntry currentEntry = table[currentVertex.getNumber()];
			currentEntry.setKnown(true);

			// Explore all adjacent vertices of the current vertex
			Node<Edge> currentEdgeNode = currentVertex.getEdges().getHead();
			while (currentEdgeNode != null) {
				Edge edge = currentEdgeNode.getData();
				if (edge != null && edge.getEnd() != null) {
					int adjacentNumber = edge.getEnd().getNumber();

					// Only explore the adjacent vertex if it hasn't been processed yet
					if (!table[adjacentNumber].isKnown()) {
						double edgeWeight = edge.getWeight(selectedWeight); // Get the edge weight based on the selected
																			// type
						double newDistance = table[currentVertex.getNumber()].getDistance() + edgeWeight;
						double oldDistance = table[adjacentNumber].getDistance();
						// If the new calculated distance is smaller, update the entry
						if (newDistance < oldDistance) {
							table[adjacentNumber].setDistance(newDistance);
							table[adjacentNumber].setPath(currentVertex); // Update the path to the current vertex
						}
					}
				}
				currentEdgeNode = currentEdgeNode.getNext(); // Move to the next edge
			}
		}
	}

	// Helper method to find the vertex with the smallest unknown distance,
	private Vertex smallestUnknownDistanceVertex() {
		double minDistance = Double.MAX_VALUE;
		Vertex minVertex = null;

		// Iterate through all the entries in the table to find the vertex with the
		// smallest unknown distance
		for (TableEntry entry : table) {
			if (entry != null && !entry.isKnown()) {
				// Compare the entry's distance and find the vertex with the smallest one
				double entryDistance = entry.getDistance(); // Get the distance based on the selected weight

				if (entryDistance < minDistance) {
					minDistance = entryDistance;
					minVertex = entry.getVertex(); // Store the vertex with the smallest distance
				}
			}
		}

		// Return the vertex with the smallest distance that hasn't been processed
		return minVertex;
	}

	public void initializeTable() {
		table = new TableEntry[graph.getNumberOfVertices()];
		Vertex[] capitalsV = graph.getVertices();
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			TableEntry tableEntry = new TableEntry(capitalsV[i], Double.MAX_VALUE, false, null);
			table[i] = tableEntry;
		}
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public TableEntry[] getTable() {
		return table;
	}

	public void setTable(TableEntry[] table) {
		this.table = table;
	}

	public void graph(int numOfV) {
		graph = new Graph(numOfV);
	}

//	public void reinitializeTable(Vertex source) {
//	Node<Integer> currNode = altered.getHead();
//	while (currNode != null) {
//		int index = currNode.getData();
//		table[index].setDistance(Double.MAX_VALUE);
//		table[index].setKnown(false);
//		table[index].setPath(null);
//		currNode = currNode.getNext();
//	}
//
//	table[source.getNumber()].setDistance(0.0);
//}

//	private Test<TableEntry> heap;
//
//	public boolean dijkstraT(Vertex source, Vertex target, ComboBox<String> cmboFilter) {
//		String selectedWeight = cmboFilter.getValue().toLowerCase();
//
//		heap = new Test<>(graph.getNumberOfVertices());
//		reinitializeTable(source); //
//		TableEntry.setWeightType(selectedWeight);
//	
//		for (TableEntry entry : table) {
//			if (entry != null) {
//				entry.updateDistance();
//			}
//		}

//	// If no such vertex exists, all vertices have been processed
//	if (currentVertex == null) {
//		break;
//	}
//		altered.addAtEnd(source.getNumber());
//		heap.add(table[source.getNumber()]);
//
//		while (!heap.isEmpty()) {
//			TableEntry entry = heap.removeMin();
//			table[entry.getVertex().getNumber()].setKnown(true);
//
//			Vertex currentVertex = table[entry.getVertex().getNumber()].getVertex();
//			if (currentVertex.equals(target)) {
//				return true;
//			}
//
//			Node<Edge> currentEdgeNode = currentVertex.getEdges().getHead();
//			while (currentEdgeNode != null) {
//				Edge edge = currentEdgeNode.getData();
//				if (edge != null && edge.getEnd() != null && !table[edge.getEnd().getNumber()].isKnown()) {
//					int adjacentNumber = edge.getEnd().getNumber();
//
//					double edgeWeight = edge.getWeight(selectedWeight); // الوزن المختار فقط
//					double newValue = table[currentVertex.getNumber()].getDistance() + edgeWeight;
//					double oldValue = table[adjacentNumber].getDistance();
//
//					if (table[adjacentNumber].getPath() == null) {
//						heap.add(table[adjacentNumber]);
//						table[adjacentNumber].setDistance(newValue);
//						table[adjacentNumber].setPath(currentVertex);
//						altered.addAtEnd(adjacentNumber);
//					} else {
//						if (newValue < oldValue) {
//							table[adjacentNumber].setDistance(newValue);
//							table[adjacentNumber].setPath(currentVertex);
//						}
//					}
//				}
//				currentEdgeNode = currentEdgeNode.getNext();
//			}
//		}
//
//		return false;
//	}

}
