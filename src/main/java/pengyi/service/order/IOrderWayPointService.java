package pengyi.service.order;

import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.order.OrderWayPoint;

import java.util.List;

/**
 * Created by pengyi on 2016/4/28.
 */
public interface IOrderWayPointService {

    List<OrderWayPoint> list(String orderId) throws ApiRemoteCallFailedException;

}
