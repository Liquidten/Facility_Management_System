package FMS.Model.Use;

import FMS.Model.Facility.Facility;
import FMS.DAL.UseDAO;

import java.time.LocalDate;
import java.util.List;

public class UseService implements IUseService {

	private UseDAO useDAO = new UseDAO();

	/**
	 *   List all the inspections at a particular facility. 
	 *  Uses sample dummy data in database.
	 */
	public List<Inspection> ListInspections(Facility fac) {
		return useDAO.ListInspections(fac);
	}

	/**
<<<<<<< HEAD
	 *   Checks if a particular room at a facility is in use during an interval. 
	 *  Enter 0 for room number if checking the entire facility.
=======
	 *
	 * @param facUse
	 * @return
>>>>>>> 700d31fecfc89bde1f76efd2207e65ed064094e5
	 */
	public boolean isInUseDuringInterval(FacilityUse facUse) {
		// checks the start and end data are valid and rooms are there
		if (facUse.getStartDate().isAfter(facUse.getEndDate())){
			System.out.println("Start date must be before end date.");
		} else if (facUse.getRoomNumber() > facUse.getFacilityDetail().getNumberOfRooms()) {
			System.out.println("Invalid room number. There are only" + facUse.getFacilityDetail().getNumberOfRooms() + "rooms in this facility.");
		} else {
			return useDAO.isInUseDuringInterval(facUse);
		}
		return true;
	}

	/**
<<<<<<< HEAD
	 *   Assigns a facility and room number to use from a particular start date to a particular end date. 
	 *  Enter room number 0 if assigning entire facility to use.
=======
	 *
	 * @param facUse consists of roomNumber, startDate, endDate
>>>>>>> 700d31fecfc89bde1f76efd2207e65ed064094e5
	 */
	public void AssignFacilityToUse(FacilityUse facUse) {
		// checks that the start and end data are valid, room number exists and room isn't being used at that time
		if (facUse.getStartDate().isAfter(facUse.getEndDate())){
			System.out.println("Start date must be before end date");

		} else if (facUse.getRoomNumber() > facUse.getFacilityDetail().getNumberOfRooms()) {
			System.out.println("Invalid room number. There are only" + facUse.getFacilityDetail().getNumberOfRooms() + "rooms at this facility.");
		} else {
			useDAO.AssignFacilityToUse(facUse);
		}

	}

	/**
<<<<<<< HEAD
	 *   Lists the usage assignments at a particular facility first by room number and then by date.
=======
	 *   Lists the usage assignments at a particular facility first by room number and then by date. 
	 *  @param fac Facility to list the usage assignments for
	 *  @return a list of FacilityUse objects containing a room number, start date, and end date.
	 * 
>>>>>>> 15a09c16fbf4e6c40d4c76c15cec18d14db6e80b
	 */
	public List<FacilityUse> listActualUsage(Facility fac) {
		return useDAO.listActualUsage(fac);
	}

<<<<<<< HEAD:FMS/Service/UseService.java
<<<<<<< HEAD
	/**
	 *   Calculates the current usage rate of a particular facility.
	 */
	public double calcUsageRate(Facility fac) {
		return 0;
	}

	public void VacateFacility(FacilityUse fac) {
=======
	public void VacateFacility(UniversityUse fac) {
>>>>>>> 15a09c16fbf4e6c40d4c76c15cec18d14db6e80b
=======
	public void VacateFacility(FacilityUse fac) {
>>>>>>> 289dd3d1ca8115a298f3827d28eabba6ff1aadd8:FMS/Model/Use/UseService.java
		useDAO.VacateFacility(fac);
	}

	public LocalDate getFacilityStartDate(FacilityUse facUse) {
		return facUse.getStartDate();
	}
}
