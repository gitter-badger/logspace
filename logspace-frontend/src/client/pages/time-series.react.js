/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
import React from 'react'
import {Link} from 'react-router'
import classnames from 'classnames'

import AddTimeSerie from '../time-series/add-time-series.react'
import TimeSeriesList  from '../time-series/time-series-list.react'
import Chart from '../result/result-chart.react'
import Drawer from '../drawer/drawer.react'
import Header from '../header/header.react'
import TimeWindowValues from '../time-window/time-window-values.react'

import {getTimeSeries} from '../time-series/store'
import {getActivePanel} from '../drawer/store'
import {getResult} from '../result/store'
import {getSuggestions} from '../suggestions/store'
import {getTimeWindow} from '../time-window/store'
import {onShowSuggestions} from '../suggestions/actions'

require('./time-series.styl')

export default class TimeSeries extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      navDrawerCss: 'navigation-drawer',
      mainCss: 'main'
    };
  }

  toggleNavigationDrawer() {
    this.setState(
      {
        navDrawerCss: {
          'navigation-drawer' : true,
          'navigation-drawer-expanded' : !this.state.navDrawerCss['navigation-drawer-expanded']
        },
        mainCss: {
          'main' : true,
          'main-reduced' : !this.state.mainCss['main-reduced']
        }
      });
    this.forceUpdate()
  }

  render() {
    return (
      <div className='time-series'>
        <Header />

        <div className={classnames(this.state.navDrawerCss)}>
          <div className="left">
            <TimeWindowValues />

            <TimeSeriesList items={getTimeSeries()} />

            <div className='add-series-entry'>
              <button className='btn-floating btn-large waves-effect btn-highlight' onClick={() => onShowSuggestions()}>
                <i>+</i>
              </button>
            </div>

            <div className='tools'>
              Tools
            </div>

          </div>
          <div className="right">
            <Drawer
              activePanel={getActivePanel()}
              suggestions={getSuggestions()}
              timeWindow={getTimeWindow()}
              toggle={() => this.toggleNavigationDrawer()} />
          </div>

        </div>

        <div className={classnames(this.state.mainCss)}>
          <Chart series={getTimeSeries()} result={getResult()}/>
        </div>
      </div>
    )
  }

}
