package FMS.Model.Maintenance;


import FMS.Model.Facility.Facility;
import FMS.DAL.MaintenanceDAO;
import FMS.Model.Use.UseService;
import FMS.Model.Use.FacilityUse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MaintenanceService implements IMaintenanceService {

	private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

	/**
	 *   This creates a new maintenance request which specifies the facility, maintenance details, and the cost.
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
<<<<<<< HEAD
=======
	 *  @param fac University where the maintenance request is made
=======
	 *  @param fac Facility where the maintenance request is made
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 *  @param maintDetails description of the maintenance requested
	 *  @return the Maintenance object that was requested 
	 * 
>>>>>>> 700d31fecfc89bde1f76efd2207e65ed064094e5
	 */

	public Maintenance facilityMaintRequest(Facility fac, String maintDetails, int roomnumber) {
		return maintenanceDAO.FacilityMaintenanceRequest(fac, maintDetails, roomnumber);
	}

	/**
	 *   Schedules a maintenance request by moving the request from the table of
	 *  maintenance requests to the table of completed maintenance.
	 */
	public void scheduleMaintenance(Maintenance maintRequest) {
		maintenanceDAO.ScheduleMaintenance(maintRequest);

	}

	/**
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
	 *   Calculates the total cost of completed maintenance for a particular facility.
=======
	 *   Calculates the total cost of completed maintenance for a particular facility. 
	 *  @param fac Facility to calculate maintenance cost
	 *  @return total cost of completed maintenance 
	 * 
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 */
	public int calcMaintenanceCostForFacility(Facility fac) {
		return  maintenanceDAO.calcMaintenanceCostForFacility(fac);
	}

	/**
	 *   Lists maintenance requests that have yet to be scheduled/completed at a  
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
	 *  particular facility.
=======
	 *  particular facility. 
	 *  @param fac Facility to list the maintenance requests for
	 *  @return a list of Maintenance objects containing maintenance requests 
	 * 
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 */
	public List<Maintenance> listMaintRequests(Facility fac) {
		return maintenanceDAO.ListMaintRequests(fac);
	}

	/**
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
	 *   Lists maintenance that has already been completed at a particular facility.
=======
	 *   Lists maintenance that has already been completed at a particular facility. 
	 *  @param fac Facility to list maintenance for
	 *  @return a list of Maintenance objects containing completed maintenance 
	 * 
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 */
	public List<Maintenance> listMaintenance(Facility fac) {
		return maintenanceDAO.listMaintenance(fac);
	}

	/**
	 *   Lists all problems that have affected a particular facility including  
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
	 *  current maintenance requests and completed maintenance.
=======
	 *  current maintenance requests and completed maintenance.  
	 *  @param fac Facility to the list the problems
	 *  @return a list of Maintenance objects which are the problems that have affected a facility 
	 * 
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 */
	public List<Maintenance> listFacilityProblems(Facility fac) {
		List<Maintenance> FacilityProblems = new ArrayList<Maintenance>();
		FacilityProblems.addAll(maintenanceDAO.listMaintenance(fac));
		FacilityProblems.addAll(maintenanceDAO.ListMaintRequests(fac));

		return FacilityProblems;
	}

	/**
	 *   Calculates the down time for a facility with the assumption that each completed 
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
	 *  maintenance item required 7 days of down time.
=======
	 *  maintenance item required 7 days of down time. 
	 *  @param fac Facility to calculate the down time
	 *  @return down time in days 
	 * 
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 */
	public int calcDownTimeForFacility(Facility fac) {
		int daysOfDownTime = 0;
		int numberOfCompletedMainItems = maintenanceDAO.listMaintenance(fac).size();
		daysOfDownTime = numberOfCompletedMainItems * 7;
		return daysOfDownTime;
	}

	/**
<<<<<<< HEAD:FMS/Service/MaintenanceService.java
<<<<<<< HEAD
<<<<<<< HEAD
	 *   Calculates the problem rate for a facility by dividing the down time for  
	 *  completed maintenance by the number of days since the facility was first created/assigned.
=======
	 * Calculates problem Rate for particular Facility
=======
	 * Calculates problem Rate for particular University
>>>>>>> 15a09c16fbf4e6c40d4c76c15cec18d14db6e80b
=======
	 * Calculates problem Rate for particular Facility
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Maintenance/MaintenanceService.java
	 * @param facUse
	 * @return Problem Rate
>>>>>>> 700d31fecfc89bde1f76efd2207e65ed064094e5
	 */
	public double calcProblemRateForFacility(FacilityUse facUse) {
		UseService useService = new UseService();
		LocalDate facilityStartDate = useService.getFacilityStartDate(facUse);
		double totalDays = ChronoUnit.DAYS.between(facilityStartDate, LocalDate.now());
		return calcDownTimeForFacility(facUse) / totalDays;
	}

}
