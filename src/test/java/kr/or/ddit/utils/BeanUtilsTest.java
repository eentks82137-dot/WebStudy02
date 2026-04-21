package kr.or.ddit.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanUtilsTest {
    @Test
    void test1() throws Exception {
        Map<String, Object> testMap1 =
                Map.of("prop1", "TextValue", "prop2", 123, "prop3", LocalDateTime.now());
        TestDummyDto testDummyDto = new TestDummyDto();
        log.info("Before population: {}", testDummyDto);
        BeanUtils.populate(testDummyDto, testMap1);
        log.info("After population: {}", testDummyDto);
    }

    void test2() throws Exception {

        Map<String, String> testMap1 = Map.of("prop1", "TextValue", "prop2", "123", "prop3",
                LocalDateTime.now().toString(), "prop4", LocalDate.now().toString());
        TestDummyDto testDummyDto = new TestDummyDto();
        log.info("Before population: {}", testDummyDto);
        BeanUtils.populate(testDummyDto, testMap1);
        log.info("After population: {}", testDummyDto);
    }

}
