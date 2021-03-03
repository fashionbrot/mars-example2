package com.github.fashionbrot.console.config;

import com.github.fashionbrot.core.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.processor.AbstractStandardConditionalVisibilityTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

@Component
public class AuthenticationProcessor extends AbstractStandardConditionalVisibilityTagProcessor {

    @Autowired
    private RoleInfoService roleInfoService;

    public AuthenticationProcessor() {
        super(TemplateMode.HTML, "mars", "hasRole", 300);
    }

    protected boolean isVisible(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue) {

        return true;//roleInfoService.checkRole(attributeValue);
    }

    /**
     * html头 添加
     * xmlns:mars="http://www.thymeleaf.org"
     *
     * html 标签使用  mars:hasRole="role:add"
     */
}