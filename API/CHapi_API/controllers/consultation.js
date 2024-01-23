const ConsultationModel = require('../models/consultation');

//Recordatorios de un usuario
exports.getAllConsultations = async(req, res, next) => {
    try {
        const username = req.params.username;
        const consultations = await ConsultationModel.find({username: req.user.username});
        res.status(200).json({
            message: 'Consultations fetched successfully',
            consultations: consultations
        });
    }
    catch (err) {
        res.status(500).json({
            message: err.message
        });
    }
};

//Get one
exports.getOneConsultation = async (req, res, next) => {
    try{
        let id = req.params._id;
        let consultation = await ConsultationModel.findOne({id});
        if(!consultation){
            return res.status(404).send({
                message: "remin consultation not found",
            });
        }
        res.send({ consultation });
    } catch(err){
        next(err);
    }
};

//Get all consultations by username
exports.getAllConsultationsAdmin = async(req, res, next) => {
    try{
        let username = req.params.username;
        let consultations = await ConsultationModel.find({username});
        res.send({
            count: consultations.length,
            consultations,
        });
    }
    catch(err) {
        next(err);
    }
};

//Create Consultation
exports.createConsultation = async(req, res, next)=>{
    try{
        let {hospital, direction, medical, hour, date, activation, username} = req.body;
        let newConsultation = await ConsultationModel.create({hospital, direction, medical, hour, date, activation, username})
        res.send({newConsultation})
    }catch (err){
        next(err);
    }
};

//update Consultation
exports.updateConsultation = async(req, res, next) => {
    try{
        let idToUpdate = req.params._id;
        let {date, hour} = req.body;
        let remind = await ConsultationModel.findOne({_id: idToUpdate});
        if(!remind) return res.status(400).send({
            message: "remind consultation to update not found"
        })

        remind.date = date;
        remind.hour = hour;
        let updateRemind = await remind.save();

        if(remind == updateRemind){
            return res.send({
                message: "remind consultation is updated",
                remind: updateRemind,
            });
        }
        res.send({
            message: "cannot update the remind consultation",
        });
    } catch(err){
        next(err)
    }
};

//Delete consultation
exports.deleteConsultation = async (req, res, next) => {
    try{
        let  id = req.params._id;
        let {deletedCount} = await ConsultationModel.deleteOne({id});
        if(deletedCount == 1){
            return res.send({
                message: "successfully deleted",
            });
        }
        return res.status(400).send({
            message: "cannot delete the remind, maybe id delete before",
        }); 
    } catch(err){
        next(err);
    }
};

//Disable or enable remind
exports.toogleConsultation = async(req, res, next) => {
    try{
        const idToggleRemind = req.params._id;
        let {id} = req.body;
        let remind = await ConsultationModel.findOne({_id: idToggleRemind});
        if(!remind) return res.status(400).send({
            message: "remind consultation to update not found"
        })
        
        if(remind.activation == true){
            remind.activation = false;
        }
        else{
            remind.activation = true;
        }
        let activationRemind = await remind.save();

        if(remind == activationRemind){
            return res.send({
                message: "process completed",
                remind: activationRemind,
            });
        }
        res.send({
            message: "process not completed ",
        });
    }catch(err){
        next(err)
    }
}

//Get consultations by date
exports.getConsultationsByDate = async(req, res, next) => {
    try{
        let {date, username} = req.body;
        let consultations = await ConsultationModel.find({username: username, date: date});
        res.send({
            count: consultations.length,
            consultations,
        });
    }
    catch(err) {
        next(err);
    }
}
