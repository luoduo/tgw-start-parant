package com.tgw.tgwstartws.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

/**
 * @Title: WebServiceConfig.java
 * @Description: TODO(WebService配置)
 * @Author: 爱飘de小子  16:13
 * @Date: 2018年08月03日 16点13分
 */

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }
    
    @Bean(name = "sso")
    public Wsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SSOWebServiceSoap");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://tempuri.org/");
        wsdl11Definition.setSchema(countriesSchema);
        wsdl11Definition.setCreateSoap11Binding(true);
        wsdl11Definition.setCreateSoap12Binding(true);
        Properties soapActions = new Properties();
        soapActions.setProperty("ApplicationRegister", "http://tempuri.org/ApplicationRegister");
        soapActions.setProperty("LoginVerify", "http://tempuri.org/LoginVerify");
        wsdl11Definition.setSoapActions(soapActions);
        return wsdl11Definition;
    }
    
    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("sso.xsd"));
    }
}
