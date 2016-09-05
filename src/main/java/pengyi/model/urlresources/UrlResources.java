package pengyi.model.urlresources;

import pengyi.core.type.EnableStatus;
import pengyi.model.permission.Permission;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public class UrlResources {

    private String id;
    private Integer version;

    private String urlName;                     //路径名
    private String description;                 //描述
    private List<Permission> urlPermission;     //路径权限列表
    private EnableStatus status;                     //是否启用

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

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getUrlPermission() {
        return urlPermission;
    }

    public void setUrlPermission(List<Permission> urlPermission) {
        this.urlPermission = urlPermission;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
