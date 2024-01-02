package warungmakanbahariapi.wmbahari.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import warungmakanbahariapi.wmbahari.entity.ERole;
import warungmakanbahariapi.wmbahari.entity.UserRole;
import warungmakanbahariapi.wmbahari.repository.UserRoleRepository;
import warungmakanbahariapi.wmbahari.service.RoleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRoleRepository userRoleRepository;
    @Override
    public UserRole getOrSave(ERole eRole) {
        Optional<UserRole> optionalRole = userRoleRepository.findByRole(eRole);
        if (optionalRole.isPresent()) return optionalRole.get();

        UserRole role = UserRole.builder()
                .role(eRole)
                .build();
        return userRoleRepository.save(role);
    }
}
