package pengyi.model.evaluate;


import pengyi.model.order.Order;
import pengyi.model.user.BaseUser;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class Evaluate {

    private String id;
    private Integer version;

    private BaseUser evaluateUser;               //评价人
    private Order order;                            //订单
    private String content;                                    //评价内容
    private int level;                                         //评级
    private String createDate;                                 //评价时间

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BaseUser getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(BaseUser evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
