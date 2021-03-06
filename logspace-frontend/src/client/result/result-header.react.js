/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
import React from 'react'
import classnames from 'classnames'

import Component from '../components/component.react'
import Editable from '../editable/editable.react'
import Chart from './result-chart.react'
import moment from 'moment'

import {default as LiteDropdown} from 'react-lite-dropdown';
import 'react-lite-dropdown/src/style.css';

import {onEditableState} from '../editable/actions'
import {saveChartTitle, setChartType, refreshResult, setAutoPlay} from './actions'

require ('./result-header.styl')

export default class Header extends Component {

  constructor(props) {
    super(props)
    
    var me = this
    setInterval(function(){me.onProgress()}, 500)

    this.state = {
      chartTypeDropdownShown: false
    }
  }

  toggleChartTypeDropdownShown() {
    const currentValue = this.state.chartTypeDropdownShown 
    this.setState({ chartTypeDropdownShown: !currentValue})
  }

  onProgress() {
    const label = document.getElementById('progress')

    if (!label) {
      return
    }

    if (!this.props.autoPlaySchedule) {
      label.innerHTML = 15
      return
    }

    const difference = moment().diff(this.props.autoPlaySchedule, 'seconds') 
    label.innerHTML = (15-difference)
  }

  onChartTitleSaved(title, hide) {
    saveChartTitle(title)
    hide()
  }

  getPlayControls() {
    if (this.props.autoPlay) {
           return (
        <span>
          <span className='option pause' onClick={() => setAutoPlay(false)}/>
          <span className='option progress' onClick={refreshResult}>
            <span id='progress'> 15 </span>
          </span>
        </span>
      )
    }

    return (
      <span>
        <span className='option play' onClick={() => setAutoPlay(true)} />
        <span className='option refresh' onClick={refreshResult}/>
      </span>  
    )
  }

  render() {
    const playControls = this.getPlayControls()
    const closeIcon = '<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" x="0px" y="0px" width="20px" height="20px" viewBox="0 0 512 512" style="enable-background:new 0 0 512 512;" xml:space="preserve"><path d="M437.5,386.6L306.9,256l130.6-130.6c14.1-14.1,14.1-36.8,0-50.9c-14.1-14.1-36.8-14.1-50.9,0L256,205.1L125.4,74.5  c-14.1-14.1-36.8-14.1-50.9,0c-14.1,14.1-14.1,36.8,0,50.9L205.1,256L74.5,386.6c-14.1,14.1-14.1,36.8,0,50.9  c14.1,14.1,36.8,14.1,50.9,0L256,306.9l130.6,130.6c14.1,14.1,36.8,14.1,50.9,0C451.5,423.4,451.5,400.6,437.5,386.6z" fill="#FFFFFF"/></svg>'

    return (
      <div className='chart-header'>
        <div className='chart-options'>
          {playControls}
           <LiteDropdown
            displayText={this.props.chartType}
            defaultText={'not used'}
            show={this.state.chartTypeDropdownShown}
            onToggle={() => this.toggleChartTypeDropdownShown()}
            name={'css-hook-demo'}>
              <div className={'item'}  onClick={() => setChartType('bar')}>Bar</div>
              <div className={'item'}  onClick={() => setChartType('line')}>Line</div>
              <div className={'item'}  onClick={() => setChartType('spline')}>Spline</div>
              <div className={'item'}  onClick={() => setChartType('step')}>Step</div>
              <div className={'item'}  onClick={() => setChartType('area')}>Area line</div>
              <div className={'item'}  onClick={() => setChartType('area-spline')}>Area spline</div>
              <div className={'item'}  onClick={() => setChartType('area-step')}>Area step</div>
              <div className={'item'}  onClick={() => setChartType('scatter')}>Scatter</div>
          </LiteDropdown>
        </div>
        <div className="chart-title">
          <Editable
            defaultValue={this.props.chartTitle}
            disabled={false}
            id={'result'}
            isRequired
            maxLength={200}
            name={'chartTitle'}
            onSave={(title, hide) => (this.onChartTitleSaved(title, hide))}
            onState={onEditableState}
            state={this.props.chartTitleEditable}
          >
            <label>{this.props.chartTitle}</label>
          </Editable>
        </div>  

      </div>  
    )
  }
}
