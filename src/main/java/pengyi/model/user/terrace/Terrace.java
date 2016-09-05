package pengyi.model.user.terrace;

import pengyi.model.user.BaseUser;

/**
 * Created by YJH on 2016/3/7.
 */
public class Terrace extends BaseUser {

    private String name;            //用户名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
