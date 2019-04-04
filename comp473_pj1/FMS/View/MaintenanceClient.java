package FMS.View;

import FMS.Model.Facility.Facility;
import FMS.Model.Maintenance.Maintenance;
import FMS.Model.Facility.FacilityService;
import FMS.Model.Maintenance.MaintenanceService;
import FMS.Model.Maintenance.IMaintenanceService;
import FMS.Model.Facility.IFacilityService;

import java.util.List;

public class MaintenanceClient {

	public MaintenanceClient() {
		IMaintenanceService maintenanceService = new MaintenanceService();
		IFacilityService facService = new FacilityService();
		Facility fact1 = facService.getFacilityInformation("Law Center");

		//---------------------------------------------------------------
//		System.out.println("\nMaintenanceClient: *********** Creating new facility maintenance request *****************");
//		Maintenance maintenance = maintenanceService.facilityMaintRequest(fact1, "testing maintenance", 1);
//		System.out.println("\nMaintenanceClient: *********** Maintenance request created *****************");
//
//		maintenanceService.facilityMaintRequest(fact1, "testing maintenance 1", 2);
//		System.out.println("\nMaintenanceClient: *********** Maintenance request created *****************");
//
//		//---------------------------------------------------------------
//		System.out.println("\nMaintenanceClient: *********** Scheduling this maintenance request *****************");
//		maintenance.setCost(200);
//		maintenanceService.scheduleMaintenance(maintenance);
//		System.out.println("\nMaintenanceClient: *********** Maintenance request scheduled *****************");
//
//		System.out.println("\nMaintenanceClient: *********** Calculate total maintenance cost of a facility *****************");
//		int totalCost = maintenanceService.calcMaintenanceCostForFacility(fact1);
//		System.out.println("The total cost of maintenance already completed at Facility #" + fact1.getFacilityID() + " is $" + totalCost + ".");


		//---------------------------------------------------------------
		System.out.println("\nMaintenanceClient: *********** List current maintenance requests at a facility *****************");
		List<Maintenance> maintRequestList = maintenanceService.listMaintRequests(fact1);
		Object[][] requests = new Object[maintRequestList.size() + 1][2];
		requests[0] = new Object[] {"Maintenance Request Details", "Cost"};
		for (int i = 1; i <= maintRequestList.size(); i++) {
			requests[i] = new Object[] {maintRequestList.get(i-1).getDetails()};
		}
		System.out.println("Current maintenance requests at Facility #" + fact1.getFacilityID() + ":");
		for (Object[] row : requests) {
			System.out.format("   %-29s\n", row);
		}
//		---------------------------------------------------------------
		System.out.println("\nMaintenanceClient: *********** List maintenance completed at a facility *****************");
		List<Maintenance> maintenanceList = maintenanceService.listMaintenance(fact1);
		Object[][] maintenanceTable = new Object[maintenanceList.size() + 1][2];
		maintenanceTable[0] = new Object[] {"Maintenance Details", "Cost"};
		for (int i = 1; i <= maintenanceList.size(); i++) {
			maintenanceTable[i] = new Object[] {maintenanceList.get(i-1).getDetails(), maintenanceList.get(i-1).getCost()};
		}
		System.out.println("Maintenance completed at Facility #" + fact1.getFacilityID() + ":");
		for (Object[] row : maintenanceTable) {
			System.out.format("   %-30s%6s\n", row);
		}

//		//---------------------------------------------------------------
//		System.out.println("\nMaintenanceClient: *********** List all problems that have affected a facility *****************");
//		List<Maintenance> facilityProblemsList = maintenanceService.listFacilityProblems(fact1);
//		Object[][] problems = new Object[facilityProblemsList.size() + 1][2];
//		problems[0] = new Object[] {"Problem Details"};
//		for (int i = 1; i <= facilityProblemsList.size(); i++) {
//			problems[i] = new Object[] {facilityProblemsList.get(i-1).getDetails()};
//		}
//		System.out.println("Problems at Facility #" + fact1.getFacilityID() + ":");
//		for (Object[] row : problems) {
//			System.out.format("   %s\n", row);
//		}

//		//---------------------------------------------------------------
//		System.out.println("\nMaintenanceClient: *********** Calculate the down time for a facility *****************");
//		int downTime = maintenanceService.calcDownTimeForFacility(fact1);
//		System.out.println("Facility #" + fact1.getFacilityID() + " was down for maintenance for " + downTime + " days total, "
//				+ "assuming each completed maintenance request took 7 days to complete.");
//
//		System.out.println("\nMaintenanceClient: *********** Calculate the problem rate for a facility *****************");
//		double problemRate = maintenanceService.calcProblemRateForFacility(facService) * 100;
//		System.out.print("\nThe problem rate at Facility #" + fact1.getFacilityID() + " is ");
//		System.out.format("%.2f", problemRate);
//		System.out.print("%.");
	}

}
