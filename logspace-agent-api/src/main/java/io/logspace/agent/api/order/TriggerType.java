/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.agent.api.order;

public enum TriggerType {

    Event, Cron, Off;

    public static TriggerType get(String name) {
        for (TriggerType eachTriggerType : TriggerType.values()) {
            if (eachTriggerType.name().equalsIgnoreCase(name)) {
                return eachTriggerType;
            }
        }

        return null;
    }
}