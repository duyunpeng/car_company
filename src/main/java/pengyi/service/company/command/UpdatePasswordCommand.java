package pengyi.service.company.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/18.
 */
public class UpdatePasswordCommand {

    private String id;
    private Integer version;

    @NotEmpty(message = "{company.oldPassword.NotEmpty.message}")
    private String oldPassword;
    @NotEmpty(message = "{company.newPassword.NotEmpty.message}")
    private String newPassword;
    @NotEmpty(message = "{company.confirmNewPassword.NotEmpty.message}")
    private String confirmNewPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
