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
var brushTicketTaskListStartedRouter = require('./routes/brushTicketTaskListStartedRouter');
var gotTicketListRouter = require('./routes/gotTicketListRouter');
var gotTicketDetailRouter = require('./routes/gotTicketDetailRouter');
var loginRouter = require('./routes/loginRouter');
var loginFailedRouter = require('./routes/loginFailedRouter');
var loginSuccessRouter = require('./routes/loginSuccessRouter');
var memberRouter = require('./routes/memberRouter');

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
app.use('/brushTicketTaskListStarted', brushTicketTaskListStartedRouter);
app.use('/gotTicketList', gotTicketListRouter);
app.use('/gotTicketDetail', gotTicketDetailRouter);
app.use('/login', loginRouter);
app.use('/loginFailed', loginFailedRouter);
app.use('/loginSuccess', loginSuccessRouter);
app.use('/member', memberRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
