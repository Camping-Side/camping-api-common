package com.camping.common.util.dsl;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


public class DslDateUtil {
    public final static String COMPARE_DATE_FORMAT = "%Y%m%d";
    public final static String READ_DATE_FORMAT = "%Y-%m-%d";

    /**
     * DSL 시작날짜 비교(<=)
     * @param targetDate 대상 QEntity property
     * @param startDate 요청 시작일
     * @return BooleanExpression
     */
    public static BooleanExpression cprStartDate(DateTimePath<LocalDateTime> targetDate, String startDate) {
        return StringUtils.hasText(startDate) ?
                DslDateUtil.getYMDFormat(targetDate).goe(startDate) : null;
    }
    /**
     * DSL 시작날짜 비교(>=)
     * @param targetDate 대상 QEntity property
     * @param endDate 요청 종료일
     * @return BooleanExpression
     */
    public static BooleanExpression cprEndDate(DateTimePath<LocalDateTime> targetDate, String endDate) {
        return StringUtils.hasText(endDate) ?
                DslDateUtil.getYMDFormat(targetDate).loe(endDate) : null;
    }

    /**
     * QEntity 날짜속성을 YYYYMMDD로 변경
     * @param date 대상 QEntity property
     * @return StringTemplate
     */
    public static StringTemplate getYMDFormat(DateTimePath<LocalDateTime> date) {
        return getYMDFormat(date, COMPARE_DATE_FORMAT);
    }

    /**
     * QEntity 날짜속성을 format으로 변경
     * @param date 대상 QEntity property
     * @param format 변환 format
     * @return StringTemplate
     */
    public static StringTemplate getYMDFormat(DateTimePath<LocalDateTime> date, String format) {
        return Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , date
                , ConstantImpl.create(format)
        );
    }
}
