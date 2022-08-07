var userObj = require('../Models/index').allModels.UserModel;

var utils = require('../Helpers/Utils');

exports.signup = function (req, res) {

    if (req.body.mobileNo && req.body.name && req.body.password) {

        userObj.find({ mobileNo: req.body.mobileNo }, function (err, task) {

            if (task[0]) {
                if (task[0].isActive) {
                    res.status(400).send({ "response": "", "message": "User Already Registered", "error": true });
                }
                else {
                    res.status(400).send({ "response": "", "message": "Please Verify With OTP", "error": true });
                }

            }
            else {
                var password = req.body.password;
                if( req.body.userType !== "admin"){
                    password = utils.generatePasswordHash(req.body.password);
                }
               
                var new_user = new userObj({
                    mobileNo: req.body.mobileNo,
                    emailAddress: req.body.emailAddress,
                    name: req.body.name,
                    password: password,
                    userType: req.body.userType,
                    isActive: true
                });
                console.log(new_user);

                new_user.save(function (err, task) {
                    if (err) {
                        res.status(400).send({ 'responce': "", 'message': "some error occured" });
                    }
                    else {
                        require('../Helpers/gendata').updateProviderBinding();
                   
                        res.status(200).send({ "response": "", "message": "User Registered Successfully", "error": true });
                    }
                });
            }
        })
    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }
}


exports.signin = function (req, res) {


    if (req.body.mobileNo && req.body.password) {

        userObj.find({ mobileNo: req.body.mobileNo }, function (err, task) {

            if (task[0]) {

                if (utils.comparePasswordHash(req.body.password, task[0].password)) {

                    var token = utils.generateAccessToken(task[0]);
                    userObj.updateOne({ mobileNo: task[0].mobileNo }, { accessToken: token }, function (updateerr, updateres) {
                        if (updateerr) {
                            res.status(400).send({ "response": "", "message": "Some error occured", "error": true });

                        } else {

                            var responseData = {
                                _id: task[0]._id,
                                userName: task[0].name,
                                emailAddress: task[0].emailAddress,
                                mobileNo: task[0].mobileNo,
                                accessToken: token,
                                userType: task[0].userType,
                            };


                            res.status(200).send({ "response": responseData, "message": "Login successfull", "error": true });

                        }

                    });



                }
                else {
                    res.status(400).send({ "response": "", "message": "Incorrect Password", "error": true });

                }

            }
            else {
                res.status(400).send({ "response": "", "message": "User Not Found", "error": true });

            }
        });
    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }


}

exports.forgetPassword = function (req, res) {
    if (req.body.mobileNo) {

        userObj.find({ mobileNo: req.body.mobileNo }, function (err, task) {

            if (task[0]) {
                var responseData = {
                    _id: task[0]._id,
                    mobileNo: task[0].mobileNo,
                }; res.status(200).send({ "response": responseData, "message": "Forget password", "error": true });

            }
            else {
                res.status(400).send({ "response": "", "message": "User Not Found", "error": true });

            }
        })
    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }

}


exports.changePassword = function (req, res) {
    if (req.body.mobileNo && req.body.id && req.body.password) {

        userObj.find({ mobileNo: req.body.mobileNo, _id: req.body.id }, function (err, task) {

            if (task[0]) {

                var password = utils.generatePasswordHash(req.body.password);

                userObj.updateOne({ mobileNo: task[0].mobileNo }, { password: password }, function (updateerr, updateres) {
                    if (updateerr) {
                        res.status(400).send({ "response": "", "message": "Some error occured", "error": true });

                    } else {



                        res.status(200).send({ "response": updateres, "message": "Password Change Successfully", "error": true });

                    }

                });

            }
            else {
                res.status(400).send({ "response": "", "message": "User Not Found", "error": true });

            }
        })
    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }

}



exports.genData12 = function (req, res) {
    require('../Helpers/gendata').genData1();

}


