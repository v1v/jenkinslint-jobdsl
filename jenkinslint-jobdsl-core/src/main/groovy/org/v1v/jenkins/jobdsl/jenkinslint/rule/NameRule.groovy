package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.Item

class NameRule extends AbstractRule{

    NameRule() {
        name = 'Name'
        description = 'Description of Name'
    }

    public boolean isDefect(Item item) {
        boolean defect = false
        if (!isIgnored(item)) {
            if (item.getName() != null && item.getName().contains(' ')) {
                defect = true
            }
        }
        return defect
    }
}
