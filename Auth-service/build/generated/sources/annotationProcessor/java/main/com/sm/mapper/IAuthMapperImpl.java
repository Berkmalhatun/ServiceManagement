package com.sm.mapper;

import com.sm.dto.request.RegisterRequestDto;
import com.sm.dto.response.RegisterResponseDto;
import com.sm.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T02:29:20+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.email( dto.getEmail() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public RegisterResponseDto toRegisterResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterResponseDto.RegisterResponseDtoBuilder registerResponseDto = RegisterResponseDto.builder();

        registerResponseDto.email( auth.getEmail() );

        registerResponseDto.message( "Kayıt Başarıyla tamamlanmıştır.Giriş yapabilirsiniz." );

        return registerResponseDto.build();
    }
}
