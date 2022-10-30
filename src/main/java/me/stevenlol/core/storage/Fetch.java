package me.stevenlol.core.storage;

import java.sql.SQLException;

public interface Fetch<T> {

    void give(T t) throws SQLException;

}
