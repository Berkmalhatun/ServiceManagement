package com.sm.service;

import com.sm.dto.request.LoginRequestDto;
import com.sm.dto.request.RegisterRequestDto;
import com.sm.dto.response.RegisterResponseDto;
import com.sm.exception.AuthServiceException;
import com.sm.exception.ErrorType;
import com.sm.mapper.IAuthMapper;
import com.sm.repository.IAuthRepository;
import com.sm.repository.entity.Auth;
import com.sm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
     private final IAuthRepository authRepository;

    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(ErrorType.PASSWORD_MISMATCH);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        save(auth);
        return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
    }

    public String login(LoginRequestDto dto) {
        if(authRepository.findOptionalByEmail(dto.getEmail()).isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        Optional<Auth> auth = authRepository.findOptionalByEmail(dto.getEmail());
        if(!auth.get().getPassword().equals(dto.getPassword()))
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        return "Login successful. You are being redirected to the page.";
    }
}
