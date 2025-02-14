import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerDeleteRequestDto;
import com.spribe.models.request.PlayerCreateDto;
import com.spribe.models.response.PlayerGetAllResponseDto;
import com.spribe.models.response.PlayerItemResponseDto;
import com.spribe.clients.PlayerClient;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DeletePlayerTest extends BaseTest {

    @Test
    @Description("Verify that the player is deleted successfully")
    public void testDeletePlayer() {
        PlayerCreateDto newPlayerData = PlayerGenerator.createRandomPlayer();
        PlayerCreateDto createdPlayer = playerClient
                .createPlayer(Role.SUPERVISOR, newPlayerData)
                .as(PlayerCreateDto.class);

        PlayerDeleteRequestDto deleteRequestDto = new PlayerDeleteRequestDto(createdPlayer.getId());
        Response response = playerClient.deletePlayerById(Role.SUPERVISOR, deleteRequestDto);

        assertEquals(response.getStatusCode(), 204,
                "Status code is %s after deleting the player".formatted(response.getStatusCode()));

        // could be replaced with a call to DB to check if the player is deleted
        Response playersResponse = playerClient.getAllPlayers();
        assertEquals(playersResponse.getStatusCode(), 200,
                "Status code is %s while receiving all players info".formatted(playersResponse.getStatusCode()));

        List<PlayerItemResponseDto> players = playersResponse.as(PlayerGetAllResponseDto.class).getPlayers();
        boolean playerDoesNotExists = players.stream()
                .noneMatch(player -> player.getId().equals(createdPlayer.getId()));
        assertTrue(playerDoesNotExists, "The player should not be present in DB");
    }

    @Test
    @Description("Verify that error 403 is displayed when trying to delete a player with the non existing id")
    public void testDeleteNonExistentPlayer() {
        PlayerDeleteRequestDto deleteRequestDto = new PlayerDeleteRequestDto(999999L);
        Response response = playerClient.deletePlayerById(Role.SUPERVISOR, deleteRequestDto);
        assertEquals(response.getStatusCode(), 403,
                "Status code is %s after deleting the non-existent player".formatted(response.getStatusCode()));
    }
}
