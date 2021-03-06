/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
import DocumentTitle from 'react-document-title'
import React from 'react'
import {Link, RouteHandler} from 'react-router'

import Component from '../components/component.react';

import * as appState from '../state'

import '../intl/store'
import '../time-window/store'
import '../time-series/store'
import '../result/store'
import '../suggestions/store'
import '../drawer/store'
import '../editable/store'
import '../agent-activity/store'

require('./app.styl');

export default class App extends Component {

  constructor(props) {
    super(props)
    this.state = this.getState()
  }

  getState() {
    return {
      i18n: appState.i18nCursor(),
      timeWindow: appState.timeWindowCursor().get('selection'),
      timeWindowDynamic: appState.timeWindowCursor().get('dynamic'),
      timeWindowActiveTab: appState.viewCursor().get('activeTimeWindowTab'),
      timeSeries: appState.timeSeriesCursor(),
      editedTimeSeries: appState.editedTimeSeriesCursor(),
      result: appState.resultCursor().get('translatedResult'),
      autoPlay: appState.resultCursor().get('autoPlay'),
      autoPlaySchedule: appState.resultCursor().get('autoPlaySchedule'),
      chartTitle: appState.resultCursor().get('chartTitle'),
      chartTitleEditable: appState.viewCursor().get('editables').get('result'),
      chartType: appState.resultCursor().get('chartType'),
      suggestions: appState.suggestionCursor(),
      view: appState.viewCursor(),
      agentActivity: appState.agentActivityCursor()
    };
  }

  componentDidMount() {
    appState.state.on('change', () => {
      console.time('app render') // eslint-disable-line no-console
      this.setState(this.getState(), () => {
        console.timeEnd('app render') // eslint-disable-line no-console
      })
    })
  }

  render() {
    return (
      <DocumentTitle title='logspace.io'>
        <div className='page'>
          <RouteHandler {...this.state} />
        </div>
      </DocumentTitle>
    )
  }
}
