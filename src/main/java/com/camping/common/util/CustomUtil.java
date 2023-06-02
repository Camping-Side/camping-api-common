package com.camping.common.util;

import com.camping.common.domain.dto.PageDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

public class CustomUtil {
    /**
     * ============================================================
     * ======================== 변환 유틸 Start =====================
     * ============================================================
     */

    /**
     * Object to Object 변환
     * 1. private field까지 매핑
     * 2. 타입과 field명까지 일치해야 함(엄격모드)
     *
     * @param entity 기준 클래스
     * @param clazz 변환 클래스
     * @return O clazz Class 반환
     */
    public static <I, O> O convertClass(I entity, Class<? extends O> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        AbstractConverter<LocalDateTime, String> dateToStringConverter = new AbstractConverter<>() {
            @Override
            protected String convert(LocalDateTime localDateTime) {
                DateTimeFormatter dateTImeFormatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATE_DASH);
                return localDateTime.format(dateTImeFormatter);
            }
        };

        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(dateToStringConverter);

        return modelMapper.map(entity, clazz);
    }

    /**
     * Request 요청으로 넘어온 PageDto 값을
     * 데이터레이어에서 사용하기 위해 변환
     *
     * @param vo PageDto를 상속받는 모든 Object
     * @return PageRequest Dsl에서 사용하기 위해 반환
     */
    public static PageRequest convertPageVo(@NonNull PageDto vo) {
        return PageRequest.of(vo.getPage(), vo.getSize());
    }

    /**
     * ============================================================
     * ======================== 변환 유틸 End =======================
     * ============================================================
     */

    /**
     * ============================================================
     * ======================== Null 유틸 Start ====================
     * ============================================================
     */

    /**
     * null일 경우 true
     * 문자 undefined와 null도 체크함
     *
     * @param str null 체크 대상
     * @return boolean
     */
    public static boolean isNull(@Nullable String str) {
        return !StringUtils.hasText(str) ||  "undefined".equals(str) || "null".equals(str);
    }

    /**
     * null이 아닐 경우 true
     * 문자 undefined와 null도 체크함
     *
     * @param str null 체크 대상
     * @return boolean
     */
    public static boolean isNotNull(@Nullable String str) {
        return !(!StringUtils.hasText(str) ||  "undefined".equals(str) || "null".equals(str));
    }

    /**
     *
     *
     * @param other null 체크 대상
     * @return Collection<O> param type 반환
     */
    public static <O> Collection<O> safeList(@Nullable Collection<O> other) {
        return other == null ? Collections.emptyList() : other;
    }
    /**
     * ============================================================
     * ======================== Null 유틸 End ====================
     * ============================================================
     */
}
