package Tests;

import APIModules.InjuryReport;
import APIModules.Roster;
import APIModules.Team;
import Utilities.ReusableMethod;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

public class Test_InjuryReport {

    InjuryReport injuryReport = new InjuryReport();

    Roster roster = new Roster();

    Team team = new Team();

    int count = 0;

    @Test(priority = 1, enabled = true)
    @Description("Validate if user is able to get all the teams of an university")
    public void getTeam_test() {
        String getTeam = team.getTeam();

        JsonPath js = ReusableMethod.rawToJson(getTeam);
        List<String> teams = js.getList("data.teams");
        count = teams.size()+1;
    }

    @Test(priority = 2, enabled = true)
    @Description("Validate if user is able to add a teams of an university")
    public void addTeam_test() {
        team.addTeam(this.count);
    }

    @Test(priority = 3, enabled = true)
    @Description("Test to add bulk roster")
    public void addRoster_Test(){
        roster.addRoster();
    }

    @Test(priority = 4)
    @Description("adding injury report")
    public void createInjurtReportTest() {injuryReport.createInjuryReport();}

    @Test(priority = 5)
    @Description("get injury report")
    public void getInjuryReportsTest() {injuryReport.getInjuryReport();}

    @Test(priority = 6)
    @Description("update injury report")
    public void updateInjurtReportTest() {injuryReport.updateInjuryReport();}

    @Test(priority = 7)
    @Description("delete injury report")
    public void deleteInjurtReportTest() {injuryReport.deleteReport();}
}
