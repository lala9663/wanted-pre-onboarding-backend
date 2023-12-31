package com.example.wantedpreonboardingbackend.user.controller;

import com.example.wantedpreonboardingbackend.user.dto.RegisterUserDto;
import com.example.wantedpreonboardingbackend.user.repository.UserRepository;
import com.example.wantedpreonboardingbackend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Operation(summary = "회원가입", description = "회원가입을 시도합니다.", tags = {"User Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/signUp")
    public ResponseEntity<String> signup(@RequestBody RegisterUserDto registerUserDto) {
        try {
            long userId = userService.registerUser(registerUserDto);
            return ResponseEntity.ok("회원가입되었습니다. id는: " + userId + " 입니다");
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
