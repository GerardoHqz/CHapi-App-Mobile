var{
    getAllEmergency,
    getOneEmergency,
    getAllEmergencyAdmin,
    createEmergency,
    updateEmergency,
    deleteEmergency,
} = require('../controllers/emergency');
var express = require('express');
var router = express.Router();
var authAdmin = require("../auth/authAdmin");

router.get('/:username', getAllEmergency);
router.get('/:_id', getOneEmergency);
router.get('/:username', authAdmin.isAdmin, getAllEmergencyAdmin);
router.post('/', createEmergency);
router.put('/:_id', updateEmergency);
router.delete('/:_id', deleteEmergency);

module.exports = router;
