package pl.skotniczny.app.user.mapper;

import org.springframework.http.HttpStatus;
import pl.skotniczny.app.user.dto.request.CreateUserRequestDto;
import pl.skotniczny.app.user.dto.response.*;
import pl.skotniczny.app.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapFromCreateUserRequestDtoToUser(CreateUserRequestDto requestDto) {
        return new User(requestDto.username(), requestDto.password());
    }

    public static UserDto mapFromUserToUserDto(User user) {
        return new UserDto(user.getId(), user.getPassword(), user.getUsername());
    }

    public static CreateUserResponseDto mapFromUserToCreateUserDto(User user) {
        return new CreateUserResponseDto(user.getId(), user.getUsername());
    }

    public static GetAllUsersResponseDto mapFromUserToGetAllUsersResponseDto(List<User> users) {
        List<UserDto> userDtos = users.stream()
                .map(UserMapper::mapFromUserToUserDto).collect(Collectors.toList());
        return new GetAllUsersResponseDto(userDtos);
    }

    public static GetUserResponseDto mapFromUserToGetUserResponseDto(User user) {
        return new GetUserResponseDto(user.getUsername());
    }

    public static DeleteUserResponseDto mapFromUserToDeleteUserResponseDto(String username) {
        return new DeleteUserResponseDto("You deleted user with login:  " + username, HttpStatus.OK);
    }


}
