const HistoryModel = require("../models/history");

//obtener el historial de un usuario
exports.getAllHistory = async(req, res, next) => {
    try{
        let user = req.params.username;
        let history = await HistoryModel.find({username: user});
        res.status(200).json({
            message: 'History fetched successfully',
            history: history
        });
    }
    catch(err) {
        res.status(500).json({
            message: err.message
        });
    }
};

//crear historial
exports.createHistory = async(req, res, next)=>{
    try{
        let {name, many, unit, type, username} = req.body;
        let newHistory = await HistoryModel.create({name, many, unit, type, username});
        res.send({newHistory})
    }catch (err){
        next(err);
    }
}
