/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */

import {Dispatcher} from 'flux'
import {register} from '../dispatcher'
import moment from 'moment'
import Immutable from 'immutable'

import {timeWindowCursor} from '../state'
import * as actions from './actions'

import {TimeWindowSelection} from './constants'

export const TimeWindowStore_dispatchToken = register(({action, data}) => {

  switch (action) {
    case actions.selectPredefinedDate:
      timeWindowCursor(timeWindow => {
        return timeWindow.set('selection', data)
      })
      break

    case actions.selectCustomDate:
      const customLabel = moment(data.start).format("YY-MM-DD") 
        + "<span class='small'> " + moment(data.start).format("HH:mm") + "</span>"
        + " - " 
        + moment(data.end).format("YY-MM-DD")
        + "<span class='small'> " + moment(data.end).format("HH:mm") + "</span>"

      const customSelection = new TimeWindowSelection({
        label: customLabel,
        start: () => moment(data.start), 
        end: () => moment(data.end),
        gap: Immutable.fromJS(data.gap)
      })

      timeWindowCursor(timeWindow => {
        return timeWindow.set('selection', customSelection)
      })
      break

    case actions.selectDynamicDate:
      const dynamicSelection = new TimeWindowSelection({
        label: 'last ' + data.duration + ' ' + data.unit.label,
        start: () => moment().subtract(data.duration, data.unit), 
        end: () => moment(),
        dynamicDuration: data.duration,
        dynamicUnit: data.unit,
        gap: Immutable.fromJS(data.gap)
      })

      timeWindowCursor(timeWindow => {
        return timeWindow.set('selection', dynamicSelection)
      })
      break

    case actions.onTabOpen:
      timeWindowCursor(timeWindow => {
        return timeWindow.set('activeTab', data)
      })
      break  
  }
})
