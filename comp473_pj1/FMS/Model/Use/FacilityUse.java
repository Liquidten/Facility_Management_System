package FMS.Model.Use;

import FMS.Model.Facility.Facility;

import java.time.LocalDate;

/**
 * 	public void vacateFacility(Facility fac, int roomNumber) {
 * 		 
 *  
 * 		List<FacilityUse> usageList = listActualUsage(fac);
 * 		if (roomNumber > fac.getDetailsAboutFacility().getNumberOfRooms()) { 
 * 			System.out.println("Invalid room number. There are only " +  
 * 					fac.getDetailsAboutFacility().getNumberOfRooms() + " rooms at this facility."); 
 * 		} else { 
 * 			for (FacilityUse use : usageList) {
 * 				//if room number matches usage list (or usage list entry is for entire facility)  
 * 				//and room is currently in use, set vacateQuery 
 * 				if (use.getRoomNumber() == 0 || (use.getRoomNumber() == roomNumber))  { 
 * 					if ((LocalDate.now().equals(use.getStartDate())) || LocalDate.now().isAfter(use.getStartDate())) { 
 * 						if ((LocalDate.now().equals(use.getEndDate())) || (LocalDate.now().isBefore(use.getEndDate()))) { 
 * 							useDAO.vacateFacility(fac, roomNumber); 
 * 						} else System.out.println(1); 
 * 					} else System.out.println(2); 
 * 				} else { 
 * 					//System.out.println("This room is not currently in use. Unable to vacate at this time."); 
 * 					System.out.println(3); 
 * 				} 
 * 			} 
 * 		} 
 *  
 * 		 
 * 	}
 */
public class FacilityUse extends Facility {

	private int roomNumber;

	private LocalDate endDate;

	private LocalDate startDate;

	public FacilityUse() {

	}

	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
