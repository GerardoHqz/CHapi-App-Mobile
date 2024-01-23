const mongoose = require("mongoose");

const Schema = mongoose.Schema;
const HistorySchema = new Schema(
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
        username:{
            type: String,
            required: true,
        }
    },
    {timeStamps: true}
);

const HistoryModel = mongoose.model("history",HistorySchema);

module.exports = HistoryModel;
