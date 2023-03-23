package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserDtoTest {

    @InjectMocks
    UserDto userDtoMock = Mockito.mock(UserDto.class);
    UserDto userDto;

    @BeforeEach
    void setup() {
        userDto = new UserDto("1", "1", "1", "1");
    }

    @Test
    void WHEN_id_getter_is_called_THEN_return_correct_id_value(){
        userDtoMock.getId();
        verify(userDtoMock, times(1)).getId();
    }

    @Test
    void WHEN_first_name_getter_is_called_THEN_return_correct_first_name_value(){
        Assertions.assertEquals("1", userDto.getFirstName());
    }

    @Test
    void WHEN_last_name_getter_is_called_THEN_return_correct_last_name_value(){
        Assertions.assertEquals("1", userDto.getLastName());
    }

    @Test
    void WHEN_password_getter_is_called_THEN_return_correct_password_value(){
        Assertions.assertEquals("1", userDto.getPassword());
    }

    @Test
    void WHEN_email_getter_is_called_THEN_return_correct_email_value(){
        Assertions.assertEquals("1", userDto.getEmail());
    }

}