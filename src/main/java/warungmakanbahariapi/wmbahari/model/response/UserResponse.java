package warungmakanbahariapi.wmbahari.model.response;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String name;

    private String password;
}
