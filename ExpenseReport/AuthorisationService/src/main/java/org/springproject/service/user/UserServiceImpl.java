package org.springproject.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springproject.constants.ErrorCodeConstants;
import org.springproject.constants.LoggingConstants;
import org.springproject.data.model.AppUser;
import org.springproject.data.repo.AppUserRepository;
import org.springproject.dto.user.UserInfoDto;
import org.springproject.exception.PasswordDoesNotMatchException;
import org.springproject.exception.UserNotFoundException;
import org.springproject.mapper.UserInfoMapper;
import org.springproject.service.model.UpdatePasswordReq;
import org.springproject.service.model.UserInfo;

import java.time.Instant;
import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto getUserInfo(Long id) {
        String methodName = "UserServiceImpl:getUserInfo";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, id);

        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> {
                            log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName, id + " does not exist");
                            return new UserNotFoundException(
                                    ErrorCodeConstants.USER_NOT_FOUND.getErrorCode(),
                                    ErrorCodeConstants.USER_NOT_FOUND.getMessage());
                        }
                );

        UserInfo userInfo = UserInfoMapper.INSTANCE.maptoUserInfo(appUser);


        return UserInfoMapper.INSTANCE.mapToUserInfo(userInfo);
    }

    public void updatePassword(UpdatePasswordReq updatePasswordReq) {
        String methodName = "UserServiceImpl:getUserInfo";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, updatePasswordReq);
        // get the user with email

        AppUser appUser = appUserRepository.findByEmail(updatePasswordReq.getEmail())
                .orElseThrow(() -> {
                    log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName,
                            updatePasswordReq.getEmail() + " does not exist");
                    return new UserNotFoundException(
                            ErrorCodeConstants.USER_NOT_FOUND.getErrorCode(),
                            ErrorCodeConstants.USER_NOT_FOUND.getMessage());
                });

        // match the old passwd, if no, throw exception , else update
        if(!passwordEncoder.matches(updatePasswordReq.getOldPwd(), appUser.getPassword())){
            log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName,
                    updatePasswordReq.getEmail() + "  old password does not match");
            throw new PasswordDoesNotMatchException(
                    ErrorCodeConstants.PASSWORD_DOES_NOT_MATCH.getErrorCode(),
                    ErrorCodeConstants.PASSWORD_DOES_NOT_MATCH.getMessage());

        };


              appUser.setPassword(passwordEncoder.encode(updatePasswordReq.getNewPwd()));
              appUser.setModifiedAt(Instant.now());

        appUserRepository.save(appUser);

        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, "Password updated successfull!!");
    }

    @Override
    public UserInfo updateName(Long userId, String username) {
        String methodName = "UserServiceImpl:updateName";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, username);
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName,
                            userId  + " does not exist");
                    return new UserNotFoundException(
                            ErrorCodeConstants.USER_NOT_FOUND.getErrorCode(),
                            ErrorCodeConstants.USER_NOT_FOUND.getMessage());
                });

        appUser.setUsername(username);
        appUser.setModifiedAt(Instant.now());

        AppUser updatedUser = appUserRepository.save(appUser);

        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, userId);
        return UserInfoMapper.INSTANCE.maptoUserInfo(updatedUser);

    }

    @Override
    public UserInfo updateEmail(Long userId, String email) {
        String methodName = "UserServiceImpl:updateEmail";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, userId);

        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error(LoggingConstants.ERROR_INFO_CONSTANT, methodName,
                            userId  + " does not exist");
                    return new UserNotFoundException(
                            ErrorCodeConstants.USER_NOT_FOUND.getErrorCode(),
                            ErrorCodeConstants.USER_NOT_FOUND.getMessage());
                });

        appUser.setEmail(email);
        appUser.setModifiedAt(Instant.now());
        AppUser updatedUser = appUserRepository.save(appUser);
        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, userId);
        return UserInfoMapper.INSTANCE.maptoUserInfo(updatedUser);
    }
}

