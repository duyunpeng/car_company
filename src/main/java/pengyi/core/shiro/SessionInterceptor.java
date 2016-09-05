package pengyi.core.shiro;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pengyi.core.commons.Constants;
import pengyi.model.user.BaseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YJH on 2016/3/7.
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static final String[] IGNORE_URI = {"/", "/login", "resources/"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            BaseUser user = (BaseUser) request.getSession().getAttribute(Constants.SESSION_USER);
            if (user != null) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
