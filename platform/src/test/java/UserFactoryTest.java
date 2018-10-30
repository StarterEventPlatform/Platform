import com.eventplatform.factory.UserFactory;
import com.eventplatform.model.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserFactoryTest extends Assert {

    private User testUser;

    @Before
    public void setTestUser() {
        testUser = new User(0, new Date(1), "test", "test", "test", "test", "test");
    }

    @Test
    public void testCreateUserByUser() {
        User user = new User(0, new Date(1), "test", "test", "test", "test", "test");
        User factoryUser = UserFactory.createUser(user);
        assertEquals(factoryUser, testUser);
    }

    @Test
    public void testCreateUserByParams() {
        User factoryUser = new User(0, new Date(1), "test", "test", "test", "test", "test");
        assertEquals(factoryUser, testUser);
    }
}
