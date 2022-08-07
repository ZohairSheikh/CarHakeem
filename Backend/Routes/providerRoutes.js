




module.exports.providerRoutes = function (app) {

    var providerController = require("../Controllers/providerController");



    

    app.get('/providers', providerController.getProviders);
    app.get('/providerTypes', providerController.getProviderTypes);
    app.get('/providers2', providerController.getProviders2);

    app.get('/customerSync', providerController.getProviders);


    app.post('/myServices', providerController.myServices);


    app.post('/addProvider', providerController.addProvider);


};