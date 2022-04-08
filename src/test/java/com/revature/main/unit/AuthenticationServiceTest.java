package com.revature.main.unit;

import com.revature.main.exception.BadParameterException;
import com.revature.main.model.User;
import com.revature.main.repository.UserRepository;
import com.revature.main.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authService;

    /*
        User login(String username, String password);

        Positive test case:
            Return a User object based on the username and password if exists
        Positive test case:
            Valid username and password, but user has whitespace before and after,
            return User object with trimmed whitespace
        Negative test case:
            Invalid username/password, throws an InvalidLoginException
        Negative test case:
            Blank username/password, throws BadParameterException
        Negative test case:
            Takes more than five seconds to execute the login method
     */
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void positiveTest_login_validUsernameAndPassword() throws FailedLoginException, BadParameterException {

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("patronius");
        mockUser.setPassword("password");
        mockUser.setEthAddress("0xfaceded");

        when(userRepository.findByUsernameAndPassword(eq("patronius"), eq("password"))).thenReturn(
                mockUser
        );

        User actual = authService.login("patronius", "password");

        User expected = new User();
        expected.setId(1L);
        expected.setUsername("patronius");
        expected.setPassword("password");
        expected.setEthAddress("0xfaceded");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void positiveTest_login_validUsernameAndPasswordWithWhitespace() throws FailedLoginException, BadParameterException {

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("patronius");
        mockUser.setPassword("password");
        mockUser.setEthAddress("0xaaaa");

        when(userRepository.findByUsernameAndPassword(eq("patronius"), eq("password"))).thenReturn(
                mockUser
        );

        User actual = authService.login("      patronius     ", "   password  ");

        User expected = new User();
        expected.setId(1L);
        expected.setUsername("patronius");
        expected.setPassword("password");
        expected.setEthAddress("0xaaaa");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void negativeTest_login_invalidUsernameAndPassword() throws FailedLoginException {
        Assertions.assertThrows(FailedLoginException.class, () -> {
            authService.login("invalid", "invalid");
        });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void negativeTest_allWhitespaceOrBlank() {
        Assertions.assertThrows(BadParameterException.class, () -> {
            authService.login("    ", "");
        });
    }

}
