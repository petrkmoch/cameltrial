package com.example.demo2;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PersonRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
        rest().get("hello2?name={name}").to("direct:hello2");

        from("direct:hello2")
                .log("Http Route started")
                .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.GET)
                .to("http://localhost:8080/person?name={header.name}&bridgeEndpoint=true")
                //.convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson)
                .log(LoggingLevel.INFO, "Response : ${body}");
    }
}
