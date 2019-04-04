package FMS.Model.Facility;

import FMS.Model.Facility.Facility;

import java.util.List;

public interface IFacilityService {
    public void clearAllFacilityData();
    public void addNewFacility(Facility facility);
    public Facility getFacilityInformation(String facName);
    public void removeFacility(int id);
    public void addFacilityDetail(int ID, int PhoneNumber);
    public List<Facility> listFacilities();
    public int requestAvailableCapacity(Facility fac);

}
