package Tests;

import APIModules.Roster;
import APIModules.Team;
import Utilities.ReusableMethod;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

public class Test_Roster {

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
    public void addRoster_Test(){roster.addRoster();}

    @Test(priority = 4, enabled = true)
    @Description("Validate if user is able to get roster of a team")
    public void getRoster_Test(){roster.getRoster();}

    @Test(priority = 5, enabled = true)
    @Description("Validate if user is able to update roster")
    public void updateRoster_Test(){roster.updateRoster();}

    @Test(priority = 6, enabled = true)
    @Description("Validate if user is able to delete a teams of an university")
    public void deleteTeam_test() {
        team.deleteTeam();
    }
}
