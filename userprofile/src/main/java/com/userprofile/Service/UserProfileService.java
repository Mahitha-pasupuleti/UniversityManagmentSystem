package com.userprofile.Service;

import com.userprofile.Entity.UserProfile;

public interface UserProfileService {
    void createUserProfile(UserProfile userProfile);
    UserProfile getUserProfileByUsername(String username);
    boolean doesUserProfileExist(String username);
    void updateUserProfile(UserProfile userProfile);
}
