/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.auth.security.service;

import com.lotus.uccp.authentication.model.Users;

public interface UserService {
    void save(Users user);

    Users findByUsername(String username);
}
