package irish.bla.userservice.controller;

import irish.bla.userservice.dto.UserDto;
import irish.bla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("all")
    public Flux<UserDto> all() {
        return this.userService.all();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDto>> getById(@PathVariable Integer id) {
        return this.userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> create(@RequestBody Mono<UserDto> userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> update(@PathVariable Integer id, Mono<UserDto> userDto) {
        return userService.updateUser(id, userDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
