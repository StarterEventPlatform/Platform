package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.JsonParserException;
import com.eventplatform.factory.MaintainerFactory;
import com.eventplatform.model.Maintainer;
import com.eventplatform.util.JsonParser;
import com.eventplatform.util.container.MaintainerContainer;

import java.util.List;
import java.util.Map;

public class MaintainerController implements Controller<Maintainer> {
    private JsonParser jsonParser;
    private MaintainerContainer container;

    public MaintainerController() {
        this.container = new MaintainerContainer();
        this.jsonParser = JsonParser.getJsonParser();
    }

    @Override
    public Maintainer get(int id) throws NotFoundControllerException {
        try {
            return container.getValue(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundControllerException();
        }
    }

    @Override
    public void remove(int id) throws NotFoundControllerException {
        try {
            container.remove(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundControllerException();
        }
    }

    @Override
    public List<Maintainer> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }

    @Override
    public void create(String JSON) throws ControllerException {
        /*try {
            Map<String, String> maintainerParams = jsonParser.getParsedJson(JSON);
            Maintainer maintainer = MaintainerFactory.createMaintainer(maintainerParams);
            container.addValue(maintainer.getId(), maintainer);
        } catch (JsonParserException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }*/
    }

    @Override
    public void create(Maintainer clazz) throws ControllerException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
