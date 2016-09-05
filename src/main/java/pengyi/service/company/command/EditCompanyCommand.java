package pengyi.service.company.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/18.
 */
public class EditCompanyCommand {

    private String id;

    private Integer version;

    @NotEmpty(message = "{company.email.NotEmpty.message}")
    private String email;
    @NotEmpty(message = "{company.name.NotEmpty.message}")
    private String name;

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
}
