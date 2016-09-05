package pengyi.service.order.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.OrderStatus;

/**
 * Created by YJH on 2016/3/18.
 */
public class OrderListCommand extends BasicPaginationCommand {

    private String company;             //公司id
    private OrderStatus orderStatus;    //订单状态
    private String orderNumber;          //订单号
    private String startCreateDate;     //开始下单时间
    private String endCreateDate;     //结束下单时间
    private DriverType driverType;
    private CarType carType;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStartCreateDate() {
        return startCreateDate;
    }

    public void setStartCreateDate(String startCreateDate) {
        this.startCreateDate = startCreateDate;
    }

    public String getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(String endCreateDate) {
        this.endCreateDate = endCreateDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
