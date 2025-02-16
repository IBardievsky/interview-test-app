package player;

import com.spribe.enums.Gender;
import com.spribe.enums.Role;
import com.spribe.generators.PlayerGenerator;
import com.spribe.models.request.PlayerCreateDto;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CreatePlayerTest extends BasePlayerTest {

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
        PlayerCreateDto actualResponse = playerClient.createPlayer(editor, playerDto, 200);

        assertNotNull(actualResponse.getId(), "Player ID is null");
        assertEquals(actualResponse, playerDto.withId(actualResponse.getId()));
    }

    @DataProvider
    public Object[][] inValidData() {
        return new Object[][]
                // these cases like example. All mandatory fields should be verified
                {
                        {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer(Gender.FEMALE, Role.ADMIN).withPassword(""), 400},
                        {Role.SUPERVISOR, PlayerGenerator.createRandomPlayer(Gender.MALE, Role.USER).withAge(16), 400}
                };
    }

    @Test(dataProvider = "inValidData")
    @Description("Create player with invalid data")
    public void testCreatePlayerWithInvalidData(Role editor, PlayerCreateDto playerDto, int statusCode) {
        playerClient.createPlayer(editor, playerDto, statusCode);
    }
}
