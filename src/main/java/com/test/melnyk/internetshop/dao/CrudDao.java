package com.test.melnyk.internetshop.dao;

import java.util.List;

/**
 * Main operations of Data Access Object layer
 *
 * @param <T> Type variable.
 */
public interface CrudDao<T> {
    /**
     * select object with specified id
     *
     * @param id
     * @return
     */
    T select(int id);

    /**
     * create object
     *
     * @param t
     * @return
     */
    boolean create(T t);

    /**
     * delete object with specified id
     *
     * @param id
     */
    void delete(int id);

    /**
     * update object
     *
     * @param t
     * @return
     */
    T update(T t);

    /**
     * get List parametrized Object
     *
     * @return
     */
    List<T> findAll();

}