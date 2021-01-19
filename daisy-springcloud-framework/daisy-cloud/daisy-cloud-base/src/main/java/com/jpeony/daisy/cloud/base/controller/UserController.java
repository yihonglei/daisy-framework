package com.jpeony.daisy.cloud.base.controller;

import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class UserController {
    @GetMapping("user")
    @PreAuthorize("hasAuthority('query-demo')")
    public Principal user(Principal user){
        return user;
    }
}
