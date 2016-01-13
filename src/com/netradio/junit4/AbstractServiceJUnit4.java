package com.netradio.junit4;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {"/context.xml", "classpath:/WEB-INF/dispatcher-servlet.xml", "/security.xml"} )
public abstract class AbstractServiceJUnit4 extends AbstractTransactionalJUnit4SpringContextTests {
} 
