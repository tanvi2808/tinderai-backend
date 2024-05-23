package org.springproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springproject.data.model.AppUser;
import org.springproject.dto.user.ChangePasswordReqDto;
import org.springproject.dto.user.UserInfoDto;
import org.springproject.service.model.UpdatePasswordReq;
import org.springproject.service.model.UserInfo;

@Mapper
public interface UserInfoMapper {

    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfoDto mapToUserInfo(UserInfo userinfo);
    UserInfo maptoUserInfo(AppUser appUser);

    UpdatePasswordReq mapToUpdatePassword(ChangePasswordReqDto changePasswordReqDto);


}
