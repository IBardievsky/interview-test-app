import com.spribe.clients.PlayerClient;
import com.spribe.filters.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected PlayerClient playerClient;

    @BeforeMethod
    public void setUp() {
        playerClient = new PlayerClient();
    }

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

}
