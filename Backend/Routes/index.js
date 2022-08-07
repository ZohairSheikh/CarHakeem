


module.exports = function (apiRoute) {

    require("./userRoutes").userRoutes(apiRoute);
   
    require("./providerRoutes").providerRoutes(apiRoute);
    require("./serviceRoute").serviceRoutes(apiRoute);
   
}