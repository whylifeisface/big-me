package com.example.bigme.validation;

import com.example.bigme.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


//提供指定校验

public class StateValidation implements ConstraintValidator<State, String> {

    /**
     * @param s                          要校验的值
     * @param constraintValidatorContext
     * @return boolean 是否通过校验
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        if (s.equals("已发布") || s.equals("草稿")) {
            return true;

        }

        return false;
    }
}
