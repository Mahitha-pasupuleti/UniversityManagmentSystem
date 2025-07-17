package com.userprofile.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String emailId;
    private long phoneNumber;
    private String role;
    private String address;
}
