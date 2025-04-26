package application;

/*this method to create the graph which include the Vertexes and the Edges*/
public class Graph {

	// should know the number of V to create array to but the V in
	private int numberOfVertices;
	private Vertex[] vertices;

	public Graph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		this.vertices = new Vertex[numberOfVertices];
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	// this method to put the V in the array but first make sure that the V not
	// already in
	public void setVertex(int index, Capital capital) {
		for (Vertex vertex : vertices) {
			if (vertex != null && vertex.getCapital().getCapitalName().equalsIgnoreCase(capital.getCapitalName())) {
				return;
			}
		}
		Vertex vertex = new Vertex(capital, index);
		vertices[index] = vertex;
	}

	// in this method to search on specific V
	public Vertex findVertex(String capitalName) {
		for (Vertex vertex : vertices) {
			if (vertex != null && vertex.getCapital().getCapitalName().equalsIgnoreCase(capitalName)) {
				return vertex;
			}
		}
		return null;
	}

	// in this method to add the edge to the V so check if the start and end V in
	// then put the edge(check before in there are already edge)
	public void addEdge(Vertex startVer, Vertex endVer, Double cost, Double time) {
		if (startVer == null || endVer == null || startVer.equals(endVer)) {
			return;
		}
		Edge existingEdge = startVer.getEdgeWith(endVer);
		if (existingEdge != null) {
			return;
		}
		startVer.addEdge(endVer, cost, time);
	}
}
