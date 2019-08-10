
package com.oyedost.contactapp.commands;

/**
 * The number of variables or fields equal to the number of fields available in the 
 * corresponding forms --> or form input
 * @author janeman
 */
public class LoginCommand {
    private String loginName ;
    private String loginPassword;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    
}
