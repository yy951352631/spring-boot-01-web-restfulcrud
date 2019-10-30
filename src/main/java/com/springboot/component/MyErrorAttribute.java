package com.springboot.component;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Wtq
 * @date 2019/8/13 - 17:01
 */
//给容器中加入我们自己定义的ErrorAttributes
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        errorAttributes.put("company", "primeton");
        //我们的异常处理器携带的数据   0代表从请求域中获取
        Map<String, Object> ext = (Map<String, Object>) request.getAttribute("ext", 0);
        errorAttributes.put("ext", ext);
        return errorAttributes;
    }
}
