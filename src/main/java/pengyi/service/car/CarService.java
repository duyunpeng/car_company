package pengyi.service.car;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.api.BaseResponse;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.car.Car;
import pengyi.service.car.command.InfoCarCommand;
import pengyi.service.car.command.CreateCarCommand;
import pengyi.service.car.command.EditCarCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by LvDi on 2016/3/22.
 */
@Service("carService")
public class CarService implements ICarService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public Car search(String driverId) throws ApiRemoteCallFailedException {

        InfoCarCommand command = new InfoCarCommand();
        command.setDriver(driverId);
        apiAdminService.urlConnection(ApiUrl.CAR_INFO_URL, CoreStringUtils.assemblingParameters(command));
        Car data = apiAdminService.convertJsonTo(new TypeReference<Car>() {
        });
        return data;
    }

    @Override
    public Car create(CreateCarCommand command) throws ApiRemoteCallFailedException {

        BaseResponse response = apiAdminService.urlConnection(ApiUrl.CAR_CREATE_URL, CoreStringUtils.assemblingParameters(command));
        return apiAdminService.convertJsonTo(new TypeReference<Car>() {
        });
    }

    @Override
    public Car edit(EditCarCommand command) throws ApiRemoteCallFailedException {

        apiAdminService.urlConnection(ApiUrl.CAR_EDIT_URL, CoreStringUtils.assemblingParameters(command));
        return apiAdminService.convertJsonTo(new TypeReference<Car>() {
        });
    }

    @Override
    public Car show(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.CAR_SHOW_URL, CoreStringUtils.assemblingParameters(command));
        Car car = apiAdminService.convertJsonTo(new TypeReference<Car>() {
        });
        return car;
    }

    @Override
    public Car showID(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.CAR_SHOW_ID_URL, CoreStringUtils.assemblingParameters(command));
        Car car = apiAdminService.convertJsonTo(new TypeReference<Car>() {
        });
        return car;
    }
}