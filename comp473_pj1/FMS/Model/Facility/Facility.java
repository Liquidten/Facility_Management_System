package FMS.Model.Facility;


public class Facility {



	private int facilityID;

	private FacilityDetail facilityDetail;

	public Facility() {

	}

	public FacilityDetail getFacilityDetail() {
		return this.facilityDetail;
	}

	public void setFacilityDetail(FacilityDetail details) {
		this.facilityDetail = details;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

	public int getFacilityID() {
		return this.facilityID;
	}

}
