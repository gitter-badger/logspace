/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.jvm.agent;

import io.logspace.agent.api.event.AbstractEventBuilder;
import io.logspace.agent.api.event.Optional;

public class JvmEventBuilder extends AbstractEventBuilder {

    public static final String PROPERTY_JVM_IDENTIFIER = "jvm-identifier";

    public static final String PROPERTY_INITIAL_HEAP_MEMORY = "initial_heap_memory";
    public static final String PROPERTY_MAX_HEAP_MEMORY = "max_heap_memory";
    public static final String PROPERTY_USED_HEAP_MEMORY = "used_heap_memory";
    public static final String PROPERTY_COMMITED_HEAP_MEMORY = "commited_heap_memory";

    public static final String PROPERTY_INITIAL_NON_HEAP_MEMORY = "initial_non_heap_memory";
    public static final String PROPERTY_MAX_NON_HEAP_MEMORY = "max_non_heap_memory";
    public static final String PROPERTY_USED_NON_HEAP_MEMORY = "used_non_heap_memory";
    public static final String PROPERTY_COMMITED_NON_HEAP_MEMORY = "commited_non_heap_memory";

    public static final String PROPERTY_OBJECT_PENDING_FINALIZATION_COUNT = "object_pending_finalization_count";

    public static final String PROPERTY_LOADED_CLASS_COUNT = "loaded_class_count";
    public static final String PROPERTY_TOTAL_LOADED_CLASS_COUNT = "total_loaded_class_count";
    public static final String PROPERTY_UNLOADED_CLASS_COUNT = "unloaded_class_count";

    public static final String PROPERTY_THREAD_COUNT = "thread_count";
    public static final String PROPERTY_DAEMON_THREAD_COUNT = "daemon_thread_count";

    public static final String PROPERTY_GARBAGE_COLLECTOR_RUN_COUNT = "garbagecollector_run_count";
    public static final String PROPERTY_GARBAGE_COLLECTOR_TIME = "garbagecollector_time";

    public static final String PROPERTY_OPEN_FILE_DESCRIPTOR_COUNT = "open_file_descriptor_count";
    public static final String PROPERTY_MAX_FILE_DESCRIPTOR_COUNT = "max_file_descriptor_count";

    public static final String PROPERTY_PROCESS_CPU_LOAD = "process_cpu_load";
    public static final String PROPERTY_PROCESS_CPU_TIME = "process_cpu_time";

    private static final Optional<String> JVM_EVENT_TYPE = Optional.of("jvm");

    private Optional<String> eventType;

    private JvmEventBuilder(String agentId, String system, Optional<String> eventType) {
        super(agentId, system);

        this.eventType = eventType;
    }

    public static JvmEventBuilder createJvmBuilder(String agentId, String system) {
        return new JvmEventBuilder(agentId, system, JVM_EVENT_TYPE);
    }

    public void setCommitedHeapMemory(long commitedHeapMemory) {
        this.addProperty(PROPERTY_COMMITED_HEAP_MEMORY, commitedHeapMemory);
    }

    public void setCommitedNonHeapMemory(long commitedNonHeapMemory) {
        this.addProperty(PROPERTY_COMMITED_NON_HEAP_MEMORY, commitedNonHeapMemory);
    }

    public void setDaemonThreadCount(int daemonThreadCount) {
        this.addProperty(PROPERTY_DAEMON_THREAD_COUNT, daemonThreadCount);
    }

    public void setGarbageCollectorRunCount(String garbageCollectorName, long runCount) {
        this.addProperty(this.normalizeName(garbageCollectorName) + "-" + PROPERTY_GARBAGE_COLLECTOR_RUN_COUNT, runCount);
    }

    public void setGarbageCollectorTime(String garbageCollectorName, long collectionTime) {
        this.addProperty(this.normalizeName(garbageCollectorName) + "-" + PROPERTY_GARBAGE_COLLECTOR_TIME, collectionTime);
    }

    public void setInitialHeapMemory(long initialHeapMemory) {
        this.addProperty(PROPERTY_INITIAL_HEAP_MEMORY, initialHeapMemory);
    }

    public void setInitialNonHeapMemory(long initialNonHeapMemory) {
        this.addProperty(PROPERTY_INITIAL_NON_HEAP_MEMORY, initialNonHeapMemory);
    }

    public void setLoadedClassCount(int loadedClassCount) {
        this.addProperty(PROPERTY_LOADED_CLASS_COUNT, loadedClassCount);
    }

    public void setMaxFileDescriptorCount(long maxFileDescriptorCount) {
        this.addProperty(PROPERTY_MAX_FILE_DESCRIPTOR_COUNT, maxFileDescriptorCount);
    }

    public void setMaxHeapMemory(long maxHeapMemory) {
        this.addProperty(PROPERTY_MAX_HEAP_MEMORY, maxHeapMemory);
    }

    public void setMaxNonHeapMemory(long maxNonHeapMemory) {
        this.addProperty(PROPERTY_MAX_NON_HEAP_MEMORY, maxNonHeapMemory);
    }

    public void setObjectPendingFinalizationCount(int objectPendingFinalizationCount) {
        this.addProperty(PROPERTY_OBJECT_PENDING_FINALIZATION_COUNT, objectPendingFinalizationCount);
    }

    public void setOpenFileDescriptorCount(long openFileDescriptorCount) {
        this.addProperty(PROPERTY_OPEN_FILE_DESCRIPTOR_COUNT, openFileDescriptorCount);
    }

    public void setProcessCpuLoad(double processCpuLoad) {
        this.addProperty(PROPERTY_PROCESS_CPU_LOAD, processCpuLoad);
    }

    public void setProcessCpuTime(double processCpuTime) {
        this.addProperty(PROPERTY_PROCESS_CPU_TIME, processCpuTime);
    }

    public void setThreadCount(int threadCount) {
        this.addProperty(PROPERTY_THREAD_COUNT, threadCount);
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.addProperty(PROPERTY_TOTAL_LOADED_CLASS_COUNT, totalLoadedClassCount);
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.addProperty(PROPERTY_UNLOADED_CLASS_COUNT, unloadedClassCount);
    }

    public void setUsedHeapMemory(long usedHeapMemory) {
        this.addProperty(PROPERTY_USED_HEAP_MEMORY, usedHeapMemory);
    }

    public void setUsedNonHeapMemory(long usedNonHeapMemory) {
        this.addProperty(PROPERTY_USED_NON_HEAP_MEMORY, usedNonHeapMemory);
    }

    @Override
    protected Optional<String> getType() {
        return this.eventType;
    }

    private String normalizeName(String name) {
        return name.toLowerCase().replace(' ', '-');
    }
}
