package com.iteco.basetask.services;

public interface EmailService {
    void send(String to, String title, String body);
}
