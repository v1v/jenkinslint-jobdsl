package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.*

import java.util.logging.Logger

class LabelRule extends AbstractRule{

    LabelRule () {
        name = 'Label'
        description = 'Description of Label'
    }


    public boolean isDefect(Item item) {
        boolean defect = true
        if (!isIgnored(item)) {
            if (item.getNode() != null &&
                    item.getNode().get('assignedNode') != null &&
                    !item.getNode().get('assignedNode').text().equals("")) {
                defect = false
            }
        } else {
            defect = false
        }
        return defect
    }
}
