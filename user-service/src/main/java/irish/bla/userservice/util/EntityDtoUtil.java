package irish.bla.userservice.util;

import irish.bla.userservice.dto.UserDto;
import irish.bla.userservice.entity.User;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return user;
    }
}
