package com.ctoutweb.aet.infra.config.scan;

import com.ctoutweb.aet.core.annotation.CoreService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.ctoutweb.aet.core",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {CoreService.class})}
)
public class CoreAnnoationScan {
}
