package com.example.demo.exampleSpringBootApp.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    Date timestamp;
    String message;
    String detail;
}
