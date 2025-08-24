package com.rainbowsea.sproutforgeaicodebackend.ratelimter.enums;

/**
 * 限流级别
 */
public enum RateLimitType {

    /**
     * 接口级别限流
     */
    API,

    /**
     * 用户级别限流
     */
    USER,

    /**
     * IP级别限流
     */
    IP
}