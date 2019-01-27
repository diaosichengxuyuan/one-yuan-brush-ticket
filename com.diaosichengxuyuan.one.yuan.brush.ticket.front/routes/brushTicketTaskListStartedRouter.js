var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.render('brushTicketTaskListStarted.htm', res);
});

module.exports = router;
