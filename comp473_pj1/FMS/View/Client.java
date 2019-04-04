package FMS.View;

public class Client {

	private UseClient useClient;

	private MaintenanceClient maintenanceClient;

	private FacilityClient facilityClient;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			FacilityClient facilityClient = new FacilityClient();
//			MaintenanceClient maintenanceClient = new MaintenanceClient();
//			UseClient useClient = new UseClient();
		} catch (Exception e) {
			System.out.println(e);
		}

		//UseClient useClient = new UseClient();
		//MaintenanceClient maintenanceClient = new MaintenanceClient();
	}

}
