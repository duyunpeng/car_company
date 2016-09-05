package pengyi.model.user.company;

import pengyi.model.area.Area;
import pengyi.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
public class Company extends BaseUser {

    private String name;            //公司名
    private String folder;          //公司资质
    private String registerDate;    //注册时间
    private Area registerAddress;   //注册地点
    private Area operateAddress;    //运营地点
    private BigDecimal money;       //余额
    private Double level;           //等级

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

    public Area getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(Area registerAddress) {
        this.registerAddress = registerAddress;
    }

    public Area getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(Area operateAddress) {
        this.operateAddress = operateAddress;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }
}
