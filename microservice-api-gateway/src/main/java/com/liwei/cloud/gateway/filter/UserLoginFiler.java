package com.liwei.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginFiler extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";  //设置过滤器类型
    }

    @Override
    public int filterOrder() {
        return 0;   //设置执行顺序
    }

    @Override
    public boolean shouldFilter() {
        return true;  //该过滤器需要执行
    }

    @Override
    public Object run() throws ZuulException { //编写业务逻辑
        RequestContext requestContext = RequestContext.getCurrentContext(); //获取当前上下文
        HttpServletRequest request = requestContext.getRequest();//获取当前请求
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false); //过滤该请求，不对其进行路由
            requestContext.setResponseStatusCode(401); //设置响应状态码
            requestContext.setResponseBody("token is empty!!!"); //设置相应状态码
            return null;
        }

        return null;
    }
}
