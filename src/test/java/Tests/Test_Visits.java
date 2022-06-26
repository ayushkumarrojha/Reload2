package Tests;

import APIModules.Visits;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class Test_Visits {

    Visits visit = new Visits();

    @Test(priority = 1, enabled = true)
    @Description("Validate if user is able to add Visit using players")
    public void addVisitByPlayer_test() {
        visit.addVisitByPlayers();
    }

    @Test(priority = 2, enabled = true)
    @Description("Validate if user is able to add Visit using position")
    public void addVisitByPosition_test() {
        visit.addVisitByPosition();
    }

    @Test(priority = 3, enabled = true)
    @Description("Validate if user is able to get all visit of a org")
    public void getAllVisit_test() {
        visit.getVisit();
    }

    @Test(priority = 4, enabled = true)
    @Description("Validate if user is able to get all players in a visit")
    public void getVisitPlayers_test() {
        visit.getVisitPlayer();
    }

    @Test(priority = 5, enabled = true)
    @Description("Validate if user is able to get all activity in a visit")
    public void getVisitActivity_test() {
        visit.getVisitActivities();
    }

    @Test(priority = 6, enabled = true)
    @Description("Validate if user is able to update visit")
    public void updateVisit_test() {
        visit.updateVisit();
    }

    @Test(priority = 7, enabled = true)
    @Description("Validate if user is able to delete visit")
    public void deleteVisit_test() {
        visit.deleteVisit();
    }
}
