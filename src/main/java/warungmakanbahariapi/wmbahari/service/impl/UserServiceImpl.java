package warungmakanbahariapi.wmbahari.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.request.UserRequest;
import warungmakanbahariapi.wmbahari.model.response.CustomerResponse;
import warungmakanbahariapi.wmbahari.model.response.UserResponse;
import warungmakanbahariapi.wmbahari.repository.UserRepository;
import warungmakanbahariapi.wmbahari.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {

        User user = User.builder()
                .username(request.getName())
                .password(request.getPassword())
                .build();

        User saveUser = userRepository.save(user);

        return UserResponse.builder()
                .name(saveUser.getUsername())
                .password(saveUser.getPassword())
                .build();
    }

    @Override
    public UserResponse deleteById(String id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");

        userRepository.deleteById(byId.get().getUsername());
        return UserResponse.builder()
                .name("Succes Delete User with name :" + byId.get().getUsername())
                .build();
    }

    @Override
    public UserResponse findById(String UserResponse) {
        Optional<User> byId = userRepository.findById(UserResponse);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");

        return warungmakanbahariapi.wmbahari.model.response.UserResponse.builder()
                .name(byId.get().getUsername())
                .password(byId.get().getPassword())
                .build();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse update(UserRequest request) {
        Optional<User> byId = userRepository.findById(request.getName());
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");

        User saveCustomerEntity = User.builder()
                .username(request.getName())
                .password(request.getPassword())
                .build();

        User saveAndFlush = userRepository.saveAndFlush(saveCustomerEntity);
        return UserResponse.builder()
                .name(saveAndFlush.getUsername())
                .password(saveCustomerEntity.getPassword())
                .build();
    }

    @Override
    public User loadByUSerId(String userId) {
            return userRepository.findById(userId).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.UNAUTHORIZED,"unauthorized"));

        }
    }

