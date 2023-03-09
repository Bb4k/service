package ro.unibuc.hello.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.dto.UserDto;
import ro.unibuc.hello.entity.UserEntity;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() { }


    public UserEntity saveUser(UserDto userDTO) {

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(userDTO, userEntity);

        return userRepository.insert(userEntity);
    }

    public UserEntity getUser(String userId) {

        ObjectId objId = new ObjectId(userId);

        Optional<UserEntity> user = userRepository.findById(objId.toString());

        return user.get();

    }


}
