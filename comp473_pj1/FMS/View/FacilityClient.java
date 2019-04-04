package FMS.View;

import FMS.Model.Facility.Facility;
import FMS.Model.Facility.FacilityDetail;
import FMS.Model.Facility.FacilityService;
import FMS.Model.Facility.IFacilityService;

import java.util.List;

public class FacilityClient {

	private FacilityService facilityService;

	public FacilityClient() throws Exception {
		IFacilityService facService = new FacilityService();
		System.out.println("\nFacilityClient: *************** Clearing All Data *************************");
		facService.clearAllFacilityData();

		//---------------------------------------------------------------
		System.out.println("\nFacilityClient: *************** Instantiating a facility and its details *************************");
		Facility fac = new Facility();;
		FacilityDetail detail = new FacilityDetail();
		detail.setName("IT Center");
		detail.setNumberOfRooms(4);
		detail.setPhoneNumber(5550123);
		fac.setFacilityDetail(detail);

		Facility fac1 = new Facility();;
		FacilityDetail detail1 = new FacilityDetail();
		detail1.setName("Law Center");
		detail1.setNumberOfRooms(10);
		detail1.setPhoneNumber(123456);
		fac1.setFacilityDetail(detail1);

		Facility fac2 = new Facility();;
		FacilityDetail detail2 = new FacilityDetail();
		detail2.setName("Student Center");
		detail2.setNumberOfRooms(100);
		detail2.setPhoneNumber(111111);
		fac2.setFacilityDetail(detail2);

		facService.addNewFacility(fac);
		facService.addNewFacility(fac1);
		facService.addNewFacility(fac2);
		System.out.println("FacilityClient: *************** Facility is inserted in Facility Database *************************");

		//---------------------------------------------------------------
		System.out.println("FacilityClient: *************** Trying to get information about this facility in the database ***************");
		Facility searchedFacility = facService.getFacilityInformation("IT Center");
		System.out.println("\nFacilityClient: *************** Here is searched facility information *************************");
		System.out.println("\n\tFacility ID:   \t\t" + searchedFacility.getFacilityID());
		FacilityDetail facilityDet = searchedFacility.getFacilityDetail();
		System.out.println("\tInfo About Facility:  \t" + facilityDet.getName() +
				"\n\t\t\t\t Number of Rooms:" + facilityDet.getNumberOfRooms());
		if (facilityDet.getPhoneNumber() != 0) {
			System.out.print("\t\t\t\t Phone Number: " + facilityDet.getPhoneNumber() +
					"\n\t\t\t\t" + "\n");
		} else {
			System.out.print("\t\t\t\t Phone Number: unlisted" +
					"\n\t\t\t\t" + "\n");
		}


		//---------------------------------------------------------------
		facService.addFacilityDetail(fac.getFacilityID(), 3120136);
		Facility updatedFacility = facService.getFacilityInformation(fac.getFacilityDetail().getName());
		FacilityDetail facilityNewDet = updatedFacility.getFacilityDetail();

		System.out.println("\nFacilityClient: *************** Here is the updated facility information *************************");
		System.out.println("\n\tFacility ID:   \t\t" + updatedFacility.getFacilityID());
		System.out.println("\tInfo About Facility:  \t" + facilityNewDet.getName() +
				"\n\t\t\t\t Number of Rooms: " + facilityNewDet.getNumberOfRooms());
		if (facilityNewDet.getPhoneNumber() != 0) {
			System.out.print("\t\t\t\t Phone Number: " + facilityNewDet.getPhoneNumber() +
					"\n\t\t\t\t" + "\n");
		} else {
			System.out.print("\t\t\t\t Phone Number: unlisted" +
					"\n\t\t\t\t" + "\n");
		}


		//---------------------------------------------------------------
		System.out.println("\nFacilityClient: *************** Remove a facility from the database *************************");
		facService.removeFacility(fac.getFacilityID());
		System.out.println("************ Facility Removed ************");

		System.out.println("\nFacilityClient: *************** An updated list of all the facilities *************************");
		List<Facility> listOfFacilities = facService.listFacilities();
		for (Facility fact : listOfFacilities) {
			FacilityDetail facDet = fact.getFacilityDetail();
			System.out.println("\n\t" + facDet.getName() + " ID: " + fac.getFacilityID());
		}

		//---------------------------------------------------------------
		System.out.println("\nFacilityClient: *************** Request available capacity of a facility *************************");
		//uses sample data
		int roomsAvail = facService.requestAvailableCapacity(fac2);
		System.out.println("There are " + roomsAvail + " rooms currently available at Facility #" + fac2.getFacilityID() + ".");

		//---------------------------------------------------------------
		System.out.println("\nFacilityClient: *************** Remove Facility *************************");
		facilityService.removeFacility(fac.getFacilityID());

	}

}
