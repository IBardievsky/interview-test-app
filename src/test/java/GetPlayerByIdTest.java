import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerCreateDto;
import com.spribe.models.request.PlayerGetByPlayerIdRequestDto;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetPlayerByIdTest extends BaseTest {

    @Test
    @Description("Verify that it is possible to get data by player id")
    public void testGetPlayerById() {
        PlayerCreateDto newPlayerData = PlayerGenerator.createRandomPlayer();
        PlayerCreateDto createdPlayer = playerClient
                .createPlayer(Role.SUPERVISOR, newPlayerData)
                .as(PlayerCreateDto.class);

        PlayerGetByPlayerIdRequestDto fetchedPlayer = new PlayerGetByPlayerIdRequestDto(createdPlayer.getId());
        Response response = playerClient.getPlayerById(fetchedPlayer);

        assertEquals(response.getStatusCode(), 200,
                "Status code is %s while receiving player info".formatted(response.getStatusCode()));
        PlayerCreateDto actual = response.as(PlayerCreateDto.class);
        assertEquals(actual, createdPlayer);
    }

    @Test
    public void testGetPlayerByInvalidId() {
        PlayerGetByPlayerIdRequestDto fetchedPlayer = new PlayerGetByPlayerIdRequestDto(9999999L);
        Response response = playerClient.getPlayerById(fetchedPlayer);
        assertEquals(response.getStatusCode(), 404,
                "Status code is %s while receiving player info".formatted(response.getStatusCode()));
    }
}
