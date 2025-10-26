package com.ctoutweb.aet.infra.config.scan;

import com.ctoutweb.aet.domain.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.ctoutweb.aet.domain",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class})}
)
public class DomainAnnoationScan {
}
