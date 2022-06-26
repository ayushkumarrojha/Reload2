package Tests;

import APIModules.PlayerActivity;
import APIModules.Visits;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class Test_PerformActivity {

    Visits visit = new Visits();
    PlayerActivity pa = new PlayerActivity();

    public static int visitId;

    @Test(priority = 1, enabled = true)
    @Description("Validate if user is able to add Visit using players")
    public void addVisitByPlayer_test() {
        visitId = visit.addVisitByPlayers();
    }

    @Test(priority = 2, enabled = true)
    @Description("Validate if user is able to perform activity for player")
    public void performPlayerActivity_test() {
        pa.performPlayerActivity(this.visitId);
    }

    @Test(priority = 3, enabled = true)
    @Description("Validate if user is able to update activity for player")
    public void updatePlayerActivity_test() {
        pa.updatePlayerActivity(this.visitId);
    }

    @Test(priority = 4, enabled = true)
    @Description("Validate if user is able to add trial for player")
    public void addTrial_test(){
        pa.addPlayerTrial(this.visitId);
    }
}
