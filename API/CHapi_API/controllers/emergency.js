const EmergencyModel = require('../models/emergency');

exports.getAllEmergency = async (req, res, next) => {
    try{
        const username  = req.params.username;
        const emergencies = await EmergencyModel.find({username: req.user.username});
        res.status(200).json({
            message: 'Emergencies fetched successfully',
            emergencies: emergencies
        });
    }catch(err){
        next(err);
    }
}

exports.getOneEmergency = async (req, res, next) => {
    try{
        let id = req.params._id;
        let emergency = await EmergencyModel.findOne({id});
        if(!emergency){
            return res.status(404).send({
                message: "contact emergency not found",
            });
        }
        res.send({ emergency });
    } catch(err){
        next(err);
    }
};

exports.getAllEmergencyAdmin = async (req, res, next) => {
    try{
        let username = req.params.username;
        let emergencies = await EmergencyModel.find({username});
        res.send({
            count: emergencies.length,
            emergencies,
        });
    }
    catch(err) {
        next(err);
    }
};

exports.createEmergency = async(req, res, next) => {
    try{
        let {name, email, phone, username} = req.body;
        let newEmergency = await EmergencyModel.create({name, email, phone, username})
        res.send({newEmergency})
    }catch(err){
        next(err);
    }
};

exports.updateEmergency = async(req, res, next) => {
    try{
        let idToUpdate = req.params._id;
        let {name, email, phone} = req.body;
        let emergency = await EmergencyModel.findOne({_id: idToUpdate});
        if(!emergency) return res.status(400).send({
            message: "contact emergency to update not found"
        })
        
        emergency.name = name;
        emergency.email = email;
        emergency.phone = phone;

        let updateEmergency = await emergency.save();

        if(emergency == updateEmergency){
            return res.send({
                message: "contact emergency is updated",
                emergency: updateEmergency,
            });
        }
        res.send({
            message: "cannot update the contact emergency",
        });
    } catch(err){
        next(err)
    }
};

exports.deleteEmergency = async (req, res, next) => {
    try{
        let  id = req.params._id;
        let {deletedCount} = await EmergencyModel.deleteOne({id});
        if(deletedCount == 1){
            return res.send({
                message: "successfully deleted",
            });
        }
        return res.status(400).send({
            message: "cannot delete the contact emergency, maybe id delete before",
        }); 
    } catch(err){
        next(err);
    }
};
