package com.thpt.user.request.copy;

import static com.thpt.common.constant.KeyFields.UserKeyFields.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    
    @JsonProperty(USERNAME)
    private String username;
    @JsonProperty(PASSWORD)
    private String password;
    @JsonProperty(GENDER)
    private Integer gender;
    @JsonProperty(FULLNAME)
    private String fullName;
    @JsonProperty(PHONE)
    private String phone;
    @JsonProperty(EMAIL)
    private String email;
    
}
