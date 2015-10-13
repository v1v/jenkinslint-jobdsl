package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.Item

/**
 * @author victor.martinez.
 */
public interface Rule {

    boolean isDefect(Item item);

    boolean isIgnored(Item item);

}
