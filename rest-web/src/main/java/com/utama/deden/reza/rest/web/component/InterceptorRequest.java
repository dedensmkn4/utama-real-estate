package com.utama.deden.reza.rest.web.component;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class InterceptorRequest extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        MandatoryRequest mandatoryRequest = MandatoryRequest.builder()
                .requestId(request.getHeader("requestId"))
                .channelId(request.getHeader("channelId"))
                .username(request.getHeader("username"))
                .build();

        MDC.put("requestId", request.getHeader("requestId"));

        request.setAttribute("mandatory", mandatoryRequest);

        return true;
    }
}
