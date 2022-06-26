package Tests;

import APIModules.Player;
import Utilities.ReusableMethod;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

public class Test_Players {

    Player player = new Player();

    int count = 0;

    @Test(priority = 1, enabled = true)
    @Description("Retriving players of a team")
    public void getPlayer_test() {
        String getPlr = player.getPlayer();

        JsonPath js = ReusableMethod.rawToJson(getPlr);
        List<String> plr = js.getList("data.players");
        count = plr.size()+1;
    }

    @Test(priority = 2)
    @Description("Validate if user is able to add players")
    public void addPlayer_test() {
        player.addPlayer(this.count);
    }

    @Test(priority = 3, enabled = true)
    @Description("Validate if user is able to delete players")
    public void deletePlayer_test() {player.deletePlayer();}

    @Test(priority = 4, enabled = true)
    @Description("Validate if user is able to activate players")
    public void activatePlayer_test() {player.updatePlayer();}
}
