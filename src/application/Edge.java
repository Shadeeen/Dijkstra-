package application;

/*this class to create the edge with should be two vertex the start and the end one to connect them with the edge
 * and it should be the cost which can be time,price,and distance
 * and it have the weight calculate method which is calculate the distance */
public class Edge implements Comparable<Edge> {

	private Vertex start;
	private Vertex end;
	private Double costWeight;
	private Double distanceWeight;
	private Double timeWeight;
	private String weightType = "distance";

	public Edge(Vertex start, Vertex end, Double costWeight, Double timeWeight) {
		this.start = start;
		this.end = end;
		this.costWeight = costWeight;
		this.timeWeight = timeWeight;
		calculate_weight();
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public Vertex getEnd() {
		return end;
	}

	public void setEnd(Vertex end) {
		this.end = end;
	}

	// this method to calculate the distance
	public void calculate_weight() {
		// we take the start and end longitude,latitude vertex and then convert them to
		// the radians to can use the sin and cos,,,
		double longitude1 = start.getCapital().getLongitude();
		double longitude2 = end.getCapital().getLongitude();
		double latitude1 = start.getCapital().getLatitude();
		double latitude2 = end.getCapital().getLatitude();

		double lat1_radian = Math.toRadians(latitude1);
		double lat2_radian = Math.toRadians(latitude2);
		double long1_radian = Math.toRadians(longitude1);
		double long2_radian = Math.toRadians(longitude2);

		// then calculate the distance by this equation
		double earthRadius = 6371;
		double cosTheta = (Math.sin(lat1_radian) * Math.sin(lat2_radian))
				+ Math.cos(lat1_radian) * Math.cos(lat2_radian) * Math.cos(long1_radian - long2_radian);

		// cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));
		double distance = earthRadius * Math.acos(cosTheta);
		this.distanceWeight = distance;
	}

	// this method to know exactly which weight chosen
	public Double getWeight(String type) {
		switch (type.toLowerCase()) {
		case "price":
			return costWeight != null ? costWeight : Double.MAX_VALUE;
		case "time":
			return timeWeight != null ? timeWeight : Double.MAX_VALUE;
		case "distance":
		default:
			return distanceWeight != null ? distanceWeight : Double.MAX_VALUE;
		}
	}

	public void setWeightType(String type) {
		this.weightType = type.toLowerCase();
	}

	@Override
	// compare the edges depend on the type which choose
	public int compareTo(Edge o) {
		double thisWeight = this.getWeight(weightType);
		double otherWeight = o.getWeight(weightType);
		return Double.compare(thisWeight, otherWeight);
	}
}
