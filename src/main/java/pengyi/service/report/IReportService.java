package pengyi.service.report;

import pengyi.core.api.ApiPagination;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.report.Report;
import pengyi.service.report.command.EditReportCommand;
import pengyi.service.report.command.ListReportCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by LvDi on 2016/3/24.
 */

public interface IReportService {
    Report info(String id) throws ApiRemoteCallFailedException;

    ApiPagination<Report> list(ListReportCommand command)throws ApiRemoteCallFailedException;

    void updateReport(ShareCommand command) throws ApiRemoteCallFailedException;

    void finishReport(EditReportCommand command) throws ApiRemoteCallFailedException;
}
