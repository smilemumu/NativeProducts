package com.shibro.nativeproducts.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhongcaishi
 * @date 2019/1/2
 */
@Component
public class MdcFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(MdcFilter.class);
    public static final String MDC_ID = "reqId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean mdcFlag = false;
        try {
            MDC.put(MDC_ID, UUID.randomUUID().toString());
            mdcFlag = true;
        } catch (Throwable e) {
            LOG.error("MDC put error", e);
        }
        try {
            String path = ((HttpServletRequest)request).getRequestURL().toString();
            LOG.info("request path:" + path);
            chain.doFilter(request, response);
        } finally {
            if (mdcFlag) {
                try {
                    MDC.remove(MDC_ID);
                } catch (Throwable e) {
                    LOG.error("MDC remove error", e);
                }
            }
        }
    }
}
