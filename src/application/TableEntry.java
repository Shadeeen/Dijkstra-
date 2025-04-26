package application;

/*
 *in this class Represents the entry in the Dijkstra's table, storing the current distance (or
 * cost/time), whether the vertex is known, and the previous vertex in the path.
 */
public class TableEntry implements Comparable<TableEntry> {

	private Vertex vertex;
	private double distance;
	private boolean known;
	private Vertex path;

	private static String weightType = "distance";

	public TableEntry(Vertex vertex, double distance, boolean known, Vertex path) {
		this.vertex = vertex;
		this.distance = distance;
		this.known = known;
		this.path = path;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public Vertex getPath() {
		return path;
	}

	public void setPath(Vertex path) {
		this.path = path;
	}

	public static void setWeightType(String type) {
		weightType = type.toLowerCase();
	}

	// to update the way (cost,time,distance) to choose the path (edges)
	public void updateDistance() {
		if (path != null) {
			Edge edge = path.getEdgeWith(vertex);
			if (edge != null) {
				this.distance = edge.getWeight(weightType);
			}
		}
	}

	@Override
	public int compareTo(TableEntry o) {
		return Double.compare(this.distance, o.getDistance());
	}
}
