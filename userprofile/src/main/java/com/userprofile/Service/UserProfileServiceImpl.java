package com.userprofile.Service;

import com.userprofile.CustomExceptions.ProfileAlreadyCreatedException;
import com.userprofile.CustomExceptions.UserNotFoundException;
import com.userprofile.Entity.UserProfile;
import com.userprofile.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if ( !doesUserProfileExist( userProfile.getUsername() ) ) {
            userProfileRepository.save(userProfile);
        } else {
            throw new ProfileAlreadyCreatedException("User Profile already created!");
        }
    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUserProfileByUsername(String username) {
        return userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    @Override
    public boolean doesUserProfileExist(String username) {
        return userProfileRepository.findByUsername(username).isPresent();
    }

}
