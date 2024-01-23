const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const EmergencySchema = Schema(
    {
        name:{
            type: String, 
            required: true,
        },
        email: {
            type: String,
            required: true,
        },
        phone: {
            type: String,
            required: true,
        },
        username:{
            type: String,
            required: true,
        }
    },
    {timeStamps: true}
);

const EmergencyModel = mongoose.model("emergency",EmergencySchema);

module.exports = EmergencyModel;