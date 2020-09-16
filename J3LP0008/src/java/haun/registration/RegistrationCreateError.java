/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.registration;

/**
 *
 * @author msi
 */
public class RegistrationCreateError {
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String passwordIncorrect;
    private String usernameNotExist;

    public RegistrationCreateError() {
    }

    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the passwordIncorrect
     */
    public String getPasswordIncorrect() {
        return passwordIncorrect;
    }

    /**
     * @param passwordIncorrect the passwordIncorrect to set
     */
    public void setPasswordIncorrect(String passwordIncorrect) {
        this.passwordIncorrect = passwordIncorrect;
    }

    /**
     * @return the usernameNotExist
     */
    public String getUsernameNotExist() {
        return usernameNotExist;
    }

    /**
     * @param usernameNotExist the usernameNotExist to set
     */
    public void setUsernameNotExist(String usernameNotExist) {
        this.usernameNotExist = usernameNotExist;
    }
    
    
}
