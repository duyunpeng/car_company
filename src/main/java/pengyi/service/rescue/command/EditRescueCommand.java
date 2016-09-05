package pengyi.service.rescue.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.RescueStatus;
import pengyi.model.user.driver.Driver;

/**
 * Created by YJH on 2016/3/9.
 */
public class EditRescueCommand {
    private String id;
    private Integer version;

    private RescueStatus rescueStatus;

    @NotEmpty(message = "{rescue.driver.NotEmpty.message}")
    private String driver;

    private String driverName;


    public RescueStatus getRescueStatus() {
        return rescueStatus;
    }

    public void setRescueStatus(RescueStatus rescueStatus) {
        this.rescueStatus = rescueStatus;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

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

}
