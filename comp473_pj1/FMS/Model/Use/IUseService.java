package FMS.Model.Use;

import FMS.Model.Facility.Facility;
import FMS.Model.Use.Inspection;
import FMS.Model.Use.FacilityUse;

import java.time.LocalDate;
import java.util.List;

public interface IUseService {
    public List<Inspection> ListInspections(Facility fac);
    public boolean isInUseDuringInterval(FacilityUse facUse);
    public void AssignFacilityToUse(FacilityUse facUse);
    public List<FacilityUse> listActualUsage(Facility fac);
    public void VacateFacility(FacilityUse fac);
    public LocalDate getFacilityStartDate(FacilityUse facUse);
}
