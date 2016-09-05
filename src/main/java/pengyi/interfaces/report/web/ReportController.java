package pengyi.interfaces.report.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.BaseResponse;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.model.report.Report;
import pengyi.model.user.BaseUser;
import pengyi.service.report.IReportService;
import pengyi.service.report.command.EditReportCommand;
import pengyi.service.report.command.ListReportCommand;
import pengyi.service.share.command.ShareCommand;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by LvDi on 2016/3/24.
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IReportService reportService;

    @RequestMapping(value = "/show/{id}")
    public ModelAndView info(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
        Report report = null;
        AlertMessage alertMessage;
        try {
            report = reportService.info(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/report/show", "report", report);

    }

    @RequestMapping(value = "/list")
    public ModelAndView list(ListReportCommand command, HttpSession session,
                             RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        ApiPagination<Report> pagination = null;
        AlertMessage alertMessage;
        try {
            pagination = reportService.list(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/report/list", "pagination", pagination)
                .addObject("command", command);
    }

//    @RequestMapping(value = "/deal/{id}", method = RequestMethod.GET)
//    public ModelAndView deal(@PathVariable String id, EditReportCommand command,
//                             HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
//        BaseResponse user = (BaseResponse) session.getAttribute(Constants.SESSION_USER);
//        if (null == user) {
//            return new ModelAndView("redirect:logout");
//        }
//        Report report = null;
//        AlertMessage alertMessage;
//        try {
//            report = reportService.info(id);
//        } catch (ApiRemoteCallFailedException e) {
//            logger.warn(e.getMessage());
//        }
//        return new ModelAndView("/report/deal", "report", report).addObject("command", command);
//
//    }


    @RequestMapping(value = "/deal")
    public ModelAndView deal(@Valid @ModelAttribute("command")ShareCommand command,
                             HttpSession session,RedirectAttributes redirectAttributes,Locale locale){
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }

        AlertMessage alertMessage;
        try {
            reportService.updateReport(command);

        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("redirect:/report/list", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        alertMessage = new AlertMessage(this.getMessage("default.deal.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/report/list");
    }

    @RequestMapping(value = "/finish",method = RequestMethod.POST)
    public ModelAndView finish(@Valid @ModelAttribute("command")EditReportCommand command,
                             BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes,Locale locale){

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/report/list", "command", command);
        }
        AlertMessage alertMessage;
        try {
            reportService.finishReport(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("redirect:/report/list", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        alertMessage = new AlertMessage(this.getMessage("default.deal.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/report/list");
    }

 }