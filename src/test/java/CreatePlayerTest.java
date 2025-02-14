import com.spribe.enums.Gender;
import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerCreateDto;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CreatePlayerTest extends BaseTest {

    @DataProvider
    public Object[][] validData() {
        return new Object[][]
                {
                        {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer(Gender.FEMALE, Role.ADMIN)},
                        {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer(Gender.MALE, Role.USER)}
                };
    }

    @Issue("1")
    @Test(dataProvider = "validData")
    @Description("Create player with valid data")
    public void testCreatePlayerWithValidData(Role editor, PlayerCreateDto playerDto) {
        Response response = playerClient.createPlayer(editor, playerDto);
        assertEquals(response.getStatusCode(), 200,
                "Status code is %s during creating the player for editor %s".formatted(response.getStatusCode(), editor));
        PlayerCreateDto actual = response.as(PlayerCreateDto.class);

        assertNotNull(actual.getId(), "Player ID is null");
        assertEquals(actual, playerDto.withId(actual.getId()));
    }

    @DataProvider
    public Object[][] inValidData() {
        return new Object[][]
                // these cases like example. All mandatory fields should be verified
                {
                        {Role.ADMIN, PlayerGenerator.createRandomPlayer(Gender.FEMALE, Role.ADMIN), 403},
                        {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer(Gender.MALE, Role.USER).withAge(16), 400}
                };
    }

    @Test(dataProvider = "inValidData")
    @Description("Create player with invalid data")
    public void testCreatePlayerWithInvalidData(Role editor, PlayerCreateDto playerDto, int statusCode) {
        Response response = playerClient.createPlayer(editor, playerDto);
        assertEquals(response.getStatusCode(), statusCode,
                "Status code is %s during creating the player for editor %s".formatted(response.getStatusCode(), editor));
    }
}
