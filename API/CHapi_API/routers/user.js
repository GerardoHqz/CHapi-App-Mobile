var {
    getAllUsers,
    getUser,
    createUser,
    updateUser,
    deleteUser,
  } = require("../controllers/user");
  var express = require("express");
  var router = express.Router();
  var authAdmin = require("../auth/authAdmin");

  router.get("/",authAdmin.isAdmin, getAllUsers);
  router.get("/:username", authAdmin.isAdmin, getUser);
  router.post("/", createUser);
  router.put("/:username", updateUser);
  router.delete("/:username", authAdmin.isAdmin, deleteUser);
  
  module.exports = router;