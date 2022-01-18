package com.xl.demo.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author XL
 * 注册 Sa-Token 路由拦截器
 * @date 2022/1/11 16:15
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
//        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
//            // 根据路由划分模块，不同模块不同鉴权
//            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
//            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
//            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
//            SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
//            SaRouter.match("/test/**", r -> StpUtil.hasRole("admin"));
//        })).addPathPatterns("/**").excludePathPatterns("/test/demo","/login");

        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/test/**","/login",
                        "/doc.html","/swagger-resources/**", "/webjars/**",
                        "/v2/**", "/swagger-ui.html/**");

    }
}
