package com.vti.rk25finalexam.utils;

import com.vti.rk25finalexam.exception.ICommonException;
import com.vti.rk25finalexam.exception.RK25Exception;
import com.vti.rk25finalexam.exception.Rk25Error;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;
import java.util.Map;

@Component
public class HttpUtils {

    private final MessageSource messageSource;

    public HttpUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Rk25Error populateMessage(ICommonException ex, Locale locale) {
        if (locale == null) {
            locale = new Locale("vi", "VI");
        }
        if (ex != null) {
            Rk25Error rk25Error = ex.getRk25Error();

            String message = rk25Error.getMessage();
            String code = rk25Error.getCode();
            Object params = rk25Error.getParam();

            if (message == null || message.isEmpty()) {
                String defaultMessage = messageSource
                        .getMessage("defaultMessage", new Object[]{params},
                                "", locale);
                rk25Error.message(
                    messageSource
                        .getMessage(code, new Object[]{params},
                                defaultMessage, locale));
            }
            return rk25Error;
        } else {
            try {
                throw new Exception();
            } catch (Exception exception) {
                System.out.println("error!!!");
            }
        }
        return new Rk25Error();
    }

    public String getLanguage(WebRequest webRequest) {
        return  webRequest.getHeader("lang") != null ? webRequest.getHeader("lang") : "vi";
    };

    public String getLanguage(Map<String, String> headers) {
        return  headers.get("lang") != null ? headers.get("lang") : "vi";
    };

}
