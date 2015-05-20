/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.agent.os;

import io.logspace.agent.api.AbstractAgent;
import io.logspace.agent.api.AgentControllerProvider;
import io.logspace.agent.api.order.AgentOrder;
import io.logspace.agent.api.order.TriggerType;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public final class SwapAgent extends AbstractAgent {

    private SwapAgent(String agentId) {
        super(agentId, "os/swap", TriggerType.Off, TriggerType.Cron);

        this.setAgentController(AgentControllerProvider.getAgentController());
    }

    public static SwapAgent create(String agentId) {
        return new SwapAgent(agentId);
    }

    @Override
    public void execute(AgentOrder agentOrder) {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        if (operatingSystemMXBean == null) {
            return;
        }

        OsEventBuilder eventBuilder = OsEventBuilder.createCpuBuilder(this.getId(), this.getSystem());

        long totalSwapSpace = operatingSystemMXBean.getTotalSwapSpaceSize();
        long freeSwapSpace = operatingSystemMXBean.getFreeSwapSpaceSize();
        long usedSwapSpace = totalSwapSpace - freeSwapSpace;

        eventBuilder.setTotalSwapSpace(totalSwapSpace);
        eventBuilder.setFreeSwapSpace(freeSwapSpace);
        eventBuilder.setUsedSwapSpace(usedSwapSpace);

        this.sendEvent(eventBuilder.toEvent());
    }
}
