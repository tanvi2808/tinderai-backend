package org.springproject.service.user;

import org.springproject.dto.user.UserInfoDto;
import org.springproject.service.model.UpdatePasswordReq;

public interface UserService {

    UserInfoDto getUserInfo(Long id);

    public void updatePassword(UpdatePasswordReq updatePasswordReq);
}
