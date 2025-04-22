package Container;

import EntityClasses.EntityBus;
import EntityClasses.EntityTicket;

import java.sql.Date;

public class temporarydataContainer {


    public static class searchBusinfo {
        private static String fromLocation;
        private static String toDestination;
        private static Date journeydate;

        public static void setData(String fromLocation, String toDestination, Date journeydate) {
            searchBusinfo.fromLocation = fromLocation;
            searchBusinfo.toDestination = toDestination;
            searchBusinfo.journeydate = journeydate;
        }

        public static String getFromLocation() {
            return fromLocation;
        }

        public static String getToDestination() {
            return toDestination;
        }

        public static Date getJourneydate() {
            return journeydate;
        }

        public static void remove() {
            searchBusinfo.journeydate = null;
            searchBusinfo.toDestination = null;
            searchBusinfo.fromLocation = null;
        }

        public static boolean isnull() {
            return (searchBusinfo.fromLocation == null);
        }
    }


    private static EntityBus bus;

    public static void setSelectedEntityBus(EntityBus entityBus) {
        bus = entityBus;
    }

    public static EntityBus getSelectedBus() {
        if (bus == null) return null;
        return bus;
    }

    public static void remove() {
        bus = null;
    }


    private static EntityTicket currentTicket;

    public static EntityTicket getCurrentTicket() {
        return currentTicket;
    }

    public static void setCurrentTicket(EntityTicket currentTicket) {
        temporarydataContainer.currentTicket = currentTicket;
    }


    private static String previousPage;

    public static String getPreviousPage() {
        return previousPage;
    }

    public static void setPreviousPage(String str) {
        previousPage = str;
    }


}
