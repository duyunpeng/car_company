package pengyi.service.driver.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.DriverType;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by YJH on 2016/3/24.
 */
public class CreateDriverCommand {

    private String company;
    @NotEmpty(message = "{driver.userName.NotEmpty.message}")
    private String userName;
    @NotEmpty(message = "{driver.password.NotEmpty.message}")
    private String password;
    @NotEmpty(message = "{driver.confirmPassword.NotEmpty.message}")
    private String confirmPassword;
    @NotNull(message = "{driver.driverType.NotNull.message}")
    private DriverType driverType;
    @NotEmpty(message = "{driver.startDriveDate.NotEmpty.message}")
    private String startDriveDate;

    private String name;                    //司机名字

    private String identityCardPic;         //身份证照片
    private String drivingLicencePic;       //驾驶证照片
    private String travelPic;               //行驶证
    private String drivingLicenceType;      //驾驶证类型（C1,C2,B1,B2,A1,A2）
    private String phone;               //电话
    private String businessPic;         //营业资格证
    private String workPic;             //从业资格证

    private String bankCardNo;              //银行卡号
    private String bankName;            //银行名称


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

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

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getStartDriveDate() {
        return startDriveDate;
    }

    public void setStartDriveDate(String startDriveDate) {
        this.startDriveDate = startDriveDate;
    }

    public String getIdentityCardPic() {
        return identityCardPic;
    }

    public void setIdentityCardPic(String identityCardPic) {
        this.identityCardPic = identityCardPic;
    }

    public String getDrivingLicencePic() {
        return drivingLicencePic;
    }

    public void setDrivingLicencePic(String drivingLicencePic) {
        this.drivingLicencePic = drivingLicencePic;
    }

    public String getTravelPic() {
        return travelPic;
    }

    public void setTravelPic(String travelPic) {
        this.travelPic = travelPic;
    }

    public String getDrivingLicenceType() {
        return drivingLicenceType;
    }

    public void setDrivingLicenceType(String drivingLicenceType) {
        this.drivingLicenceType = drivingLicenceType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(String businessPic) {
        this.businessPic = businessPic;
    }

    public String getWorkPic() {
        return workPic;
    }

    public void setWorkPic(String workPic) {
        this.workPic = workPic;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
