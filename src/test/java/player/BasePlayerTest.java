package player;

import com.spribe.clients.PlayerClient;
import com.spribe.filters.TestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BasePlayerTest {

    protected PlayerClient playerClient;

    @BeforeClass
    public void beforeClass() {
        playerClient = new PlayerClient();
    }

}
