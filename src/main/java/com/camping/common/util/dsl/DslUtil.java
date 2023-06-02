package com.camping.common.util.dsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.util.StringUtils;

public class DslUtil {
    /**
     * Integer 타입 값 = 비교
     */
    public static BooleanExpression cprEq(NumberPath<Integer> target, Integer number) {
        return number == null ? null : target.eq(number);
    }
    /**
     * Long 타입 값 = 비교
     */
    public static BooleanExpression cprEq(NumberPath<Long> target, Long number) {
        return number == null ? null : target.eq(number);
    }
    /**
     * String 타입 값  = 비교
     */
    public static BooleanExpression cprEq(StringPath target, String str) {
        return StringUtils.hasText(str) ? target.eq(str) : null;
    }
    /**
     * String 타입 값  like 비교
     */
    public static BooleanExpression cprLike(StringPath target, String str) {
        return StringUtils.hasText(str) ? target.like("%" + str + "%") : null;
    }
    /**
     * Enum 타입 값  = 비교
     */
    public static <T extends Enum<T>> BooleanExpression cprEq(EnumPath<T> target, T enumObject) {
        return enumObject == null ? null : target.eq(enumObject);
    }
}
