package br.com.les.amstore.service;

import java.util.List;

public interface IGenericService<T> {
    public List<T> findAll();
    public T findById(Long id);
    public T saveAndFlush(T object);
}
