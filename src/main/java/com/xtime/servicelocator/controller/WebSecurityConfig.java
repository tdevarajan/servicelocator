/*
 * Copyright (c) 2014 Xtime, Inc.  All rights reserved.
 */
package com.xtime.servicelocator.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/swagger*").authorizeRequests().anyRequest().fullyAuthenticated().and().
    httpBasic().and().
    csrf().disable();
  }
  
}