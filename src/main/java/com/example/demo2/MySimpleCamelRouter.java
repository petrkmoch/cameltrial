package com.example.demo2;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestOperationParamDefinition;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class MySimpleCamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        RestOperationParamDefinition param = new RestOperationParamDefinition();
        param.setType(RestParamType.query);
        param.setName("word");
        rest().get("/hello").param(param).to("direct:hello");

        from("direct:hello").log(LoggingLevel.INFO, "Hello world").transform().simple("Hello world!");
    }
}
