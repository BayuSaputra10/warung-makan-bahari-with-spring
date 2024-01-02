package warungmakanbahariapi.wmbahari.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String name;

    private String password;

}
