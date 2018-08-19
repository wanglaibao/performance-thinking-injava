package com.laibao.springakka.extension;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 */
public class SpringExtension extends AbstractExtensionId<SpringExt> {

    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExt();
    }

}
