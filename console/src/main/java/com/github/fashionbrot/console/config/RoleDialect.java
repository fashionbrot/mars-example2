package com.github.fashionbrot.console.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class RoleDialect extends AbstractDialect implements IProcessorDialect {

    public static final String NAME = "mars";
    public static final String DEFAULT_PREFIX = "hasRole";

    @Autowired
    private AuthenticationProcessor authenticationProcessor;

    public RoleDialect() {
        super(NAME);
    }

    @Override
    public String getPrefix() {
        return DEFAULT_PREFIX;
    }

    @Override
    public int getDialectProcessorPrecedence() {
        return 0;
    }

    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(authenticationProcessor);
        return processors;

    }


}
