
var passwordHash = require('bcryptjs');

var jwt = require('jsonwebtoken');

exports.randomNumber = function () {
  return Math.floor(100000 + Math.random() * 900000);
}

exports.generatePasswordHash = function (password) {

  var salt = passwordHash.genSaltSync(10);
  return passwordHash.hashSync(password, salt);


};


exports.createRandomString = function (length) {
  var result = '';
  var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
  for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
  return result;
};

exports.getErrorMessage = function (code) {
  if (code == 11000) {
    return "User Already Registered"
  }
};



exports.comparePasswordHash = function (reqPassword, savedPassword) {

  return passwordHash.compareSync(reqPassword, savedPassword);

};


exports.generateAccessToken = function (data) {
  return token = jwt.sign({ data }, "carhakeem", {
    //  expiresIn: 86400 // expires in 24 hours
  });
}


exports.between = function (min, max) {

  const result = Math.random() * (max - min) + min


  return Math.floor(result);

}