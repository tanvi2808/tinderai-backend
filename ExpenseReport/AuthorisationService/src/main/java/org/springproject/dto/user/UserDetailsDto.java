package org.springproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springproject.constants.RequestType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    String username;
    String email;
    RequestType requestType;
}
