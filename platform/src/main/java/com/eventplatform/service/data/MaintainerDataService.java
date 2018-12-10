package com.eventplatform.service.data;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.exception.dataservice.NotFoundDataServiceException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.factory.MaintainerFactory;
import com.eventplatform.domain.model.GeoPosition;
import com.eventplatform.domain.model.Maintainer;
import com.eventplatform.domain.model.User;
import com.eventplatform.repository.MaintainerDataRepository;
import com.eventplatform.util.container.PojoContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(value = "singleton")
@Service
public class MaintainerDataService implements DataService<Maintainer> {
    @Autowired
    private Serializer serializer;
    @Autowired
    private MaintainerFactory maintainerFactory;
    private MaintainerDataRepository maintainerDataRepository;
    private PojoContainer<Maintainer> container;

    public MaintainerDataService(MaintainerDataRepository maintainerDataRepository) {
        this.container = new PojoContainer<>();
        this.maintainerDataRepository = maintainerDataRepository;
        maintainerDataRepository.findAll().forEach(value -> {
            try {
                container.addValue(value.getId(), value);
            } catch (AlreadyExistsContainerException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Maintainer get(int id) throws NotFoundDataServiceException {
        try {
            return container.getValue(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundDataServiceException();
        }
    }

    @Override
    public void remove(int id) throws NotFoundDataServiceException {
        try {
            container.remove(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundDataServiceException();
        }
    }

    @Override
    public List<Maintainer> getAll() throws EmptyDataServiceException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyDataServiceException();
        }
    }

    public void create(String text, String textType) throws DataServiceException {
        try {
            Maintainer maintainer = (Maintainer) serializer.deserialize(text, SerializerConstants.MAINTAINER_CLAZZ, textType);
            container.addValue(maintainer.getId(), maintainer);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }

    public void create(String name, String description, User user, GeoPosition geoPosition) throws DataServiceException {
        try {
            Maintainer maintainer = maintainerFactory.createMaintainer(name, description, user, geoPosition);
            container.addValue(maintainer.getId(), maintainer);
        } catch (AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }

    @Override
    public void create(Maintainer clazz) throws DataServiceException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }
}
