package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.config.myLogin.SecurityUtil;
import org.example.controller.MemberException;
import org.example.controller.MemberExceptionType;
import org.example.domain.User;
import org.example.dto.UserSignUpDto;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MyLoginUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void signUp(UserSignUpDto userSignUpDto) throws Exception {
        User user = userSignUpDto.toEntity();
        user.encodePassword(passwordEncoder);

        if(userRepository.findByUsername(userSignUpDto.username()).isPresent()){
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_USERNAME);
        }

        userRepository.save(user);
    }
/*
    @Override
    public void update(MemberUpdateDto memberUpdateDto) throws Exception {
        User user = userRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        memberUpdateDto.age().ifPresent(user::updateAge);
        memberUpdateDto.name().ifPresent(user::updateName);
        memberUpdateDto.nickName().ifPresent(user::updateNickName);
    }


    @Override
    public void updatePassword(String checkPassword, String toBePassword) throws Exception {
        User user = userRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        if(!member.matchPassword(passwordEncoder, checkPassword) ) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        member.updatePassword(passwordEncoder, toBePassword);
    }*/


    public void withdraw(String checkPassword) throws Exception {
        User user = userRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        if(!user.matchPassword(passwordEncoder, checkPassword) ) {
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }

        userRepository.delete(user);
    }


/*

    @Override
    public MemberInfoDto getInfo(Long id) throws Exception {
        User findMember = memberRepository.findById(id).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }

    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        User findMember = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }*/
}
