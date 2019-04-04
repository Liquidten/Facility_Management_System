package FMS.Model.Maintenance;

import FMS.Model.Facility.Facility;
import FMS.Model.Maintenance.Maintenance;
import FMS.Model.Use.FacilityUse;

import java.util.List;

public interface IMaintenanceService {
    public Maintenance facilityMaintRequest(Facility fac, String maintDetails, int roomnumber);
    public void scheduleMaintenance(Maintenance maintRequest);
    public int calcMaintenanceCostForFacility(Facility fac);
    public List<Maintenance> listMaintRequests(Facility fac);
    public List<Maintenance> listMaintenance(Facility fac);
    public List<Maintenance> listFacilityProblems(Facility fac);
    public int calcDownTimeForFacility(Facility fac);
    public double calcProblemRateForFacility(FacilityUse facUse);
}
