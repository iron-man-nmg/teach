package com.nmg.teach;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * teach Web程序启动类
 *
 * @author xiadingli
 * @date 2017-05-21 9:43
 */
public class TeachServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TeachApplication.class);
    }

}
