package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.dto.UserDto;
import ro.unibuc.hello.entity.UserEntity;
import ro.unibuc.hello.util.PasswordUtil;

@SpringBootTest
@Tag("IT")
class UserServiceTestIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @Test
    void WHEN_save_new_user_THEN_save_correct_fields() {

        // Arrange
        UserDto user = new UserDto("test_firstName", "test_lastName", "test_password", "test");

        // Act
        UserEntity registeredUser = userService.saveUser(user);

        // Assert
        Assertions.assertEquals(user.getFirstName(), registeredUser.getFirstName());
        Assertions.assertEquals(user.getLastName(), registeredUser.getLastName());
        Assertions.assertTrue(PasswordUtil.verifyUserPassword(user.getPassword(), registeredUser.getPassword(),
                                                                                        registeredUser.getEmail()));
        Assertions.assertEquals(user.getEmail(), registeredUser.getEmail());
    }

    @Test
    void WHEN_get_user_by_id_THEN_return_correct_user() {

        // Arrange
        UserDto user = new UserDto("test_firstName", "test_lastName", "test_password", "test_1");

        // Act
        UserEntity testUser = userService.saveUser(user);

        UserEntity registeredUser = userService.getUser(testUser.getId());

        // Assert
        Assertions.assertEquals(testUser.getId(), registeredUser.getId());
    }

    @Test
    void WHEN_get_user_by_email_THEN_return_correct_user() {

        UserDto user = new UserDto("test_firstName", "test_lastName", "test_password", "test_email");

        userService.saveUser(user);

        // Act
        UserEntity registeredUser = userService.getUserByEmail(user.getEmail());

        // Assert
        Assertions.assertEquals(user.getEmail(), registeredUser.getEmail());
    }

}