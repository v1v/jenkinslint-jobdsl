package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.Item

/**
 * @author victor.martinez.
 */

public abstract class AbstractRule implements Rule {

    /**
     * Flag indicating whether this rule should be enabled (applied). Defaults to true.
     * If set to false, this rule will not produce any violations.
     */
    private boolean enabled = true;

    /**
     * If not null, this is used as the description text for this rule, overriding any
     * description text found in the i18n resource bundles. Defaults to null.
     */
    protected String description;

    protected String name;

    /**
     * Allows rules to perform validation. Do nothing by default.
     * This method is provided as a placeholder so subclasses can optionally override.
     * Subclasses will typically use <code>assert</code> calls to verify required preconditions.
     */
    public abstract boolean isDefect(Item item);

    /**
     * Allows rules to perform validation. Do nothing by default.
     * This method is provided as a placeholder so subclasses can optionally override.
     * Subclasses will typically use <code>assert</code> calls to verify required preconditions.
     */
    public void validate() {
    }

    @Override
    boolean isIgnored(Item item) {
        boolean ignored = false
        if (item != null && item.getNode().get('description') != null &&
                item.getNode().get('description').text().toLowerCase().contains(this.name.toLowerCase())) {
            ignored = true
        }
        return ignored
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getDescription() {
        return description;
    }

    /**
     * @return the unique name for this rule
     */
    public String getName() {
        return name;
    }

    private String getClassNameNoPackage() {
        String className = getClass().getName();
        int indexOfLastPeriod = className.lastIndexOf('.');
        return (indexOfLastPeriod == -1) ? className : className.substring(indexOfLastPeriod + 1);
    }


    public String toString() {
        return String.format(
                "%s[name=%s, description=%s]",
                getClassNameNoPackage(),
                getName(),
                getDescription()
        );
    }
}
