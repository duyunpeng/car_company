package pengyi.service.order;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.order.OrderWayPoint;

import java.util.List;

/**
 * Created by pengyi on 2016/4/28.
 */
@Service("orderWayPointService")
public class OrderWayPointService implements IOrderWayPointService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public List<OrderWayPoint> list(String orderId) throws ApiRemoteCallFailedException {

        apiAdminService.urlConnection(ApiUrl.ORDER_WAY_LIST_URL, "orderId=" + orderId);
        return apiAdminService.convertJsonTo(new TypeReference<List<OrderWayPoint>>() {
        });
    }
}
