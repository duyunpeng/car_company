package pengyi.service.company.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/23.
 */
public class ResetPasswordCommand {

    @NotEmpty(message = "{company.userName.NotEmpty.message}")
    private String userName;
    @NotEmpty(message = "{company.password.NotEmpty.message}")
    private String password;
    @NotEmpty(message = "{driver.confirmPassword.NotEmpty.message}")
    private String confirmPassword;
    private String verificationCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
