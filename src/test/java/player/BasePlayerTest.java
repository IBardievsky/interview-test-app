package player;

import com.spribe.clients.PlayerClient;
import com.spribe.filters.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BasePlayerTest {

    protected PlayerClient playerClient;

    @BeforeMethod
    public void setUp() {
        playerClient = new PlayerClient();
    }

}
