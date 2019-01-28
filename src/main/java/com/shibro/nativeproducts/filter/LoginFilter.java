package com.shibro.nativeproducts.filter;

import com.alibaba.fastjson.JSON;
import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LoginFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);

    private PathMatcher pathMatcher = new AntPathMatcher();


    private List<String> specialNotNeedLoginPattern = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String staticUlr = "**/static/**";
        String loginUrl = "**/login/";
        String logoutUrl = "**/logout/";
        String registerUrl = "**/register/";
        LOG.info("初始化用户登录过滤器");
        specialNotNeedLoginPattern.add(staticUlr);
        specialNotNeedLoginPattern.add(loginUrl);
        specialNotNeedLoginPattern.add(logoutUrl);
        specialNotNeedLoginPattern.add(registerUrl);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
        HttpSession session = req.getSession();
		String path = req.getRequestURL().toString();
        LOG.info("request path:"+path);
        Boolean pass = specialNotNeedLoginPattern.stream().map(pattern->pathMatcher.match(pattern,path)).anyMatch(item->item.equals(true));
        if(pass){
            chain.doFilter(request, response);
        }else{
            String tokenValue = (String) session.getAttribute("token");
            String verifyToken = TokenUtil.generateToken((String) session.getAttribute("userName"));
            if(Objects.isNull(tokenValue)||!verifyToken.equals(verifyToken)){
                returnLoginPage(req,rep, ErrorCodeEnum.NEED_LOGIN);
            }else{
                chain.doFilter(request, response);
            }
        }
    }


    private void returnLoginPage(HttpServletRequest req, HttpServletResponse rep, ErrorCodeEnum needLogin) {
        rep.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        rep.setContentType("application/json;charset=utf-8");
        rep.setHeader("Access-Control-Allow-Origin","*");
        PrintWriter out = null;
        try{
            BaseResponseVo responseVo = BaseResponseVo.failResponseVo(ErrorCodeEnum.NEED_LOGIN);
            out= rep.getWriter();
            out.print(JSON.toJSONString(responseVo));
            out.flush();
        }catch (IOException e){
            LOG.error("未登录，输出未登录信息异常，`{}",e);
        }finally {
            if(Objects.isNull(out)){
                out.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
