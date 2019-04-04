package FMS.DAL;

import FMS.Model.Facility.Facility;
import FMS.Model.Maintenance.Maintenance;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MaintenanceDAO {

    public MaintenanceDAO() {

    }

    /**
     * Creates a new maintenance request and inserts it into the facilityMaintRequest table.
     *
     * @param fac          Facility where the maintenance request is made
     * @param maintDetails description of the maintenance requested
     * @return the Maintenance object that was requested
     */
    public Maintenance FacilityMaintenanceRequest(Facility fac, String maintDetails, int roomnumber) {
        try {

            Maintenance maint = new Maintenance();
            maint.setDetails(maintDetails);
            maint.setCost(0);
            maint.setFacilityID(fac.getFacilityID());
            maint.setRoomnumber(roomnumber);

            Statement st = DB_Helper.getConnection().createStatement();
            String makeMaintRequestQuery = "INSERT INTO MaintenanceRequest (FacilityID, Detail,RoomNumber) VALUES (" +
                    fac.getFacilityID() + ", '" + maintDetails + "', " + maint.getRoomnumber() + ")";
            st.execute(makeMaintRequestQuery);
            System.out.println("MaintenanceDAO: *************** Query " + makeMaintRequestQuery + "\n");

            //close to manage resources
            st.close();

            return maint;

        } catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException making a maintenance request.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;

    }

    /**
     * Schedules a maintenance request by first adding it to the MaintenanceSchedule table and
     * then removing it from the facilityMaintRequest table.
     *
     * @param maintenance the request to be scheduled
     */
    public void ScheduleMaintenance(Maintenance maintenance) {
        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String scheduleMaintenanceAddQuery = "INSERT INTO MAINTENANCE (FacilityID, Detail, Cost, RoomNumber) VALUES (" +
                    maintenance.getFacilityID() + ", '" + maintenance.getDetails() +
                    "', " + maintenance.getCost() + ", " + maintenance.getRoomnumber() + ")";
            st.execute(scheduleMaintenanceAddQuery);
            System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceAddQuery + "\n");

            //close to manage resources
            st.close();

        } catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException adding a MaintenanceSchedule "
                    + "request to MaintenanceSchedule table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String scheduleMaintenanceRemoveQuery = "DELETE FROM MaintenanceRequest WHERE FacilityID = " +
                    maintenance.getFacilityID() + " AND Detail = '" + maintenance.getDetails() + "'";
            st.execute(scheduleMaintenanceRemoveQuery);
            System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceRemoveQuery + "\n");

            //close to manage resources
            st.close();

        } catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException removing a "
                    + "maintenance request from facilityMaintRequest table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }


    }

    /**
     * Calculates the sum of completed maintenance at a particular facility from the MaintenanceSchedule table.
     *
     * @param fac Facility to calculate maintenance Cost
     * @return total Cost of completed maintenance
     */
    public int calcMaintenanceCostForFacility(Facility fac) {
        try {

            int totalCost = 0;

            Statement st = DB_Helper.getConnection().createStatement();
            String calcMaintenanceCostQuery = "SELECT SUM(Cost) FROM MAINTENANCE "
                    + "WHERE FacilityID = " + fac.getFacilityID();
            ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);

            while (maintRS.next()) {
                totalCost = maintRS.getInt(1);
            }
            System.out.println("MaintenanceDAO: *************** Query " + calcMaintenanceCostQuery + "\n");

            //close to manage resources
            maintRS.close();
            st.close();

            return totalCost;

        } catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException calculating total "
                    + "maintenance cost from MaintenanceSchedule table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return 0;

    }

    /**
     * Lists Maintenance objects from the facilityMaintRequest table that have yet to be
     * scheduled/completed at a particular facility.
     *
     * @param fac Facility to list maintenance requests for
     * @return a list of Maintenance objects containing maintenance requests
     */
    public List<Maintenance> ListMaintRequests(Facility fac) {
        List<Maintenance> listOfMaintRequests = new ArrayList<Maintenance>();

        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String listMaintRequestsQuery = "SELECT * FROM MaintenanceRequest WHERE FacilityID = '" +
                    fac.getFacilityID() + "'";

            ResultSet maintRS = st.executeQuery(listMaintRequestsQuery);
            System.out.println("MaintenanceDAO: *************** Query " + listMaintRequestsQuery + "\n");

            while (maintRS.next()) {
                Maintenance maintenanceRequest = new Maintenance();
                maintenanceRequest.setDetails(maintRS.getString("Detail"));
                maintenanceRequest.setFacilityID(fac.getFacilityID());
                listOfMaintRequests.add(maintenanceRequest);
            }


            //close to manage resources
            maintRS.close();
            st.close();

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving list of maintenance "
                    + "requests from facilityMaintRequest table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfMaintRequests;
    }

    /**
     * Lists Maintenance objects from the MaintainanceSchedule table that have already been
     * completed at a particular facility.
     *
     * @param fac Facility to list maintenance for
     * @return a list of Maintenance objects containing completed maintenance
     */
    public List<Maintenance> listMaintenance(Facility fac) {
        List<Maintenance> listOfCompletedMaintenance = new ArrayList<Maintenance>();

        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String listMaintenanceQuery = "SELECT * FROM MAINTENANCE WHERE facilityID = '" +
                    fac.getFacilityID() + "' ORDER BY Cost";

            ResultSet maintRS = st.executeQuery(listMaintenanceQuery);
            System.out.println("MaintenanceDAO: *************** Query " + listMaintenanceQuery + "\n");

            while (maintRS.next()) {
                Maintenance maintenanceRequest = new Maintenance();
                maintenanceRequest.setDetails(maintRS.getString("Detail"));
                maintenanceRequest.setCost(maintRS.getInt("Cost"));
                maintenanceRequest.setFacilityID(fac.getFacilityID());
                listOfCompletedMaintenance.add(maintenanceRequest);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving list of maintenance "
                    + "from MaintainanceSchedule table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfCompletedMaintenance;
    }

}
