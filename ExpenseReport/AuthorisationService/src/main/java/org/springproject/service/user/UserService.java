package org.springproject.service.user;

import org.springproject.dto.user.UserInfoDto;
import org.springproject.service.model.UpdatePasswordReq;
import org.springproject.service.model.UserInfo;

public interface UserService {

    UserInfoDto getUserInfo(Long id);

    public void updatePassword(UpdatePasswordReq updatePasswordReq);

    UserInfo updateName(Long userId, String username);

    UserInfo updateEmail(Long userId, String email);
}
