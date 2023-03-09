package ro.unibuc.hello.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

}



