package uz.pdp.codingbatteam3.service;

import uz.pdp.codingbatteam3.entity.UserEntity;

import java.util.List;

public interface BaseService<T, R> {
    List<R> list();

    boolean add(T t);

    boolean delete(Integer id);

    R update(Integer id, T t);

    R get(Integer id);
    R getByName(String name);
}
