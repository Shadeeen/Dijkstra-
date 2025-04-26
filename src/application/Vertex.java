package application;

/*
 * Represents a vertex in the graph.
 * a vertex contains a capital, edges connected to it, and a unique number
 */
public class Vertex implements Comparable<Vertex> {

	private Capital capital;
	// to Stores the edges connected to this vertex
	private SinglyLinkedList<Edge> edges;
	private int number;

	public Vertex(Capital capital, int number) {
		this.capital = capital;
		this.edges = new SinglyLinkedList<>();
		this.number = number;
	}

	public Vertex(Capital capital, SinglyLinkedList<Edge> edges) {
		this.capital = capital;
		this.edges = edges;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Capital getCapital() {
		return capital;
	}

	public void setCapital(Capital capital) {
		this.capital = capital;
	}

	public SinglyLinkedList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(SinglyLinkedList<Edge> edges) {
		this.edges = edges;
	}

	// in this method it adds an edge to this vertex connecting it to another vertex
	// and the vertex to connect to,the cost weight of the edge and the time weight
	// of the edge
	public void addEdge(Vertex endVertex, Double cost, Double time) {
		if (endVertex == null) {
			return;
		}
		// the edge already exists
		if (getEdgeWith(endVertex) != null) {
			return;
		}
		Edge edge = new Edge(this, endVertex, cost, time);
		this.edges.addAtEnd(edge);
	}

	// in this method it Finds the edge that connecting this vertex to the specified
	// vertex.
	public Edge getEdgeWith(Vertex endVertex) {
		if (endVertex == null) {
			return null;
		}

		Node<Edge> currentNode = edges.getHead();
		while (currentNode != null) {
			Edge edge = currentNode.getData();
			if (edge.getEnd().equals(endVertex)) {
				return edge;
			}
			currentNode = currentNode.getNext();
		}

		return null;
	}

	@Override
	public int compareTo(Vertex other) {
		if (other == null || other.getCapital() == null || this.capital == null) {
			return -1;
		}
		return this.capital.compareTo(other.getCapital());
	}

	@Override
	public String toString() {
		return  capital + "";
	}
	
}
