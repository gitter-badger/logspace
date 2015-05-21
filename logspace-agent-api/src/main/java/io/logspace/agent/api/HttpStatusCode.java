/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.agent.api;

public enum HttpStatusCode {

    Accepted(202), NotModified(304), BadRequest(400), Forbidden(403), NotFound(404), InternalServerError(500);

    private final int code;

    private HttpStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public boolean matches(int statusCode) {
        return this.code == statusCode;
    }
}