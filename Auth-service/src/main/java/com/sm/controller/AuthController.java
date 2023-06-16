package com.sm.controller;

import com.sm.dto.request.LoginRequestDto;
import com.sm.dto.request.RegisterRequestDto;
import com.sm.dto.response.RegisterResponseDto;
import com.sm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sm.constants.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
        public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto){
     return ResponseEntity.ok(authService.register(dto));
 }
         @GetMapping(LOGIN)
        public ResponseEntity<String> login(LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
 }
@PostMapping(ACTIVE)
 public ResponseEntity<String> activet(Long id,String activationCode){
        return ResponseEntity.ok(authService.actived(id,activationCode));
 }
}
