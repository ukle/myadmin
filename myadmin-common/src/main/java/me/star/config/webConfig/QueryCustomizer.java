package me.star.config.webConfig;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * @author bearBoy80
 */
@Configuration(proxyBeanMethods = false)
public class QueryCustomizer implements TomcatConnectorCustomizer {
    @Override
    public void customize(Connector connector) {
        connector.setProperty("relaxedQueryChars", "[]{}");
    }
}
