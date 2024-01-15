package irish.bla.userservice.util;

import irish.bla.userservice.dto.TransactionRequestDto;
import irish.bla.userservice.dto.TransactionResponseDto;
import irish.bla.userservice.dto.TransactionStatus;
import irish.bla.userservice.dto.UserDto;
import irish.bla.userservice.entity.User;
import irish.bla.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

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

    public static UserTransaction toEntity(TransactionRequestDto dto) {
        return UserTransaction.builder()
                .userId(dto.getUserId())
                .amount(dto.getAmount())
                .transactionDate(LocalDateTime.now())
                .build();
    }

    public static TransactionResponseDto toDto(TransactionRequestDto dto, TransactionStatus status) {
       return TransactionResponseDto.builder()
               .status(status)
               .amount(dto.getAmount())
               .userId(dto.getUserId())
               .build();
    }
}
