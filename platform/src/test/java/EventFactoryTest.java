import com.eventplatform.factory.EventFactory;
import com.eventplatform.model.Event;
import com.eventplatform.model.GeoPosition;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class EventFactoryTest extends Assert {

    private Event testEvent;

    @Before
    public void setTestEvent() {
        testEvent = new Event(0, new Date(1), "test", "test", new GeoPosition(1, new Date(1), 1.0f, 1.0f), "test", new Date(2));
    }

    @Test
    public void testCreateEventByEvent() {
        Event event = new Event(0, new Date(1), "test", "test", new GeoPosition(1, new Date(1), 1.0f, 1.0f), "test", new Date(2));
        Event factoryEvent = EventFactory.createEvent(event);
        assertEquals(factoryEvent, testEvent);
    }

    @Test
    public void testCreateEventByParams() {
        Event factoryEvent = new Event(0, new Date(1), "test", "test", new GeoPosition(1, new Date(1), 1.0f, 1.0f), "test", new Date(2));
        assertEquals(factoryEvent, testEvent);
    }
}
