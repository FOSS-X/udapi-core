package com.gupta.udapi.services.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ApplicationContextFactory {

    private static ApplicationContext applicationContext;

    @Autowired
    private ApplicationContext springInjectedContext;

    @PostConstruct
    private void init() {
        applicationContext = this.springInjectedContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
