




module.exports.serviceRoutes = function (app) {

    var serviceController = require("../Controllers/serviceController");


    app.post('/makeAppointment', serviceController.makeAppointment);
  
    app.post('/myAppointments', serviceController.myAppointment);


    app.post('/appointmentStatus', serviceController.appointmentStatus);




};