package com.eventplatform.tmp.oldcontainers;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.domain.model.Maintainer;

import java.util.*;

public class MaintainerContainer implements Container<Maintainer> {
    private Map<Integer, Maintainer> maintainerMap;

    public MaintainerContainer() {
        maintainerMap = new HashMap<>();
    }

    @Override
    public void addValue(Integer key, Maintainer value) throws AlreadyExistsContainerException {
        if (maintainerMap.get(key) == null)
            maintainerMap.put(key, value);
        else throw new AlreadyExistsContainerException();
    }

    @Override
    public Maintainer getValue(Integer key) throws NotFoundContainerException {
        Maintainer maintainer = maintainerMap.get(key);
        if (maintainer != null)
            return maintainer;
        else throw new NotFoundContainerException();
    }

    @Override
    public void remove(Integer key) throws NotFoundContainerException {
        Maintainer maintainer = maintainerMap.get(key);
        if (maintainer != null)
            maintainerMap.remove(key);
        else throw new NotFoundContainerException();
    }

    @Override
    public List<Maintainer> getAllValues() throws EmptyContainerException {
        List<Maintainer> list = new ArrayList<>(maintainerMap.values());
        if (list.size() == 0)
            throw new EmptyContainerException();
        return Collections.unmodifiableList(list);
    }
}
