const express = require("express");
const app = express();
const router = require("./router");
const bodyParser = require("body-parser");
var debug = require('debug')('my-application');

// post请求获取参数的方案
app.use(bodyParser.urlencoded({
    extended: true
}));

app.use("/api",router);

app.listen(3000,function(){
    debug('Express server listening on port ' + 3000);
})