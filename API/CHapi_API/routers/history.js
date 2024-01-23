var{
    getAllHistory,
    createHistory
} = require('../controllers/history');
var express = require('express');
var router = express.Router();

router.get('/:username', getAllHistory);
router.post('/', createHistory);

module.exports = router;