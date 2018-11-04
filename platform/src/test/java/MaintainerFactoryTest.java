import com.eventplatform.factory.MaintainerFactory;
import com.eventplatform.model.GeoPosition;
import com.eventplatform.model.Maintainer;
import com.eventplatform.model.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class MaintainerFactoryTest extends Assert {

    private Maintainer testMaintainer;

    @Before
    public void setTestMaintainer() {
        testMaintainer = new Maintainer(0, new Date(1), new User(1, new Date(2), "test", "test", "test", "test", "test"), "test", "test", new GeoPosition(2, new Date(3), 1.0f, 1.0f));
    }

    @Test
    public void testCreateMaintainerByMaintainer() {
        Maintainer maintainer = new Maintainer(0, new Date(1), new User(1, new Date(2), "test", "test", "test", "test", "test"), "test", "test", new GeoPosition(2, new Date(3), 1.0f, 1.0f));
        Maintainer factoryMaintainer = MaintainerFactory.createMaintainer(maintainer);
        assertEquals(factoryMaintainer, testMaintainer);
    }

    @Test
    public void testCreateMaintainerByParams() {
        Maintainer factoryMaintainer = new Maintainer(0, new Date(1), new User(1, new Date(2), "test", "test", "test", "test", "test"), "test", "test", new GeoPosition(2, new Date(3), 1.0f, 1.0f));
        assertEquals(factoryMaintainer, testMaintainer);
    }
}
