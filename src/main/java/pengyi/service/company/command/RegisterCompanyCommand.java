package pengyi.service.company.command;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/16.
 */
public class RegisterCompanyCommand {

    @NotEmpty(message = "{register.userName.NotEmpty.message}")
    private String userName;                         //用户名
    @NotEmpty(message = "{register.password.NotEmpty.message}")
    private String password;                        //密码
    @NotEmpty(message = "{register.confirmPassword.NotEmpty.message}")
    private String confirmPassword;                 //确认密码
    @Email(message = "{register.email.NotEmail.message}")
    private String email;                           //邮箱
    @NotEmpty(message = "{register.name.NotEmpty.message}")
    private String name;            //公司名
    @NotEmpty(message = "{register.folder.NotEmpty.message}")
    private String folder;          //公司资质
    @NotEmpty(message = "{register.registerDate.NotEmpty.message}")
    private String registerDate;    //公司注册时间
    @NotEmpty(message = "{register.registerAddress.NotEmpty.message}")
    private String registerAddress;   //注册地点
    @NotEmpty(message = "{register.operateAddress.NotEmpty.message}")
    private String operateAddress;    //运营地点
    @NotEmpty(message = "{register.verificationCode.NotEmpty.message}")
    private String verificationCode;    //验证码

    private String registerAddressName;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress;
    }

    public String getRegisterAddressName() {
        return registerAddressName;
    }

    public void setRegisterAddressName(String registerAddressName) {
        this.registerAddressName = registerAddressName;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
