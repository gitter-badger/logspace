/**
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package io.logspace.hq.rest.model;

import static io.logspace.agent.api.HttpStatusCode.Forbidden;

import io.logspace.hq.core.api.model.AbstractLogspaceResourceException;

public class MissingSpaceTokenException extends AbstractLogspaceResourceException {

    private static final long serialVersionUID = 1L;

    public MissingSpaceTokenException(String message) {
        super(message, Forbidden, "MISSING_SPACE_TOKEN");
    }
}
