const MedicineModel = require("../models/medicine");

exports.getAllMedicine = async(req, res, next) => {
    try{
        let medicine = await MedicineModel.find({});
        res.send({
            count: medicine.length,
            medicine,
        });
    } catch(err){
        next(err);
    }
};

exports.getOneMedicine = async(req, res, next) => {
    try{
        let name = req.params._id;
        let medicine = await MedicineModel.findOne({name});
        if(!medicine){
            return res.status(404).send({
                message: "medicine not found",
            });
        }
        res.send({ medicine });
    }catch(err){
        next(err);
    }
}

exports.createMedicine = async(req, res, next)=>{
    try{
        let {name_medicine} = req.body;
        let newMedicine = await MedicineModel.create({name_medicine})
        res.send({newMedicine})
    }catch (err){
        next(err);
    }
};

exports.updateMedicine = async(req, res, next) => {
    try{
        let nameToUpdate = req.params.name_medicine;
        let {name_medicine} = req.body;
        let medicine = await MedicineModel.findOne({name_medicine: nameToUpdate});
        if(!medicine) return res.status(400).send({
            message: "medicine to update not found"
        })

        medicine.name_medicine = name_medicine;

        let updateMedicine = await medicine.save();

        if(medicine == updateMedicine){
            return res.send({
                message: "medicine is updated",
                remind: updateMedicine,
            });
        }
        res.send({
            message: "cannot update the medicine",
        });
    } catch(err){
        next(err)
    }
};

exports.deleteMedicine = async (req, res, next) => {
    try{
        let  name = req.params.name_medicine;
        let {deletedCount} = await MedicineModel.deleteOne({name});
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


