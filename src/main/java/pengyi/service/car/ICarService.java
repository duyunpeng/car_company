package pengyi.service.car;

import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.car.Car;
import pengyi.service.car.command.CreateCarCommand;
import pengyi.service.car.command.EditCarCommand;

/**
 * Created by LvDi on 2016/3/22.
 */
public interface ICarService {
    Car search(String driverId) throws ApiRemoteCallFailedException;

    Car create(CreateCarCommand command) throws ApiRemoteCallFailedException;

    Car edit(EditCarCommand command)throws ApiRemoteCallFailedException;

    Car show(String id) throws ApiRemoteCallFailedException;

    Car showID(String id) throws ApiRemoteCallFailedException;
}

