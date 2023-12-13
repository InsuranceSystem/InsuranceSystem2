package aplus.insurancesystem.common.cache;

import lombok.Getter;

@Getter
public enum CacheType {
    INSURANCE_DETAIL(
        CacheConst.INSURANCE_DETAIL,
        3 * 60 * 60,
        100
    ),
    CUSTOMER_INFO(
        CacheConst.CUSTOMER_INFO,
        3 * 60 * 60,
        10_000
    ),
    CUSTOMER_ALL_INFO(
        CacheConst.CUSTOMER_ALL_INFO,
        3 * 60 * 60,
        10_000
    );

    CacheType(String cacheName, int expireAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }

    private final String cacheName;
    private final int expireAfterWrite;
    private final int maximumSize;

}
