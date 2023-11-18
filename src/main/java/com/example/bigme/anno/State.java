package com.example.bigme.anno;


import com.example.bigme.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
//元注解
@Constraint(validatedBy = { StateValidation.class })

public @interface State {


    String message() default "state参数的值只能是已发布或者草稿";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
