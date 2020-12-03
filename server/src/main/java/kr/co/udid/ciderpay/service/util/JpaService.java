package kr.co.udid.ciderpay.service.util;

public interface JpaService<T> {
    public Iterable<T> findAll();
}
