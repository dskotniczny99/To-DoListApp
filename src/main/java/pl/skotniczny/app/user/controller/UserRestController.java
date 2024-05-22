package pl.skotniczny.app.user.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.skotniczny.app.user.dto.request.CreateUserRequestDto;
import pl.skotniczny.app.user.dto.response.CreateUserResponseDto;
import pl.skotniczny.app.user.dto.response.DeleteUserResponseDto;
import pl.skotniczny.app.user.dto.response.GetAllUsersResponseDto;
import pl.skotniczny.app.user.dto.response.GetUserResponseDto;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.service.UserDeleter;
import pl.skotniczny.app.user.service.UserRetriever;
import pl.skotniczny.app.user.service.UserService;

import java.util.List;

import static pl.skotniczny.app.user.mapper.UserMapper.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserRetriever userRetriever;
    private final UserDeleter userDeleter;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> registerUser(@RequestBody CreateUserRequestDto requestDto) {
        User user = mapFromCreateUserRequestDtoToUser(requestDto);
        User savedUser = userService.saveUser(user);
        CreateUserResponseDto body = mapFromUserToCreateUserDto(savedUser);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponseDto> getAllUsers() {
        List<User> allUsers = userRetriever.findAll();
        GetAllUsersResponseDto response = mapFromUserToGetAllUsersResponseDto(allUsers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetUserResponseDto> getUserByUsername(@PathVariable String username) {
        User user = userRetriever.getUserByUsername(username);
        GetUserResponseDto responseDto = mapFromUserToGetUserResponseDto(user);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<DeleteUserResponseDto> deleteUserByUsername(@PathVariable String username) {
        userDeleter.deleteUserByUsername(username);
        DeleteUserResponseDto body = mapFromUserToDeleteUserResponseDto(username);
        return ResponseEntity.ok(body);
    }


}
