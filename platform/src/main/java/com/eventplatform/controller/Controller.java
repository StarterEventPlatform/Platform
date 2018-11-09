package com.eventplatform.controller;

public interface Controller<T> {
    public T get(int id);

    public void remove(int id);
}
