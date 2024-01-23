var{
    getAllReminds,
    getOneRemind,
    getAllRemindsByUsername,
    createRemind,
    updateRemind,
    deleteRemind,
    toogleRemind,
    getRemindsByDate
} = require('../controllers/remind');
var express = require('express');
var router = express.Router();
var authAdmin = require("../auth/authAdmin");

router.get('/', getAllReminds);
router.get('/:_id', getOneRemind);
router.get('/user/:username', authAdmin.isAdmin ,getAllRemindsByUsername);
router.post('/', createRemind);
router.put('/:_id', updateRemind);
router.delete('/:_id', deleteRemind);
router.patch('/:_id/toogle', toogleRemind);
router.post('/today/', getRemindsByDate);

module.exports = router;