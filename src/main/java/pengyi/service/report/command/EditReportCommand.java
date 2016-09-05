package pengyi.service.report.command;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by liubowen on 2016/3/10.
 * 页面修改
 */
public class EditReportCommand {
    private String id;
    private Integer version;

    @NotEmpty(message = "{report.handleResult,NotEmpty,message}")
    private String handleResult;                   //处理结果

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
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
