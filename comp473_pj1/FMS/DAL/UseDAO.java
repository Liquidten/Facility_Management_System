package FMS.DAL;

import java.util.List;

import FMS.Model.Facility.Facility;
import FMS.Model.Use.FacilityUse;
import FMS.Model.Use.Inspection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.*;
import java.util.ArrayList;


public class UseDAO {

    public UseDAO() {

    }

    /**
     * List the inspections at a particular facility.
     * Uses sample dummy data in database.
     *
     * @param fac facility to search for inspections
     * @return a list of inspections
     */
    public List<Inspection> ListInspections(Facility fac) {
        List<Inspection> listOfInspec = new ArrayList<>();

        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String listInspectionsQuery = "SELECT * FROM Inspection WHERE "
                    + "FacilityID = '" + fac.getFacilityID() + "'";

            ResultSet useRS = st.executeQuery(listInspectionsQuery);
            System.out.println("UseDAO: *************** Query " + listInspectionsQuery + "\n");

            while (useRS.next()) {
                Inspection inspection = new Inspection();
                inspection.setInspection_Category(useRS.getString("InspectionType"));
                inspection.setInspectionDetail(useRS.getString("InspectionDetail"));
                inspection.setFacilityID(fac.getFacilityID());
                listOfInspec.add(inspection);
            }

            //close to manage resources
            useRS.close();
            st.close();

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving "
                    + "inspections from Inspections table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfInspec;


    }

    /**
     * Check if Facility are in used between 2 time
     * @param facUse The FacilityUse with information to check
     * @return True if Facility are being used, False for not
     */
    public boolean isInUseDuringInterval(FacilityUse facUse) {
        boolean result = false;
        try {
            //Insert the FacilityID, RoomNumber, and start/end Dates into Use table
            Statement st = DB_Helper.getConnection().createStatement();
            String selectUseAssignments = "SELECT * FROM Use WHERE FacilityID = " + facUse.getFacilityID() +
                    " AND RoomNumber IN (0, " + facUse.getRoomNumber() + ")";

            ResultSet useRS = st.executeQuery(selectUseAssignments);
            System.out.println("UseDAO: *************** Query " + selectUseAssignments + "\n");

            //check if dates in database overlap with input interval
            while (useRS.next()) {
                LocalDate assignStart = useRS.getDate("StartDate").toLocalDate();
                LocalDate assignEnd = useRS.getDate("EndDate").toLocalDate();
                if (facUse.getStartDate().isBefore(assignEnd) && (assignStart.isBefore(facUse.getEndDate()) ||
                        assignStart.equals(facUse.getEndDate()))) {
                    result = true;
                    break;
                }
            }

            //close to manage resources
            useRS.close();
            st.close();

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException checking if "
                    + "Facility is in use during an interval.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return result;
    }

    /**
     * Assign Facility to be used
     * @param facUse the facility use contains information to assign
     */
    public void AssignFacilityToUse(FacilityUse facUse) {
        Connection con = DB_Helper.getConnection();
        PreparedStatement usePst = null;

        try {
            //Insert the facility ID, room number, and start/end dates into use table
            String useStm = "INSERT INTO USE(FacilityID, RoomNumber, StartDate, "
                    + "EndDate) VALUES (?, ?, ?, ?)";
            usePst = con.prepareStatement(useStm);
            usePst.setInt(1, facUse.getFacilityID());
            usePst.setInt(2, facUse.getRoomNumber());
            usePst.setDate(3, Date.valueOf(facUse.getStartDate()));
            usePst.setDate(4, Date.valueOf(facUse.getEndDate()));
            usePst.executeUpdate();
            System.out.println("UseDAO: *************** Query " + usePst + "\n");

            //close to manage resources
            usePst.close();
            con.close();
        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException assigning a facility "
                    + "to use in the Use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }


    }

    /**
     * Lists all the usage assignments at a particular facility from the use table.
     * Usage assignments are first sorted by room number and then by date.
     *
     * @param fac Facility to list the usage assignments for
     * @return a list of FacilityUse objects containing a room number, start date, and end date
     */
    public List<FacilityUse> listActualUsage(Facility fac) {
        List<FacilityUse> listOfUsage = new ArrayList<FacilityUse>();

        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String listUsageQuery = "SELECT * FROM Use WHERE FacilityID = '" +
                    fac.getFacilityID() + "' ORDER BY RoomNumber, StartDate";

            ResultSet useRS = st.executeQuery(listUsageQuery);
            System.out.println("UseDAO: *************** Query " + listUsageQuery + "\n");

            while (useRS.next()) {
                FacilityUse use = new FacilityUse();
                use.setFacilityID(fac.getFacilityID());
                use.setRoomNumber(useRS.getInt("RoomNumber"));
                use.setStartDate(useRS.getDate("StartDate").toLocalDate());
                use.setEndDate(useRS.getDate("EndDate").toLocalDate());
                listOfUsage.add(use);
            }

            //close to manage resources
            useRS.close();
            st.close();
            return listOfUsage;

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving list of usage from Use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }

    /**
     * Vacates a facility/room by setting the end date of a current assignment to yesterday.
     * This allows the facility to be reassigned starting today.
     */
    public void VacateFacility(FacilityUse use) {
        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String vacateQuery = "";

            if (LocalDate.now().isAfter(use.getStartDate()) && (LocalDate.now().equals(use.getEndDate()) ||
                    LocalDate.now().isBefore(use.getEndDate()))) {
                vacateQuery = "UPDATE Use SET EndDate = '" + Date.valueOf(LocalDate.now().minusDays(1)) +
                        "' WHERE FacilityID = " + use.getFacilityID() + " AND RoomNumber = " + use.getRoomNumber() +
                        " AND StartDate = '" + Date.valueOf(use.getStartDate()) + "'";
            }

            st.execute(vacateQuery);
            System.out.println("UseDAO: *************** Query " + vacateQuery + "\n");

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException vacating the facility.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }


    }

    public LocalDate getFacilityStartDate(Facility fac) {
        LocalDate facilityStartDate = null;
        try {

            Statement st = DB_Helper.getConnection().createStatement();
            String getFacilityStartDateQuery = "SELECT StartDate FROM Use WHERE FacilityID = '" +
                    fac.getFacilityID() + "' ORDER BY StartDate LIMIT 1";

            ResultSet useRS = st.executeQuery(getFacilityStartDateQuery);
            System.out.println("UseDAO: *************** Query " + getFacilityStartDateQuery + "\n");

            while (useRS.next()) {
                facilityStartDate = useRS.getDate("StartDate").toLocalDate();
            }

            //close to manage resources
            useRS.close();
            st.close();

        } catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving facility start date "
                    + "from the Use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return facilityStartDate;

    }

}
