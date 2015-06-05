/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.hq.webapp;

import io.logspace.hq.webapp.resource.EmbeddedStaticResources;
import io.logspace.hq.webapp.resource.ExternalStaticResources;

import java.io.IOException;

import spark.utils.IOUtils;

import com.indoqa.boot.AbstractIndoqaBootApplication;

public class LogspaceHq extends AbstractIndoqaBootApplication {

    private static final String APPLICATION_NAME = "Logspace";
    private static final String BASE_PACKAGE = "io.logspace";
    private static final String LOGO_PATH = "/logspace.io.txt";
    private static final String FRONTEND_INDEX_HTML_PATH = "/logspace-frontend/index.html";

    private HqMode hqMode = new DefaultHqMode();

    public static void main(String[] args) {
        LogspaceHq logspaceHq = new LogspaceHq();

        if (hasArgument(args, "--demo")) {
            logspaceHq.hqMode = new DemoHqMode();
        }

        logspaceHq.invoke();
    }

    private static boolean hasArgument(String[] arguments, String argument) {
        for (String eachArgument : arguments) {
            if (eachArgument.equalsIgnoreCase(argument)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void afterInitialization() {
        this.hqMode.afterInitialization();
    }

    @Override
    protected void beforeInitialization() {
        this.printLogo();

        this.hqMode.beforeInitialization();
    }

    @Override
    protected String getApplicationName() {
        return APPLICATION_NAME;
    }

    @Override
    protected String getComponentScanBasePackage() {
        return BASE_PACKAGE;
    }

    @Override
    protected void initializeSpringBeans() {
        this.getApplicationContext().register(EmbeddedStaticResources.class);
        this.getApplicationContext().register(ExternalStaticResources.class);
    }

    @Override
    protected boolean isDevEnvironment() {
        return LogspaceHq.class.getResourceAsStream(FRONTEND_INDEX_HTML_PATH) == null;
    }

    private void printLogo() {
        try {
            String asciiLogo = IOUtils.toString(LogspaceHq.class.getResourceAsStream(LOGO_PATH));
            if (asciiLogo == null) {
                return;
            }
            this.getInitializationLogger().info(asciiLogo);
        } catch (IOException e) {
            throw new ApplicationInitializationException("Error while reading ASCII logo.", e);
        }
    }
}
