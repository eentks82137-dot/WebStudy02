package kr.or.ddit.validate;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.ListUtils;

import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateUtils {

    private static final Validator validator;
    static {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure()
                .defaultLocale(Locale.KOREAN).buildValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> Map<String, List<String>> validate(T target, Class<?>... groups) {

        Set<ConstraintViolation<T>> constraintViolation = validator.validate(target, groups);

        Map<String, List<String>> errors = constraintViolation.stream().collect(Collectors.toMap(
                cv -> cv.getPropertyPath().toString(),
                cv -> List.of(cv.getMessage()),
                (l1, l2) -> ListUtils.union(l1, l2)));
        // log.info("{}", errors);
        return errors;
    }

}
