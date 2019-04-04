package FMS.Model.Maintenance;

public class Maintenance {

	private String details;

	private int facilityID;

	private int roomnumber;

	private int cost;

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public int getFacilityID() {
		return facilityID;
	}
}
