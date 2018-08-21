package com.laibao.springintegration.springextension;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class AkkaSpringExtension extends AbstractExtensionId<SpringExt> {

    public static AkkaSpringExtension springExtProvider = new AkkaSpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExt();
    }
}
