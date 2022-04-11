package com.child.box;

import org.springframework.boot.builder.SpringApplicationBuilder;

/*
extends SpringBootServletInitializer
 */
public class ServletInitializer {

	//@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(QrzdApplication.class);
	}

}
