package application;

/*this class to create the city with the name and the longitude,latitude distance */
public class Capital implements Comparable<Capital> {

	private String capitalName;
	private double longitude;
	private double latitude;

	public Capital(String capitalName, double latitude, double longitude) {
		super();
		this.capitalName = capitalName;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public int compareTo(Capital o) {
		return this.capitalName.compareToIgnoreCase(o.getCapitalName());
	}

	@Override
	public String toString() {
		return  capitalName + "";
	}
	

}
