var { createUser } = require("../controllers/user");
var express = require("express");
var jwt = require("jsonwebtoken");
var UserModel = require("../models/user");
var CHapiEmail = require("../config/mailer");

var router = express.Router();

router.post("/register", createUser);
router.post("/login", async (req, res, next) => {
    try {
        let { username, password } = req.body; //TODO: Add email 
        let user = await UserModel.findOne({ username });
        if (!user)
        return res.status(400).send({
            message: "User not found",
        });
        let validPassword = await user.isValidPassword(password);
        if (!validPassword)
        return res.status(400).send({
            message: "Wrong password",
        });
        let body = { _id: user._id, email: user.email };
        let token = jwt.sign(
        { user: body },
        process.env.JWT_SECRET || "TOP_SECRET"
        );
        return res.json({ token });
    } catch (err) {
        next(err);
    }
});

router.put("/forgot-password", async (req, res, next) => {
    try {
        let digitPass = Math.floor(Math.random() * (999999 - 100000 + 1)) + 100000;
        let { email } = req.body;
        let user = await UserModel.findOne({ email });
        if (!user)
        return res.status(400).send({
            message: "User not found",
        });
        

        //guardando el codigo en la base de datos
        user.code = digitPass;
        user.save();

        let correo = CHapiEmail.transporter.sendMail({
            from: '"Forgot Password CHapi" <noreply.chapi@gmail.com>', // sender address
            to: user.email, // list of receivers
            subject: "Reset Password", // Subject line
            text: "Hello, " + user.username + "!\n\n" +
            "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n" +
            "your code is " + digitPass + "\n\n"
        });
        return res.status(200).send({
            message: "Code sent",
        });

    } catch (err) {
        next(err);
    }
});

//actualizar contraseña 
router.put("/update-password/:digitPass", async (req, res, next) => {
    try {
        let { digitPass } = req.params;
        let { password } = req.body;
        let user = await UserModel.findOne({ code: digitPass });
        if (!user)
        return res.status(400).send({
            message: "User not found",
        });
        user.password = password;
        let updatedUser = await user.save();
        if (user == updatedUser) {
            return res.send({
                message: "User is updated",
                user: { username: user.username, email: user.email, phone: user.phone, role: user.role, code: user.code },
            });
        }
    } catch (err) {
        next(err);
    }
});

//Verificar si el codigo es correcto
router.post("/verify-code", async (req, res, next) => {
    try {
        let { digitPass } = req.body;
        let user = await UserModel.findOne({ code: digitPass });
        if (!user)
        return res.status(400).send({
            message: "User not found",
        });
        return res.status(200).send({
            message: "Code is correct",
        });
    } catch (err) {
        next(err);
    }
});

//Devolver usuario y role con token
router.get("/auth/whoami", async (req, res, next) => {
    try {
        let token = req.headers.authorization;
        let payload = jwt_decode(token);
        let user = await UserModel.findOne({ _id: payload.user._id });
        if (!user)
        return res.status(400).send({
            message: "User not found",
        });
        return res.status(200).send({
            message: "User found",
            user: { username: user.username, email: user.email, phone: user.phone, role: user.role, code: user.code },
        });
    } catch (err) {
        next(err);
    }
})


module.exports = router; 