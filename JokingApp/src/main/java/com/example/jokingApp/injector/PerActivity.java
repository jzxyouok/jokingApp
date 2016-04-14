package com.example.jokingApp.injector;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by idea-pc on 2016/4/12.
 */
@Documented
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}


