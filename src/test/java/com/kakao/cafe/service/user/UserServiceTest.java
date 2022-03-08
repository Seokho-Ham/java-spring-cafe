package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserForm;
import com.kakao.cafe.repository.user.UserMemoryRepository;
import com.kakao.cafe.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    UserRepository repository = new UserMemoryRepository();
    UserService service = new UserServiceImpl(repository);

    @BeforeEach
    void clear() {
        repository.clear();
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        UserForm form = new UserForm("forky", "1111", "hello@spring.com", "퐄퐄퐄");
        service.createUser(form);

        User user = service.findSingleUser(1L).get();

        assertThat(user.getUserId()).isEqualTo("forky");
        assertThat(user.getName()).isEqualTo("퐄퐄퐄");

    }

    @Test
    @DisplayName("중복된 id로 회원가입 시 예외를 던진다.")
    void validateDuplicate() {
        //given
        UserForm form = new UserForm("forky", "1111", "hello@spring.com", "퐄퐄퐄");
        service.createUser(form);

        UserForm form2 = new UserForm("forky", "222", "hello@summer.com", "다른 포키");
        assertThatThrownBy(() -> service.createUser(form))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("사용자 ID가 중복됩니다.");
    }
}
