/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.auth.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptPassword {
	
	public static void main(String[] args) 
    {
		String hPassword = BCrypt.hashpw("Password1", BCrypt.gensalt());
		System.out.print(hPassword);
    }
}
