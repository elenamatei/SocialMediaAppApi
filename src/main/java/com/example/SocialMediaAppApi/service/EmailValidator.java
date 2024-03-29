package com.example.SocialMediaAppApi.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        final String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}