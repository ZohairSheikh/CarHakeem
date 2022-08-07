
var serviceObj = require('../Models/index').allModels.ServiceModel;
var mongoose = require('mongoose');


exports.makeAppointment = function (req, res) {
    if (req.body.userId && req.body.providerId && req.body.status) {


        var new_service = new serviceObj({
            userId: req.body.userId,
            providerId: req.body.providerId,
            providerUserId: req.body.providerUserId,
            status: req.body.status,
            adminStatus: "pending",
            appointmentDate:req.body.appointmentDate
        });
        // console.log(new_user);

        new_service.save(function (err, task) {
            if (err) {
                res.status(400).send({ 'responce': "", 'message': err });
            }
            else {
                res.status(200).send({ "response": "", "message": "Appointment Booked Successfully", "error": true });
            }
        });

    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }



}





exports.myAppointment = async (req, res) => {

    if (req.body.userId) {
        try {

            if (req.body.userType == "customer") {
                var a = await serviceObj.aggregate([
                    { "$match": { "userId": mongoose.Types.ObjectId(req.body.userId) } },
                    {
                        $lookup: {
                            from: "providers",
                            localField: "providerId",
                            foreignField: "_id",
                            as: "providers"
                        }
                    },

                ])



                res.status(200).send({ "response": a, "message": "Appointments", "error": false });
            } else {
                var a = await serviceObj.aggregate([
                    { "$match": { "providerUserId": mongoose.Types.ObjectId(req.body.userId) ,adminStatus:"approved" } },
                    {
                        $lookup: {
                            from: "providers",
                            localField: "providerId",
                            foreignField: "_id",
                            as: "providers"
                        }
                    },

                ])



                res.status(200).send({ "response": a, "message": "Appointments", "error": false });

            }


        }
        catch (err) {
            console.error(err.message);
        }
    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }


}


exports.appointmentStatus = async (req, res) => {

    if (req.body.appId && req.body.status) {
        serviceObj.updateOne({ _id: mongoose.Types.ObjectId(req.body.appId) }, { status: req.body.status }, function (updateerr, updateres) {

            if (updateerr) {
                res.status(400).send({ 'response': "", 'message': updateerr });
            }
            else {
                res.status(200).send({ 'response': updateres, 'message': "updated successfully" });
            }



        });
    }
    else {
        res.status(400).send({ 'responce': "", 'message': "Required Param missing" });
    }


}