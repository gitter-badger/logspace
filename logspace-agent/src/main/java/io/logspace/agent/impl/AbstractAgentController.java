/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.agent.impl;

import io.logspace.agent.api.Agent;
import io.logspace.agent.api.AgentController;
import io.logspace.agent.api.event.Event;
import io.logspace.agent.api.order.AgentControllerCapabilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAgentController implements AgentController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, Agent> agents = new ConcurrentHashMap<String, Agent>();

    private String id;
    private String system;

    protected AbstractAgentController() {
        super();

        this.initalizeSystem();
    }

    @Override
    public void flush() {
        // default does nothing
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getSystem() {
        return this.system;
    }

    @Override
    public boolean isAgentEnabled(String agentId) {
        return true;
    }

    @Override
    public final void register(Agent agent) {
        this.agents.put(agent.getId(), agent);

        this.onAgentRegistered(agent);
    }

    @Override
    public void send(Event event) {
        this.send(Collections.singleton(event));
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public void shutdown() {
        // default does nothing
    }

    @Override
    public final void unregister(Agent agent) {
        this.agents.remove(agent.getId());

        this.onAgentUnregistered(agent);
    }

    protected Agent getAgent(String agentId) {
        return this.agents.get(agentId);
    }

    protected Collection<String> getAgentIds() {
        return this.agents.keySet();
    }

    protected Iterable<Agent> getAgents() {
        return this.agents.values();
    }

    protected AgentControllerCapabilities getCapabilities() {
        AgentControllerCapabilities result = new AgentControllerCapabilities();

        result.setId(this.getId());

        for (Agent eachAgent : this.getAgents()) {
            result.add(eachAgent.getCapabilities());
        }

        return result;
    }

    /**
     * Called when an {@link Agent} has registered itself with this {@link AgentController}.
     *
     * @param agent The registered agent.
     */
    protected void onAgentRegistered(Agent agent) {
        // default does nothing
    }

    /**
     * Called when an {@link Agent} has unregistered itself from this {@link AgentController}.
     *
     * @param agent The unregistered agent.
     */
    protected void onAgentUnregistered(Agent agent) {
        // default does nothing
    }

    private void initalizeSystem() {
        try {
            this.system = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            this.logger.error("Failed to retrieve host name.", e);
        }
    }
}