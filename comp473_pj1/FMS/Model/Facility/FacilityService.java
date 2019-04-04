package FMS.Model.Facility;

import FMS.DAL.FacilityDAO;
import FMS.DAL.UseDAO;
import FMS.Model.Use.FacilityUse;

import java.time.LocalDate;
import java.util.List;

public class FacilityService implements IFacilityService {

	private FacilityDAO facDAO = new FacilityDAO();
	private UseDAO useDAO = new UseDAO();

	public void clearAllFacilityData() {
		facDAO.removeAllData();
	}

	// Adding a new facility to the database
	public void addNewFacility(Facility facility) {
		facDAO.addNewFacility(facility);
	}

	public Facility getFacilityInformation(String facName) {
		return facDAO.getFacilityInformationByFacilityName(facName);
	}

	public void removeFacility(int id) {
		facDAO.removeFacility(id);
	}

	public void addFacilityDetail(int ID, int PhoneNumber) {
		facDAO.addFacilityDetail(ID, PhoneNumber);
	}

	public List<Facility> listFacilities() {
		return facDAO.listFacilities();
	}

	public int requestAvailableCapacity(Facility fac) {

		List<FacilityUse> usage = useDAO.listActualUsage(fac);
		int roomInUse = 0;
		if (usage.size()>0) {
			for (FacilityUse facUse :usage) {
				//if currently un use
				if ((LocalDate.now().equals(facUse.getStartDate()) || LocalDate.now().isAfter(facUse.getStartDate())) & LocalDate.now().equals(facUse.getEndDate()) || LocalDate.now().isBefore(facUse.getEndDate())) {
					if (facUse.getRoomNumber() == 0) {
						return 0;
					} else {
						roomInUse = roomInUse + 1;
					}
				}
			}
		}

		return fac.getFacilityDetail().getNumberOfRooms() - roomInUse;
	}

}
