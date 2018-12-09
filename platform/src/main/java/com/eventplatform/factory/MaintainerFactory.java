package com.eventplatform.factory;

import com.eventplatform.pojo.CollectionConstants;
import com.eventplatform.pojo.klass.GeoPosition;
import com.eventplatform.pojo.klass.Maintainer;
import com.eventplatform.pojo.klass.User;
import com.eventplatform.repository.MaintainerDataRepository;
import com.eventplatform.repository.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Scope(value = "singleton")
@Component
public class MaintainerFactory {

    @Autowired
    private MaintainerDataRepository maintainerDataRepository;
    @Autowired
    private SequenceDao sequenceDao;

    public Maintainer createMaintainer(String name, String description, User user, GeoPosition geoPosition) {
        Maintainer maintainer = new Maintainer();
        maintainer.setId(sequenceDao.getNextSequenceId(CollectionConstants.COLLECTION_NAME_MAINTAINER));
        maintainer.setCreationDate(new Date(System.currentTimeMillis()));
        maintainer.setName(name);
        maintainer.setDescription(description);
        maintainer.setUser(user);
        maintainer.setGeoPosition(geoPosition);
        maintainerDataRepository.save(maintainer);
        return maintainer;
    }
}
