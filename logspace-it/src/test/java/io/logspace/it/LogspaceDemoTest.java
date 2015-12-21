/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.it;

import static com.indoqa.system.test.tools.JavaProcessRunnerUtils.*;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.ClassRule;
import org.junit.Test;

import com.indoqa.system.test.tools.JavaProcessRunner;
import com.indoqa.system.test.tools.JavaProcessRunnerBuilder;

public class LogspaceDemoTest {

    private static final String PORT = "4567";
    private static final String BASE_URL = "http://localhost:" + PORT;

    private static final Path HQ_RUNNABLE = searchJavaRunnable(Paths.get("../logspace-hq-webapp/target/"), endsWithRunnableJar());
    private static final Path MONITOR_RUNNABLE = searchJavaRunnable(Paths.get("../logspace-monitor/target/"), endsWithRunnableJar());

    @ClassRule
    public static final JavaProcessRunner HQ_RUNNER = new JavaProcessRunnerBuilder(HQ_RUNNABLE)
        .preInitialization(LogspaceDemoTest::cleanupDemoDirectory)
        .setCheckAdress(BASE_URL + "/system-info")
        .addArguments("--demo")
        .build();

    @ClassRule
    public static final JavaProcessRunner MONITOR_RUNNER = new JavaProcessRunnerBuilder(MONITOR_RUNNABLE)
        .setAlwaysWait(1000)
        .addArguments("--demo")
        .build();

    private static void cleanupDemoDirectory() {
        try {
            FileUtils.cleanDirectory(new File(System.getProperty("java.io.tmpdir"), "logspace-demo"));
        } catch (IOException e) {
            throw new LogspaceTestException("Error while cleaning Logspace demo directory.", e);
        }
    }

    @Test
    public void test() throws InterruptedException {
        System.out.println("test");
        SECONDS.sleep(30);
    }
}
