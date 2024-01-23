const RemindModel = require("../models/remind");

//Recordatorios de un usuario
exports.getAllReminds = async(req, res, next) => {
    try {
        const username = req.params.username;
        const reminds = await RemindModel.find({username: req.user.username});
        res.status(200).json({
            message: 'Consultations fetched successfully',
            reminds: reminds
        });
    }
    catch (err) {
        res.status(500).json({
            message: err.message
        });
    }
};

//Get one   
exports.getOneRemind = async (req, res, next) => {
    try{
        let id = req.params._id;
        let remind = await RemindModel.findOne({id});
        if(!remind){
            return res.status(404).send({
                message: "remind not found",
            });
        }
        res.send({ remind });
    } catch(err){
        next(err);
    }
};

//Get all reminds by username
exports.getAllRemindsByUsername = async(req, res, next) => {
    try{
        let username = req.params.username;
        let reminds = await RemindModel.find({username});
        res.send({
            count: reminds.length,
            reminds,
        });
    }
    catch(err) {
        next(err);
    }
};

//Create Remind
exports.createRemind = async(req, res, next)=>{
    try{
        let {name, many, unit, type, date, hour, frecuently,activation, username} = req.body;
        let newRemind = await RemindModel.create({name, many, unit, type, date, hour, frecuently, activation, username})
        res.send({newRemind})
    }catch (err){
        next(err);
    }
};

//update Remind
exports.updateRemind = async(req, res, next) => {
    try{
        let idToUpdate = req.params._id;
        let {date, hour} = req.body;
        let remind = await RemindModel.findOne({_id: idToUpdate});
        if(!remind) return res.status(400).send({
            message: "remind to update not found"
        })

        remind.date = date;
        remind.hour = hour;
        let updateRemind = await remind.save();

        if(remind == updateRemind){
            return res.send({
                message: "remind is updated",
                remind: updateRemind,
            });
        }
        res.send({
            message: "cannot update the remind",
        });
    } catch(err){
        next(err)
    }
};

//Delete remind
exports.deleteRemind = async (req, res, next) => {
    try{
        let  id = req.params._id;
        let {deletedCount} = await RemindModel.deleteOne({id});
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
exports.toogleRemind = async(req, res, next) => {
    try{
        const idToggleRemind = req.params._id;
        let {id} = req.body;
        let remind = await RemindModel.findOne({_id: idToggleRemind});
        if(!remind) return res.status(400).send({
            message: "remind to update not found"
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

//reminds by date
exports.getRemindsByDate = async(req, res, next) => {
    try{
        const {date, username} = req.body;
        let reminds = await RemindModel.find({date, username});
        res.send({
            count: reminds.length,
            reminds,
        });
    }
    catch(err) {
        next(err);
    }
}