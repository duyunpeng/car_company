package pengyi.model.car;


import pengyi.core.type.CarType;
import pengyi.model.user.driver.Driver;

/**
 * Created by lvdi on 2016/3/22.
 */
public class Car {

    private String id;
    private Integer version;

    private String name;                //车辆名称
    private String carNumber;           //车牌号
    private Driver driver;              //司机
    private CarType carType;

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getId() { return id; }

    public Integer getVersion() { return version; }

    public void setId(String id) { this.id = id; }

    public void setVersion(Integer version) { this.version = version; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
