package org.example.expert.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class PasswordEncoderTest {

    @InjectMocks
    private PasswordEncoder passwordEncoder;

    @Test
    void matches_메서드가_정상적으로_동작한다() {
        // given
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // when(matches 메서드의 첫번째 파라미터는 평문이고, 두번쨰 파라미터는 변환된 코드이다.
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        boolean notMatches = passwordEncoder.matches(encodedPassword, rawPassword);
        boolean notMatches2 = passwordEncoder.matches("wrongPassword", encodedPassword);

        // then
        assertTrue(matches);
        assertFalse(notMatches);
        assertFalse(notMatches2);
    }
}
