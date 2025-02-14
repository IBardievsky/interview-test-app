import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerCreateDto;
import com.spribe.models.response.ErrorResponseDto;
import com.spribe.models.response.PlayerGetAllResponseDto;
import com.spribe.models.response.PlayerItemResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.spribe.endpoints.PlayerEndpoints.*;
import static org.testng.Assert.assertEquals;

public class GetAllPlayersTest extends BaseTest {

    @Issue("2")
    @Test
    @Description("Verify that all players are received successfully")
    public void testGetAllPlayers() {
        List<PlayerCreateDto> createdPlayers = new ArrayList<>();
        while (createdPlayers.size() < 2) {
            PlayerCreateDto newPlayerData = PlayerGenerator.createRandomPlayer();
            Response response = playerClient.createPlayer(Role.SUPERVISOR, newPlayerData);
            assertEquals(response.getStatusCode(), 200,
                    "Status code is %s during creating the player".formatted(response.getStatusCode()));
            PlayerCreateDto createdPlayer = response.as(PlayerCreateDto.class);
            createdPlayers.add(createdPlayer);
        }

        Response playersResponse = playerClient.getAllPlayers();
        assertEquals(playersResponse.getStatusCode(), 200,
                "Status code is %s while receiving all players info".formatted(playersResponse.getStatusCode()));
        List<PlayerItemResponseDto> playersList = playersResponse.as(PlayerGetAllResponseDto.class).getPlayers();

        boolean allFieldsNotNull = playersList.stream().allMatch(player ->
                player.getId() != null &&
                        player.getScreenName() != null &&
                        player.getGender() != null &&
                        player.getAge() != null &&
                        player.getRole() == null
        );
        List<Long> createdPlayerIds = createdPlayers.stream().map(PlayerCreateDto::getId).toList();
        boolean createdPlayersInTheList = playersList.stream()
                .map(PlayerItemResponseDto::getId)
                .anyMatch(createdPlayerIds::contains);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createdPlayersInTheList, "All created players should be present in the list");
        softAssert.assertTrue(allFieldsNotNull, "All fields of the players except of Role should not be null");
        softAssert.assertAll();
    }

    @Test
    @Description("Get all players with invalid request method")
    public void testGetAllPlayersWithInvalidRequestMethod() {
        Response responseResponse = playerClient.getAllPlayersWithPostMethod();
        assertEquals(responseResponse.getStatusCode(), 405,
                "Status code is %s while receiving all players info".formatted(responseResponse.getStatusCode()));
        ErrorResponseDto errorResponse = responseResponse.as(ErrorResponseDto.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(errorResponse.getTimestamp(), "Timestamp should not be null");
        softAssert.assertEquals(errorResponse.getStatus(), 405, "Status code mismatch");
        softAssert.assertEquals(errorResponse.getError(), "Method Not Allowed", "Error message mismatch");
        softAssert.assertEquals(errorResponse.getMessage(), StringUtils.EMPTY, "Message should be empty");
        softAssert.assertEquals(errorResponse.getPath(), GET_ALL_PLAYERS_URL, "Path mismatch");
        softAssert.assertAll();
    }
}