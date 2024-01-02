package warungmakanbahariapi.wmbahari.service;

import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.request.AuthRequest;
import warungmakanbahariapi.wmbahari.model.request.UserRequest;
import warungmakanbahariapi.wmbahari.model.response.UserResponse;

public interface AuthService {
    User register(AuthRequest authRequest);
    User registerAdmin(AuthRequest authRequest);

    String login(AuthRequest request);
}
