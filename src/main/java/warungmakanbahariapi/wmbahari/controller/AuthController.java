package warungmakanbahariapi.wmbahari.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.WebResponse;
import warungmakanbahariapi.wmbahari.model.request.AuthRequest;
import warungmakanbahariapi.wmbahari.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        User userCredential = authService.register(request);
        WebResponse<User> response = WebResponse.<User>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Create new User")
                .data(userCredential)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request){
        User userCredential = authService.registerAdmin(request);
        WebResponse<User> response = WebResponse.<User>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Create new User")
                .data(userCredential)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        String token = authService.login(request);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Create new User")
                .data(token)
                .build();

        return ResponseEntity.ok(response);
    }
}
