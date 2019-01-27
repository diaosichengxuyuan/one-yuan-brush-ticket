var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.render('brushTicketTaskListStopped.htm', res);
});

module.exports = router;
