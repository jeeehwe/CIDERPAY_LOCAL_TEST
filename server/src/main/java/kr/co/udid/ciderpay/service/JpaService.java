package kr.co.udid.ciderpay.service;

public interface JpaService<T> {
    public Iterable<T> findAll();
}
