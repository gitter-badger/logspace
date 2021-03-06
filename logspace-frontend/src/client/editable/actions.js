/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */

import {dispatch} from '../dispatcher';
import setToString from '../../lib/settostring';

export function onEditableState(id, name, state) {
  dispatch(onEditableState, {id, name, state});
}

setToString('editable', {
  onEditableState
});  