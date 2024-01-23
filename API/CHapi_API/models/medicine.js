const mongoose = require('mongoose');

const Schema = mongoose.Schema;
const MedicineSchema = new Schema(
    {
        name_medicine: {
            type: String,
            unique: true
        }
    },
    {timeStamps: true}
);

const MedicineModel = mongoose.model("medicine", MedicineSchema);

module.exports = MedicineModel;