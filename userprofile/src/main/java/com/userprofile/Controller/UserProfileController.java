package com.userprofile.Controller;

import com.userprofile.Entity.UserProfile;
import com.userprofile.External.AuthServiceClient;
import com.userprofile.Mapper.UserProfileMapper;
import com.userprofile.Request.UpdateUserProfileRequest;
import com.userprofile.Request.UserProfileRequest;
import com.userprofile.Response.AuthUserResponse;
import com.userprofile.Response.UserProfileResponse;
import com.userprofile.Service.JwtService;
import com.userprofile.Service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/university/userProfile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest, HttpServletRequest request) {
        AuthUserResponse authUserResponse = authServiceClient.getUserByUsername( request.getHeader("Authorization") );
        UserProfile userProfile = userProfileMapper.convertUserProfileRequestToUserProfile(userProfileRequest, authUserResponse);
        userProfileService.createUserProfile( userProfile );
        UserProfileResponse userProfileResponse = userProfileMapper.convertUserProfileToUserProfileResponse(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest updateUserProfileRequest, HttpServletRequest request) {
        String username = jwtService.extractUsername( request.getHeader("Authorization").substring(7) );
       UserProfile userProfile = userProfileService.getUserProfileByUsername( username );

        if ( updateUserProfileRequest.getUsername() != null ) {
            userProfile.setUsername( updateUserProfileRequest.getUsername() );
        }
        if ( updateUserProfileRequest.getFirstName() != null ) {
            userProfile.setFirstName( updateUserProfileRequest.getFirstName() );
        }
        if ( updateUserProfileRequest.getLastName() != null ) {
            userProfile.setLastName( updateUserProfileRequest.getLastName() );
        }
        if ( updateUserProfileRequest.getPhoneNumber() != 0 ) {
            userProfile.setPhoneNumber(updateUserProfileRequest.getPhoneNumber());
        }
        if ( updateUserProfileRequest.getAddress() != null ) {
            userProfile.setEmailId( updateUserProfileRequest.getAddress() );
        }

        userProfileService.updateUserProfile(userProfile);

        UserProfileResponse userProfileResponse = userProfileMapper.convertUserProfileToUserProfileResponse(userProfile);
        return ResponseEntity.status(HttpStatus.OK).body(userProfileResponse);
    }
}
