package com.sm.repository.entity;

import com.sm.repository.enums.ERole;
import com.sm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role=ERole.ENGINEER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status= EStatus.ACTIVE;
}
