var{
    getAllConsultations,
    getOneConsultation,
    getAllConsultationsAdmin,
    createConsultation,
    updateConsultation,
    deleteConsultation,
    toogleConsultation,
    getConsultationsByDate
} = require('../controllers/consultation');  
var express = require('express');
var router = express.Router();
var authAdmin = require("../auth/authAdmin");


router.get('/:username', getAllConsultations);
router.get('/:_id', getOneConsultation);
router.get('/user/:username', authAdmin.isAdmin ,getAllConsultationsAdmin);
router.post('/', createConsultation);
router.put('/:_id', updateConsultation);
router.delete('/:_id', deleteConsultation);
router.patch('/:_id/toogle', toogleConsultation);
router.post('/today/', getConsultationsByDate);

module.exports = router;