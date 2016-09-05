package pengyi.service.car.command;

import pengyi.core.type.CarType;

/**
 * Created by lvdi on 2016/3/7.
 */
public class InfoCarCommand {

    private String id;

    private String driver;              //司机

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
