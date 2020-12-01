var express = require("express");
var app = express();

app.use(express.static("layuibokemoban")).listen(3001);