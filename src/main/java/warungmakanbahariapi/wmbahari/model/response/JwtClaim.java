package warungmakanbahariapi.wmbahari.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtClaim {
    private String userId;

    private List<String> roles;
}