package pengyi.interfaces.car.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.model.car.Car;
import pengyi.model.user.BaseUser;
import pengyi.service.car.ICarService;
import pengyi.service.car.command.CreateCarCommand;
import pengyi.service.car.command.EditCarCommand;
import pengyi.service.car.command.InfoCarCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;


/**
 * Created by LvDi on 2016/3/23.
 */
@Controller
@RequestMapping("/car")
public class CarController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ICarService carService;

    @RequestMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        Car car = null;
        AlertMessage alertMessage;
        try {
            car = carService.show(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        return new ModelAndView("car/show", "car", car);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(CreateCarCommand command, HttpSession session, HttpServletRequest request,
                               RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        String url = request.getHeader("Referer");
        AlertMessage alertMessage;
        try {
            String id = url.substring(url.lastIndexOf("/") + 1);
            command.setDriver(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.apiRequest.failure.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/driver/list");
        }
        return new ModelAndView("/car/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateCarCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/car/create", "command", command);
        }
        AlertMessage alertMessage;
        Car car;
        try {
            car = carService.create(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/car/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", car.getDriver().getId());
        return new ModelAndView("redirect:/car/show/{id}");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id, EditCarCommand command, HttpSession session,
                             RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        Car car;
        try {
            car = carService.showID(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.apiRequest.failure.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/driver/list");
        }
        return new ModelAndView("car/edit", "command", command).addObject("car", car);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditCarCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        if (bindingResult.hasErrors()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.apiRequest.failure.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/car/show/{id}");
        }
        Car car;
        try {
            car = carService.edit(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.apiRequest.failure.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/car/show/{id}");

        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", car.getDriver().getId());
        return new ModelAndView("redirect:/car/show/{id}");
    }
}
