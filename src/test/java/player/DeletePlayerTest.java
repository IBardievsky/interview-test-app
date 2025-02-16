package player;

import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerDeleteRequestDto;
import com.spribe.models.request.PlayerCreateDto;
import com.spribe.models.response.PlayerItemResponseDto;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class DeletePlayerTest extends BasePlayerTest {

    @Test
    @Description("Verify that the player is deleted successfully")
    public void testDeletePlayer() {
        PlayerCreateDto newPlayerData = PlayerGenerator.createRandomPlayer();
        PlayerCreateDto createdPlayer = playerClient.createPlayer(Role.SUPERVISOR, newPlayerData, 200);

        PlayerDeleteRequestDto deleteRequestDto = new PlayerDeleteRequestDto(createdPlayer.getId());
        playerClient.deletePlayerById(Role.SUPERVISOR, deleteRequestDto, 204)
                .asResponse().assertThat().body(Matchers.emptyOrNullString());

        // could be replaced with a call to DB to check if the player is deleted
        List<PlayerItemResponseDto> players = playerClient.getAllPlayers(200).getPlayers();

        boolean playerDoesNotExists = players.stream()
                .noneMatch(player -> player.getId().equals(createdPlayer.getId()));
        assertTrue(playerDoesNotExists, "The player should not be present in DB");
    }

    @Test
    @Description("Verify that error 403 is displayed when trying to delete a player with the non existing id")
    public void testDeleteNonExistentPlayer() {
        PlayerDeleteRequestDto deleteRequestDto = new PlayerDeleteRequestDto(999999L);
        playerClient.deletePlayerById(Role.SUPERVISOR, deleteRequestDto, 403)
                .asResponse().assertThat().body(Matchers.emptyOrNullString());
    }
}
