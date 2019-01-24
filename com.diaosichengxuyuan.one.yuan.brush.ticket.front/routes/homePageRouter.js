var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.render('homePage.htm', res);
});

module.exports = router;
