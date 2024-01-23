var{
    getAllMedicine,
    getOneMedicine,
    createMedicine,
    updateMedicine,
    deleteMedicine,
} = require('../controllers/medicine');
var express = require('express');
var router = express.Router();
var authAdmin = require("../auth/authAdmin");


router.get('/', getAllMedicine);
router.get('/:name_medicine', getOneMedicine);
router.post('/', createMedicine);
router.put('/:name_medicine' , authAdmin.isAdmin, updateMedicine);
router.delete('/:name_medicine', authAdmin.isAdmin, deleteMedicine);

module.exports = router;