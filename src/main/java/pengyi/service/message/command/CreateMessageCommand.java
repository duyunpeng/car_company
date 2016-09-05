package pengyi.service.message.command;

import java.util.List;

/**
 * Created by liubowen on 2016/3/24.
 */
public class CreateMessageCommand {
    private String company;                  //公司
    private List<String> receiveBaseUser;            //接收人
    private String content;                 //内容

    public List<String> getReceiveBaseUser() {
        return receiveBaseUser;
    }

    public void setReceiveBaseUser(List<String> receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

