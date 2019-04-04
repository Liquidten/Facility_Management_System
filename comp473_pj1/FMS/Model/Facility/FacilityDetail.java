package FMS.Model.Facility;

public class FacilityDetail extends Facility {

	private String facilityName;

	private int numberOfRooms;

	private int phoneNumber;

	public FacilityDetail() {
	}

	public String getName() {
		return facilityName;
	}

	public void setName(String name) {
		this.facilityName = name;

	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;

	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int PhoneNumber) {
		this.phoneNumber = PhoneNumber;

	}

}
