package com.tw;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-text")
@Produces(MediaType.TEXT_HTML)
public class TextResource {
    private final AtomicLong counter;
    private String template;
    private String defaultName;

    public TextResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public String sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(), String.format(template, name.or(defaultName))).toString();
    }
}
