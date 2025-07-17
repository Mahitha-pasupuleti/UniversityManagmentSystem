package com.userprofile.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {
    private String username;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String address;
}
