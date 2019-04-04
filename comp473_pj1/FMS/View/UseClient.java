package FMS.View;

import FMS.Model.Facility.Facility;
import FMS.Model.Facility.FacilityService;
import FMS.Model.Use.UseService;
import FMS.Model.Facility.IFacilityService;
import FMS.Model.Use.IUseService;
import FMS.Model.Use.FacilityUse;
import FMS.Model.Use.Inspection;

import java.time.LocalDate;
import java.util.List;


public class UseClient {

	public UseClient() {
		IUseService useService = new UseService();
		IFacilityService universityService = new FacilityService();
		Facility fac = universityService.getFacilityInformation("Law Center");

		//---------------------------------------------------------------
		System.out.println("\nUseClient: *************** Listing the inspections at a facility *************************");
		System.out.println("\n\tInspections At Facility #" + fac.getFacilityID());
		List<Inspection> inspectionsList = useService.ListInspections(fac);
		if (inspectionsList.size() == 0) {
			System.out.println("No Inspection available");
		}
		for (Inspection inspection : inspectionsList) {
			System.out.println("\t" + inspection.getInspectionCategory() +
					" status: " + inspection.getInspectionDetail());
		}

		//---------------------------------------------------------------
		System.out.println("\nUseClient: ************ Assigning a facility to use ***************");
		FacilityUse facUse = new FacilityUse();;
		facUse.setFacilityID(fac.getFacilityID());
		facUse.setFacilityDetail(fac.getFacilityDetail());
		facUse.setStartDate(LocalDate.of(2016, 12, 1));
		facUse.setEndDate(LocalDate.of(2019, 12, 1));
		facUse.setRoomNumber(1);

		useService.AssignFacilityToUse(facUse);
		System.out.println("\nUseClient: ************ Facility and room assigned ***************");

		FacilityUse facUse2 = new FacilityUse();;
		facUse2.setFacilityID(fac.getFacilityID());
		facUse2.setFacilityDetail(fac.getFacilityDetail());
		facUse2.setStartDate(LocalDate.of(2016, 1, 1));
		facUse2.setEndDate(LocalDate.of(2020, 12, 1));
		facUse2.setRoomNumber(5);

		useService.AssignFacilityToUse(facUse2);
		System.out.println("\nUseClient: ************ Facility and room assigned ***************");


		//---------------------------------------------------------------
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");
		FacilityUse facUse3 = new FacilityUse();;
		facUse3.setFacilityID(fac.getFacilityID());
		facUse3.setFacilityDetail(fac.getFacilityDetail());
		facUse3.setStartDate(LocalDate.of(2015, 12, 1));
		facUse3.setEndDate(LocalDate.of(2017, 12, 1));
		facUse3.setRoomNumber(1);

		boolean result = useService.isInUseDuringInterval(facUse3);

		System.out.print("\tFacility #" + facUse3.getFacilityID());
		if (facUse3.getRoomNumber() != 0) {
			System.out.print(" - Room " + facUse3.getRoomNumber());
		}
		if (result) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + facUse3.getStartDate() + " to " + facUse3.getEndDate() + ".\n");


		//---------------------------------------------------------------
		System.out.println("\nUseClient: ************ Listing the usage at a facility before being vacated***************");
		List<FacilityUse> usageList = useService.listActualUsage(fac);
		Object[][] usage = new Object[usageList.size() + 1][3];
		usage[0] = new Object[] {"Room #", "Start Date", "End Date"};
		for (int i = 1; i <= usageList.size(); i++) {
			usage[i] = new Object[] {usageList.get(i-1).getRoomNumber(), usageList.get(i-1).getStartDate().toString(),
					usageList.get(i-1).getEndDate().toString()};
			if ((int) usage[i][0] == 0) {
				usage[i][0] = "all";
			}
		}
		System.out.println("Usage at Facility #" + facUse.getFacilityID());
		for (Object[] row : usage) {
			System.out.format("\t%-10s%-15s%-15s\n", row);
		}


		//---------------------------------------------------------------
		System.out.println("\nUseClient: ************ Vacate a facility  ***************");
		useService.VacateFacility(facUse);
		System.out.println("\nUseClient: ************ Facility vacated  ***************");

		//list actual usage that has been assigned to a particular facility
		System.out.println("\nUseClient: ************ Listing the usage at a facility after being vacated***************");

		//uses sample dummy data of usage in database
		List<FacilityUse> usageList2 = useService.listActualUsage(facUse);
		Object[][] usage2 = new Object[usageList2.size() + 1][3];
		usage2[0] = new Object[] {"Room #", "Start Date", "End Date"};
		for (int i = 1; i <= usageList2.size(); i++) {
			usage2[i] = new Object[] {usageList2.get(i-1).getRoomNumber(), usageList2.get(i-1).getStartDate().toString(),
					usageList2.get(i-1).getEndDate().toString()};
			if ((int) usage2[i][0] == 0) {
				usage2[i][0] = "all";
			}
		}
		System.out.println("Usage at Facility #" + facUse.getFacilityID());
		for (Object[] row : usage2) {
			System.out.format("\t%-10s%-15s%-15s\n", row);
		}

	}

}
