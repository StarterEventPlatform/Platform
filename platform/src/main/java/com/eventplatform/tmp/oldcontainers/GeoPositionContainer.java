package com.eventplatform.tmp.oldcontainers;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.domain.model.GeoPosition;

import java.util.*;

public class GeoPositionContainer implements Container<GeoPosition> {
    private Map<Integer, GeoPosition> geoPositionMap;

    public GeoPositionContainer() {
        geoPositionMap = new HashMap<>();
    }

    @Override
    public void addValue(Integer key, GeoPosition value) throws AlreadyExistsContainerException {
        if (geoPositionMap.get(key) == null)
            geoPositionMap.put(key, value);
        else throw new AlreadyExistsContainerException();
    }

    @Override
    public GeoPosition getValue(Integer key) throws NotFoundContainerException {
        GeoPosition geoPosition = geoPositionMap.get(key);
        if (geoPosition != null) return geoPosition;
        else throw new NotFoundContainerException();
    }

    @Override
    public void remove(Integer key) throws NotFoundContainerException {
        GeoPosition geoPosition = geoPositionMap.get(key);
        if (geoPosition != null)
            geoPositionMap.remove(key);
        else throw new NotFoundContainerException();
    }

    @Override
    public List<GeoPosition> getAllValues() throws EmptyContainerException {
        List<GeoPosition> list = new ArrayList<>(geoPositionMap.values());
        if (list.size() == 0)
            throw new EmptyContainerException();
        return Collections.unmodifiableList(list);
    }
}
