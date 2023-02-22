package uz.pdp.codingbatteam3.service;

public interface BaseService<T, R> {
    R getList();

    R add(T t);

    R deleteById(Integer id);

    R update(Integer id, T t);

    R getById(Integer id);
}
