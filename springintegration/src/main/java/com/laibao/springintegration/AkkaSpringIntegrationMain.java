package com.laibao.springintegration;

import com.laibao.springakka.confiruration.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class AkkaSpringIntegrationMain {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfiguration.class);
        //applicationContext.scan();
        applicationContext.refresh();

        Thread.sleep(5000);
        applicationContext.registerShutdownHook();
    }
}
