package kr.co.udid.ciderpay.service.common;

public interface JpaService<T> {
    public Iterable<T> findAll();
}
