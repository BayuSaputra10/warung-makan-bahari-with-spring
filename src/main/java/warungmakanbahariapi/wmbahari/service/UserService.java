package warungmakanbahariapi.wmbahari.service;

import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.request.UserRequest;

import warungmakanbahariapi.wmbahari.model.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest UserResponse);

    UserResponse deleteById(String id);

    UserResponse findById(String UserResponse);

    List<User> getAll();

    UserResponse update(UserRequest UserResponse);
    User loadByUSerId(String userId);
}
