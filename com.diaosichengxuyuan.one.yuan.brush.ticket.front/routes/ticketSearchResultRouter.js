var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.render('ticketSearchResult.htm', res);
});

module.exports = router;
