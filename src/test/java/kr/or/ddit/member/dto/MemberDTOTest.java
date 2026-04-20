package kr.or.ddit.member.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.ListUtils;
import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUpBeforeClass() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure()
                .defaultLocale(Locale.KOREAN).buildValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    void test1() {
        MemberDTO memberDTO = MemberDTO.builder()
                .memId("a1234")
                .memPass("abc")
                .memMail("aa@asd.as")
                .memBir(LocalDate.now().minusDays(1))
                .memMileage(100)
                .build();
        Set<ConstraintViolation<MemberDTO>> constraintViolation = validator.validate(memberDTO);
        constraintViolation.forEach(cv -> {
            String propertyPath = cv.getPropertyPath().toString();
            String message = cv.getMessage();
            log.info("Property: {}, Message: {}", propertyPath, message);
        });
        @SuppressWarnings("unchecked")
        Map<String, List<String>> errors = constraintViolation.stream().collect(Collectors.toMap(
                cv -> cv.getPropertyPath().toString(),
                cv -> List.of(cv.getMessage()),
                (l1, l2) -> ListUtils.union(l1, l2)));
        log.info("{}", errors);
    }

    @Test
    void testInsert() {
        MemberDTO memberDTO = MemberDTO.builder()

                .build();
        Set<ConstraintViolation<MemberDTO>> constraintViolation = validator.validate(memberDTO, InsertGroup.class);

        @SuppressWarnings("unchecked")
        Map<String, List<String>> errors = constraintViolation.stream().collect(Collectors.toMap(
                cv -> cv.getPropertyPath().toString(),
                cv -> List.of(cv.getMessage()),
                (l1, l2) -> ListUtils.union(l1, l2)));
        errors.forEach((k, v) -> {
            log.info("{} : {}", k, v);
        });
    }

    @Test
    void testUpdate() {
        MemberDTO memberDTO = MemberDTO.builder()
                .build();
        Set<ConstraintViolation<MemberDTO>> constraintViolation = validator.validate(memberDTO, UpdateGroup.class);

        @SuppressWarnings("unchecked")
        Map<String, List<String>> errors = constraintViolation.stream().collect(Collectors.toMap(
                cv -> cv.getPropertyPath().toString(),
                cv -> List.of(cv.getMessage()),
                (l1, l2) -> ListUtils.union(l1, l2)));
        errors.forEach((k, v) -> {
            log.info("{} : {}", k, v);
        });
    }

    @Test
    void testDelete() {
        MemberDTO memberDTO = MemberDTO.builder()
                .memId("as")
                .build();
        Set<ConstraintViolation<MemberDTO>> constraintViolation = validator.validate(memberDTO, DeleteGroup.class);

        @SuppressWarnings("unchecked")
        Map<String, List<String>> errors = constraintViolation.stream().collect(Collectors.toMap(
                cv -> cv.getPropertyPath().toString(),
                cv -> List.of(cv.getMessage()),
                (l1, l2) -> ListUtils.union(l1, l2)));
        errors.forEach((k, v) -> {
            log.info("{} : {}", k, v);
        });
    }

    @Test
    void testValidateUtils() {
        MemberDTO memberDTO = MemberDTO.builder()
                .memComtel("041-836-96811")
                .memHometel("asd")
                .memHp("000-0000-0000")
                .build();
        Map<String, List<String>> errors = ValidateUtils.validate(memberDTO, InsertGroup.class);
        errors.forEach((k, v) -> {
            log.info("{} : {}", k, v);
        });
    }

}
