package pengyi.interfaces.area.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.interfaces.shared.web.JsonMessage;
import pengyi.model.area.Area;
import pengyi.service.area.IAreaService;
import pengyi.service.area.command.SearchAreaCommand;

import java.util.List;

/**
 * Created by YJH on 2016/3/17.
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAreaService areaService;

    @RequestMapping(value = "/search")
    @ResponseBody
    public JsonMessage searchArea(SearchAreaCommand command) {
        JsonMessage jsonMessage = new JsonMessage();
        try {
            List<Area> data = areaService.searchArea(command);
            jsonMessage.setMessage("操作成功");
            jsonMessage.setCode("0");
            jsonMessage.setData(data);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            jsonMessage.setCode("1");
            jsonMessage.setMessage(e.getResponse().getMessage());
        }
        return jsonMessage;
    }

}
