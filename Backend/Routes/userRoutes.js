




module.exports.userRoutes = function (app) {

    var userController = require("../Controllers/userController");


    app.post('/signin', userController.signin);
    app.post('/signup', userController.signup);
    app.post('/forgetPassword', userController.forgetPassword);
    app.post('/changePassword', userController.changePassword);


    app.post('/g', userController.changePassword);




};