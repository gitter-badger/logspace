/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */

import React from 'react';
import immutable from 'immutable';
import Component from '../components/component.react';
import TimeSeriesItem  from '../time-series/time-series-item.react'

require('./time-series-list.styl')

export default class TimeSeriesList extends Component {
  render() {
    const items = this.props.items;

    return (
      <div className='time-series-list'>
        {items.map(function(item) {
          return <TimeSeriesItem key={item.get("id")} item={item} />;
        })}
      </div>
    )
  }
}

TimeSeriesList.propTypes = {
  items: React.PropTypes.instanceOf(immutable.List)
};