package uz.ilmnajot.college.service;


import uz.ilmnajot.college.model.common.ApiResponse;

public interface BaseService<T, R> {

    ApiResponse create(T t);
    ApiResponse getById(R r);
    ApiResponse getAll();

    ApiResponse getAll(int page, int size);

    ApiResponse deleteById(R r);
    ApiResponse update(T t, R r);
}
