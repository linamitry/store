package org.example.store.dao;

import java.util.List;

public interface DAO<Entity, Key,S> {

    List<Entity> readAll();
    void create(Entity model);
    Entity readById(Key key);
    boolean delete(Key key);
    boolean update(Entity entity);
    Entity find(S e, S p);
}

