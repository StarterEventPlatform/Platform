package com.eventplatform.controller;

import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.model.Maintainer;

import java.util.List;

public class MaintainerController implements Controller<Maintainer> {
    @Override
    public Maintainer get(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Maintainer> getAll() {
        return null;
    }

    @Override
    public void create(String JSON) throws ControllerException {

    }

    @Override
    public void create(Maintainer clazz) throws ControllerException {

    }
}
