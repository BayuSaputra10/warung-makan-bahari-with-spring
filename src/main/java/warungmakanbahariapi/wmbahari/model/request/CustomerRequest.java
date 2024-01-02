package warungmakanbahariapi.wmbahari.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    private String name;

    private String phoneNumber;

}
