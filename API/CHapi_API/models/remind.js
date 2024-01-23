const mongoose = require("mongoose");

const Schema = mongoose.Schema;
const RemindSchema = new Schema(
    {
        name:{
            type: String,
            required: true,
        },
        many:{
            type: Number,
            required: true,
        },
        unit:{
            type: String,
            required: true,
        },
        type:{
            type: String,
            required: true,
        },
        date:{
            type: String,
            required: true,
        },
        hour:{
            type: String,
            required: true,
        },
        frecuently:{
            type: String,
            required: true,
        },
        activation:{
            type: Boolean,
            required: true,
            default: true,
        },
        username:{
            type: String,
            required: true,
        }
    },
    {timeStamps: true}
);

const RemindModel = mongoose.model("remind",RemindSchema);

module.exports = RemindModel;