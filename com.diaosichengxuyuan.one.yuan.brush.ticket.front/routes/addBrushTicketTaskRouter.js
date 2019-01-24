var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.render('addBrushTicketTask.htm', res);
});

module.exports = router;
