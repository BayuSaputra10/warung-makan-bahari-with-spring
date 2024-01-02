package warungmakanbahariapi.wmbahari.service;

import warungmakanbahariapi.wmbahari.entity.ERole;
import warungmakanbahariapi.wmbahari.entity.UserRole;

public interface RoleService {

    UserRole getOrSave(ERole eRole);
}
