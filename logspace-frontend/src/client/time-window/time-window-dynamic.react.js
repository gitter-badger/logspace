/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */

import React from 'react';
import Immutable from 'immutable'
import Component from '../components/component.react'
import moment from 'moment-range'

import GapSelection from './time-window-gapselection.react.js'

import {selectDynamicDate} from './actions';
import {units, selections} from './constants'

require('./time-window.styl')

export default class TimeWindowDynamic extends Component {

  constructor(props) {
    super(props);

    this.state = {
      localState: Immutable.fromJS({
         range: {
          amount: props.timeWindow.get("dynamicDuration"),
          unit: props.timeWindow.get("dynamicUnit")
         }
      })
    }
  }

   onRangeChange(value) {
    this.setState({
      localState: this.state.localState.merge({
        range: {
          amount: value.get('amount'),
          unit: value.get('unit')
        }
      })
    })
  }

  submit() {
    const state = this.state.localState.toJS()
    selectDynamicDate(state.range.amount, state.range.unit, {amount: 1, unit: state.range.unit})
  }

  render() {


    return (
      <div>
         <div className='selection'>
          <div className='submit' >
             <button className='waves-effect waves-light btn btn-small' onClick={() => this.submit()}>
              Apply
             </button>
          </div>
          <div className='dynamic'>
            <span className='intro'>last </span>
            <GapSelection value={this.state.localState.get('range')} onChange={this.onRangeChange.bind(this)}/>
          </div>
        </div>
      </div>
    )
  }
}
