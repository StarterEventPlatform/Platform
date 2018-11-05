import com.eventplatform.factory.GeoPosistionFactory;
import com.eventplatform.model.GeoPosition;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class GeoPositionFactoryTest extends Assert {

    private GeoPosition testGeoPosition;

    @Before
    public void setTestGeoPosition() {
        testGeoPosition = new GeoPosition(0, new Date(1), 1.0f, 1.0f);
    }

    @Test
    public void testCreateGeoPositionByGeoPosition() {
        GeoPosition geoPosition = new GeoPosition(0, new Date(1), 1.0f, 1.0f);
        GeoPosition factoryGeoPosition = GeoPosistionFactory.createGeoPosition(geoPosition);
        assertEquals(factoryGeoPosition, testGeoPosition);
    }

    @Test
    public void testCreateGeoPositionByParams() {
        GeoPosition factoryGeoPosition = new GeoPosition(0, new Date(1), 1.0f, 1.0f);
        assertEquals(factoryGeoPosition, testGeoPosition);
    }
}
