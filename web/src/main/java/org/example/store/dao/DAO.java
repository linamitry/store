package org.example.store.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<Entity, Key> {

    List<Entity> readAll();
    void create(Entity model);
    boolean delete(Key key);
    boolean update(Entity entity);
    Optional<Entity> findById(Key key);
}

