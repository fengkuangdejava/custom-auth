package com.example.auth.service;

import com.example.auth.model.ClientDetail;

import javax.servlet.http.HttpServletRequest;

public interface ClientService {
    ClientDetail checkClient(HttpServletRequest httpServletRequest);
}
