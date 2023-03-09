package ro.unibuc.hello.dto;


import lombok.Data;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class UserDto {
    private AtomicLong id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}



