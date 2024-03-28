package org.example.controller;

public abstract class BaseException extends RuntimeException{
    public abstract BaseExceptionType getExceptionType();
}