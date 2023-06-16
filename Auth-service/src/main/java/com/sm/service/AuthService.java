package com.sm.service;

import com.sm.dto.request.LoginRequestDto;
import com.sm.dto.request.RegisterRequestDto;
import com.sm.dto.response.RegisterResponseDto;
import com.sm.exception.AuthServiceException;
import com.sm.exception.ErrorType;
import com.sm.mapper.IAuthMapper;
import com.sm.rabbitmq.model.MailModel;
import com.sm.rabbitmq.producer.MailProducer;
import com.sm.repository.IAuthRepository;
import com.sm.repository.entity.Auth;
import com.sm.repository.enums.EStatus;
import com.sm.utility.CodeGenerator;
import com.sm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
     private final IAuthRepository authRepository;
     private final MailProducer mailProducer;

    public AuthService(IAuthRepository authRepository, MailProducer mailProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.mailProducer = mailProducer;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(ErrorType.PASSWORD_MISMATCH);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        String code = CodeGenerator.generateCode();
        auth.setActivationCode(code);
        save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
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

    public String actived(Long id,String activationCode) {
        Optional<Auth> auth = authRepository.findById(id);
        if(auth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        if (!auth.get().getActivationCode().equals(activationCode))
            throw new AuthServiceException(ErrorType.ACTIVATION_CODE_UNMATCH);
        auth.get().setStatus(EStatus.ACTIVE);
        update(auth.get());
        return "User activeted.";
    }
}
