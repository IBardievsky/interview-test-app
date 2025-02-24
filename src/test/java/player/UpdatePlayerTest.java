package player;

import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerItemDto;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UpdatePlayerTest extends BasePlayerTest {

    private PlayerItemDto prepareData() {
        PlayerItemDto newPlayerData = PlayerGenerator.createRandomPlayer();
        return playerClient.createPlayer(Role.SUPERVISOR, newPlayerData);
    }

    @Test
    @Description("Verify that the player is updated successfully")
    public void testUpdatePlayer() {
        PlayerItemDto createdPlayer = prepareData();

        // TODO: add dataProvider to update each field instead of updating all fields
        PlayerItemDto updatedPlayerData = PlayerGenerator.createRandomPlayer();
        PlayerItemDto actual = playerClient
                .updatePlayer(Role.SUPERVISOR, createdPlayer.getId(), updatedPlayerData, 200);
        assertEquals(actual, updatedPlayerData.withId(createdPlayer.getId()));
    }

    @Test(dataProvider = "data")
    public void testUpdatePlayerProfileWithInvalidData(Role role, PlayerItemDto playerDto, int statusCode) {
        PlayerItemDto createdPlayer = prepareData();
        playerClient.updatePlayer(role, createdPlayer.getId(), playerDto, statusCode);
    }

    @DataProvider
    private Object[][] data() {
        return new Object[][]{
                {Role.ADMIN, PlayerGenerator.createRandomPlayer().withGender(null), 403},
                {Role.USER, PlayerGenerator.createRandomPlayer().withAge(16), 403},
                {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer().withScreenName(""), 404},
                {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer().withRole(null), 404}

        };
    }
}
