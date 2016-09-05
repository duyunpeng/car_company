package pengyi.service.report;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.report.Report;
import pengyi.service.report.command.EditReportCommand;
import pengyi.service.report.command.ListReportCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by LvDi on 2016/3/24.
 */
@Service("reportService")
public class ReporService implements IReportService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public Report info(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.REPORT_SHOW_BY_REPORTID_URL, CoreStringUtils.assemblingParameters(command));
        Report report = apiAdminService.convertJsonTo(new TypeReference<Report>() {
        });
        return report;
    }

    @Override
    public ApiPagination<Report> list(ListReportCommand command) throws ApiRemoteCallFailedException {
        command.verifyPage();
        command.verifyPageSize(20);
        apiAdminService.urlConnection(ApiUrl.REPORT_LIST_URL,CoreStringUtils.assemblingParameters(command));
        return apiAdminService.convertJsonTo(new TypeReference<ApiPagination<Report>>() {
        });
    }

    @Override
    public void updateReport(ShareCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.REPORT_DEAL_REPORT_URL, CoreStringUtils.assemblingParameters(command
        ));

    }

    @Override
    public void finishReport(EditReportCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.REPORT_FINISH, CoreStringUtils.assemblingParameters(command
        ));
    }

}
