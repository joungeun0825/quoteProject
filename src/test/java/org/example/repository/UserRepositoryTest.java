package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired UserRepository userRepository;
    @Autowired
    EntityManager em;

    @AfterEach
    public void after(){
        em.clear();
    }

    @Test
    public void 회원저장_성공() throws Exception {
        //given
        User user = User.builder().nickname("NickName1").password("1234567890").username("a").build();

        //when
        User saveMember = userRepository.save(user);

        //then
        User findMember = userRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));//아직 예외 클래스를 만들지 않았기에 RuntimeException으로 처리하겠습니다.

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(user);
    }
    @Test
    public void 오류_회원가입시_아이디가_없음() throws Exception {
        //given
        User user = User.builder().password("1234567890").build();

        //when, then
        assertThrows(Exception.class, () -> userRepository.save(user));
    }
    @Test
    public void 성공_회원수정() throws Exception {
        //given
        User user1 = User.builder().username("username").password("1234567890").nickname("NickName1").build();
        userRepository.save(user1);
        em.clear();

        String updatePassword = "updatePassword";
        String updateName = "updateName";
        String updateNickName = "updateNickName";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //when
        User findMember = userRepository.findById(user1.getId()).orElseThrow(() -> new Exception());

        findMember.updateUsername(updateName);
        findMember.updateNickName(updateNickName);
        findMember.updatePassword(passwordEncoder,updatePassword);
        em.flush();

        //then
        User findUpdateMember = userRepository.findById(findMember.getId()).orElseThrow(() -> new Exception());

        assertThat(findUpdateMember).isSameAs(findMember);
        assertThat(passwordEncoder.matches(updatePassword, findUpdateMember.getPassword())).isTrue();
        assertThat(findUpdateMember.getUsername()).isEqualTo(updateName);
        assertThat(findUpdateMember.getUsername()).isNotEqualTo(user1.getUsername());
    }
}