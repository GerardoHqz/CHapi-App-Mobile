var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
var helmet = require("helmet");
var passport = require("passport");
require("./auth/auth");
require("./database/config");


var userRouter = require("./routers/user");
var remindRouter = require("./routers/remind");
var consultationRouter = require("./routers/consultation");
var emergencyRouter = require("./routers/emergency");
var medicineRouter = require("./routers/medicine");
var historyRouter = require("./routers/history");
var authRouter = require("./routers/auth");

var app = express();

app.use(logger("dev"));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));


app.use(authRouter);
app.use(passport.authenticate("jwt", { session: false }))
app.use("/users", userRouter);
app.use("/reminds", remindRouter);
app.use("/consultations", consultationRouter);
app.use("/emergencies", emergencyRouter);
app.use("/medicines", medicineRouter, );
app.use("/history", historyRouter);

module.exports = app;