package pengyi.interfaces.company.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.model.user.BaseUser;
import pengyi.model.user.company.Company;
import pengyi.service.company.ICompanyService;
import pengyi.service.company.command.EditCompanyCommand;
import pengyi.service.company.command.UpdatePasswordCommand;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/18.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICompanyService companyService;

    @RequestMapping(value = "/info")
    public ModelAndView info(HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        Company company = null;
        AlertMessage alertMessage;
        try {
            company = companyService.info(user.getId());
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/company/info", "company", company);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(EditCompanyCommand command, HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        Company company = null;
        AlertMessage alertMessage;
        try {
            company = companyService.info(user.getId());
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
        }

        return new ModelAndView("/company/edit", "company", company).addObject("command", command);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditCompanyCommand command,
                             HttpSession session, RedirectAttributes redirectAttributes, BindingResult bindingResult,
                             Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/company/edit", "command", command);
        }
        AlertMessage alertMessage;
        try {
            companyService.edit(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/company/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/company/info");
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.GET)
    public ModelAndView updatePassword(@ModelAttribute("command") UpdatePasswordCommand command,
                                       HttpSession session, RedirectAttributes redirectAttributes,
                                       Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        Company company = null;
        try {
            company = companyService.info(user.getId());
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        return new ModelAndView("/company/updatepassword", "company", company);
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public ModelAndView updatePassword(@Valid @ModelAttribute("command") UpdatePasswordCommand command,
                                       RedirectAttributes redirectAttributes, BindingResult bindingResult,
                                       Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/company/updatepassword");
        }

        AlertMessage alertMessage;
        try {
            companyService.updatePassword(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/company/updatepassword", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        return new ModelAndView("/company/updatepasswordsuccess");
    }
}
