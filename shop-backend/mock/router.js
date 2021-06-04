const express = require("express");
const router = express.Router();

router.post("/login", function (req, res) {
    let username = req.body.username;
    let password = req.body.password;
    if (username && password) {
        res.send({
            id: "1001",
            username: username,
            token:"DFGAEGRSD234TERTEHT54TEET34ER345TE2354T253TG523R4T"
        })
    } else {
        res.send({
            msg: "用户名密码错误"
        })
    }

})

module.exports = router;