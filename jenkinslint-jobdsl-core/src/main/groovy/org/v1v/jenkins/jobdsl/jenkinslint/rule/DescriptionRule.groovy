package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.Item

import java.util.logging.Logger

class DescriptionRule extends AbstractRule{

    DescriptionRule () {
        name = 'Description'
        description = 'Description of description'
    }

    public boolean isDefect(Item item) {
        boolean defect = true
        if (item.getNode() != null &&
                item.getNode().get('description') != null &&
                !item.getNode().get('description').text().equals("")) {
            defect = false
        }
        return defect
    }
}
