package com.userprofile.Mapper;

import com.userprofile.Entity.UserProfile;
import com.userprofile.Request.UserProfileRequest;
import com.userprofile.Response.AuthUserResponse;
import com.userprofile.Response.UserProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfile convertUserProfileRequestToUserProfile(UserProfileRequest userProfileRequest, AuthUserResponse authUserResponse) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername( authUserResponse.getUsername() );
        userProfile.setFirstName( userProfileRequest.getFirstName() );
        userProfile.setLastName( userProfileRequest.getLastName() );
        userProfile.setPhoneNumber( userProfileRequest.getPhoneNumber() );
        userProfile.setEmailId( authUserResponse.getEmail() );
        userProfile.setAddress( userProfileRequest.getAddress() );
        userProfile.setRole(authUserResponse.getRole());
        return userProfile;
    }

    public UserProfileResponse convertUserProfileToUserProfileResponse(UserProfile userProfile) {;
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setUsername(userProfile.getUsername());
        userProfileResponse.setFirstName(userProfile.getFirstName());
        userProfileResponse.setLastName(userProfile.getLastName());
        userProfileResponse.setEmailId(userProfile.getEmailId());
        userProfileResponse.setPhoneNumber(userProfile.getPhoneNumber());
        userProfileResponse.setAddress(userProfile.getAddress());
        userProfileResponse.setRole(userProfile.getRole());
        return userProfileResponse;
    }

}
