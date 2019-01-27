var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var ejs = require('ejs');

var homePageRouter = require('./routes/homePageRouter');
var ticketSearchRouter = require('./routes/ticketSearchRouter');
var ticketSearchResultRouter = require('./routes/ticketSearchResultRouter');
var addBrushTicketTaskRouter = require('./routes/addBrushTicketTaskRouter');
var brushTicketTaskListRouter = require('./routes/brushTicketTaskListRouter');
var brushTicketTaskListFinishedRouter = require('./routes/brushTicketTaskListFinishedRouter');
var brushTicketTaskListStoppedRouter = require('./routes/brushTicketTaskListStoppedRouter');

var app = express();

// view engine setup
app.engine('.htm', ejs.__express);
app.set('view engine', 'htm');
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', homePageRouter);
app.use('/homePage', homePageRouter);
app.use('/ticketSearch', ticketSearchRouter);
app.use('/ticketSearchResult', ticketSearchResultRouter);
app.use('/addBrushTicketTask', addBrushTicketTaskRouter);
app.use('/brushTicketTaskList', brushTicketTaskListRouter);
app.use('/brushTicketTaskListFinished', brushTicketTaskListFinishedRouter);
app.use('/brushTicketTaskListStopped', brushTicketTaskListStoppedRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
