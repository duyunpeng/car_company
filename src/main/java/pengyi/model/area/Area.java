package pengyi.model.area;

/**
 * Created by YJH on 2016/3/8.
 */
public class Area {

    private String id;
    private Integer version;

    private String name;                    //地区名
    private String priority;                //区域优先级
    private Area parent;                    //父级地区


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }
}
