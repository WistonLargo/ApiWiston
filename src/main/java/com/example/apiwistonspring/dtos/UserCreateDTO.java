package com.example.apiwistonspring.dtos;

public record UserCreateDTO(String email, String username, String password,String... roles) {
}