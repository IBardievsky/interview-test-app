package player;

import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerCreateDto;
import com.spribe.models.request.PlayerGetByPlayerIdRequestDto;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetPlayerByIdTest extends BasePlayerTest {

    @Test
    @Description("Verify that it is possible to get data by player id")
    public void testGetPlayerById() {
        PlayerCreateDto newPlayerData = PlayerGenerator.createRandomPlayer();
        PlayerCreateDto createdPlayer = playerClient.createPlayer(Role.SUPERVISOR, newPlayerData, 200);

        PlayerGetByPlayerIdRequestDto fetchedPlayer = new PlayerGetByPlayerIdRequestDto(createdPlayer.getId());
        PlayerCreateDto actual = playerClient.getPlayerById(fetchedPlayer, 200);
        assertEquals(actual, createdPlayer);
    }

    @Test
    @Description("Verify that error 404 is displayed when trying to get a player with the non existing id")
    public void testGetPlayerByInvalidId() {
        PlayerGetByPlayerIdRequestDto fetchedPlayer = new PlayerGetByPlayerIdRequestDto(9999999L);
        playerClient.getPlayerById(fetchedPlayer, 404);
    }
}
