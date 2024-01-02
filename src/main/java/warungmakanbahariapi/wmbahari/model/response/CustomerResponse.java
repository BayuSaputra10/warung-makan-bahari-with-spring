package warungmakanbahariapi.wmbahari.model.response;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private String name;

    private String phoneNumber;

    private String userId;
}
