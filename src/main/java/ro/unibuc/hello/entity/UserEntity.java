package ro.unibuc.hello.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Id;
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

}



