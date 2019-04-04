package FMS.Model.Use;

public class Inspection {

	private int facilityID;

	private String Inspection_Category;

	private String inspectionDetail;

	public Inspection() {

	}

	public String getInspectionCategory() {
		return this.Inspection_Category;
	}

	public void setInspection_Category(String inspection_type) {
		this.Inspection_Category = inspection_type;
	}

	public String getInspectionDetail() {
		return this.inspectionDetail;
	}

	public void setInspectionDetail(String inspection_detail) {
		this.inspectionDetail = inspection_detail;
	}

	public int getFacilityID() {
		return this.facilityID;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

}
