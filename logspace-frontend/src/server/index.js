/*
 * Logspace
 * Copyright (c) 2015 Indoqa Software Design und Beratung GmbH. All rights reserved.
 * This program and the accompanying materials are made available under the terms of
 * the Eclipse Public License Version 1.0, which accompanies this distribution and
 * is available at http://www.eclipse.org/legal/epl-v10.html.
 */
var express = require('express');
var proxy = require('express-http-proxy');

var port = 8000;
var app = express();

app.use('/build', express.static('build'));
app.use('/assets', express.static('assets'));
app.use('/', express.static('site'));

app.use('', proxy('http://localhost:4567', {
	forwardPath: function(req, res) {
		return require('url').parse(req.url).path;
	}
}));

app.listen(port);

console.log('App started on port ' + port);
