package com.example.Bahnhof_Ferienprojekt;

import com.example.Bahnhof_Ferienprojekt.models.Bahnhof;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BahnhoefeValidator implements Validator {

    /**
    * This Validator validates *just* Person instances
    */
    public boolean supports(Bahnhof bahnhof) {
        return Bahnhof.class.equals(bahnhof);
    }

    public void validate(Bahnhof bahnhof, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        
        if (bahnhof.getName().equals("test")) {
            e.rejectValue("name", "negativevalue");
        } 
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }
}
