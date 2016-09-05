package pengyi.model.report;

import pengyi.core.type.ReportStatus;
import pengyi.model.order.Order;
import pengyi.model.user.user.User;

import java.util.Date;


/**
 * Created by liubowen on 2016/3/10.
 */
public class Report {
    private Integer version;
    private String id;
    private User reportUser;      //举报人
    private Order order;          //举报订单
    private String reportTime;                  //举报时间
    private String startDealTime;               //开始处理时间
    private String endDealTime;                 //处理完成时间
    private String description;                 //说明
    private String handleResult;                   //处理结果
    private ReportStatus status;                //状态（待处理、处理中、处理完成）

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getReportUser() {
        return reportUser;
    }

    public void setReportUser(User reportUser) {
        this.reportUser = reportUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(String startDealTime) {
        this.startDealTime = startDealTime;
    }

    public String getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(String endDealTime) {
        this.endDealTime = endDealTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
