const req = require("express/lib/request");
const mongoose = require("mongoose");

const Schema = mongoose.Schema;
const ConsultationSchema = new Schema(
    {
        hospital:{
            type: String,
            required: true,
        },
        direction:{
            type: String,
        },
        medical:{
            type: String,
        },
        date:{
            type: String,
            required: true,
        },
        hour:{
            type: String,
            required: true,
        },
        activation:{
            type: Boolean,
            required: true,
        },
        username:{
            type: String,
            required: true,
        }
    },
    {timeStamps: true}
);

const ConsultationModel = mongoose.model("consultation",ConsultationSchema);

module.exports = ConsultationModel;