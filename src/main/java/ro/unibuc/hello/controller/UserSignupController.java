package ro.unibuc.hello.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.hello.dto.UserDto;
import ro.unibuc.hello.entity.UserEntity;
import ro.unibuc.hello.service.UserService;


@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserSignupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSignupController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> createTask(@RequestBody UserDto userDto) {
        LOGGER.info("RegisterController: " + userDto);
        UserEntity user = userService.saveUser(userDto);

        return ResponseEntity.ok(user);
    }
}

