/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
import React from 'react';
import Component from '../components/component.react';
import {onSpaceSelected, onSpaceCleared} from './actions';

export default class SuggestionSpaces extends Component {
  
  render() {
    if (this.props.selected != null) {
      return (
        <div>
          <ul>
              <li onClick={() => onSpaceCleared()}> {this.props.selected.get('name')} (x) </li>
           </ul> 
        </div>
      );
    }

    return (
      <div>
        <ul>
          {this.props.spaces.map(function(item, index) {
            return <li key={index} onClick={() => onSpaceSelected(item)}> {item.get('name')} </li>;
          })}    
        </ul>
      </div>
    );
  }
}