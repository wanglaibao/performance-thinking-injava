package com.laibao.springbootakka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by A on 2018/8/20.
 */
@Service
public class BusinessService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void perform(Object o) {
        logger.info("Perform: {}", o);
        System.out.println("ASDASFDAFS");
    }
}
