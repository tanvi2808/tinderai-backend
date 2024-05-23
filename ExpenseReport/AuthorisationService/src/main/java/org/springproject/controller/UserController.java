package org.springproject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.constants.LoggingConstants;
import org.springproject.constants.RequestType;
import org.springproject.dto.user.ChangePasswordReqDto;
import org.springproject.dto.user.UserDetailsDto;
import org.springproject.dto.user.UserInfoDto;
import org.springproject.mapper.UserInfoMapper;
import org.springproject.service.model.UpdatePasswordReq;
import org.springproject.service.user.UserService;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable Long userId) {
        String methodName = "UserController:getUserInfo";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, userId);


        UserInfoDto userInfoDto = userService.getUserInfo(userId);

        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoDto);




    }

    @PutMapping("/resetPassword")
    public ResponseEntity<HttpStatus> updatePassword (@RequestBody ChangePasswordReqDto changePasswordReq){
        String methodName = "UserController:updatePassword";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, changePasswordReq.getEmail());
        UpdatePasswordReq updatePasswordReq = UserInfoMapper.INSTANCE.mapToUpdatePassword(changePasswordReq);
        userService.updatePassword(updatePasswordReq);
        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, changePasswordReq.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateUser")
    public ResponseEntity<UserInfoDto> updateUser(@PathVariable Long userId,
                                                  @RequestBody UserDetailsDto userDetailsDto){

        String methodName = "UserController:updateUser";
        log.info(LoggingConstants.START_INFO_CONSTANT, methodName, userId);
        var  userDetails = switch(userDetailsDto.getRequestType()) {
            case RequestType.UPDATE_EMAIL -> userService.updateEmail(userId, userDetailsDto.getEmail());
            case RequestType.UPDATE_NAME -> userService.updateName(userId, userDetailsDto.getUsername());
        };

        log.info(LoggingConstants.END_INFO_CONSTANT, methodName, userId);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(UserInfoMapper.INSTANCE.mapToUserInfo(userDetails));

    }

}