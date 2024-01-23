var express = require("express");

exports.isAdmin = (req, res, next) => {
    if (req.user.role === "admin") {
        next();
    } else {
        return res.status(401).send({
            message: "You are not authorized to access this resource",
        });
    }
}
