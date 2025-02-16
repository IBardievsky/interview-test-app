package player;

import com.spribe.clients.PlayerClient;
import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerItemDto;
import com.spribe.models.request.PlayerGetByPlayerIdRequestDto;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetPlayerByIdTest extends BasePlayerTest {

    @Test
    @Description("Verify that it is possible to get data by player id")
    public void testGetPlayerById() {
        PlayerClient playerClient = new PlayerClient();
        PlayerItemDto newPlayerData = PlayerGenerator.createRandomPlayer();
        PlayerItemDto createdPlayer = playerClient.createPlayer(Role.SUPERVISOR, newPlayerData);

        PlayerGetByPlayerIdRequestDto fetchedPlayer = new PlayerGetByPlayerIdRequestDto(createdPlayer.getId());
        PlayerItemDto actual = playerClient.getPlayerById(fetchedPlayer, 200);
        assertEquals(actual, createdPlayer);
    }

    @Test
    @Description("Verify that error 404 is displayed when trying to get a player with the non existing id")
    public void testGetPlayerByInvalidId() {
        PlayerGetByPlayerIdRequestDto fetchedPlayer = new PlayerGetByPlayerIdRequestDto(9999999L);
        new PlayerClient().getPlayerById(fetchedPlayer, 404);
    }
}
