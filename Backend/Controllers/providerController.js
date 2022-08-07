

var providerObj = require('../Models/index').allModels.ProviderModel;
var providerRatingObj = require('../Models/index').allModels.ProviderRatingModel;
var mongoose = require('mongoose');

 const ContentBasedRecommender = require('content-based-recommender')
        const recommender = new ContentBasedRecommender({
            minScore: 0.1,
            maxSimilarDocuments: 100
        });


exports.getProviderTypes = function (req, res) {
    providerObj.find().distinct('category', function (error, ids) {
        res.status(200).send({ "response": ids, "message": "All Providers Type", "error": false });

    });
}

exports.getProviders = async (req, res) => {


    const { page = 1, limit = 10 } = req.query;




    try {
        // execute query with page and limit values
        const providers = await providerObj.find()
            .limit(limit * 1)
            .skip((page - 1) * limit)
            .exec();

        // get total documents in the Posts collection 
        const count = await providerObj.countDocuments();

        // return response with posts, total pages, and current page
        res.json({
            providers,
            totalPages: Math.ceil(count / limit),
            currentPage: page
        });
    } catch (err) {
        console.error(err.message);
    }






}


exports.addProvider = async (req, res) => {



    console.log(req.body);


    var new_user = new providerObj({
        providerName: req.body.providerName,
        address: req.body.address,
        category: req.body.category,
        contactNo: req.body.contactNo,
        userId: req.body.userId,
        district: req.body.district,
        area: req.body.area,
        lat: req.body.lat,
        lon: req.body.lon,
        rate: req.body.rate,
        isAuto:false
    });
  

    new_user.save(function (err, task) {
        if (err) {
            res.status(400).send({ 'responce': "", 'message': "some error occured" });
        }
        else {
            res.status(200).send({ "response": "", "message": "Provider Added Successfully", "error": false });
        }
    });
 



}


exports.getProviders2 = async (req, res) => {


    const { page = 1, limit = 10 } = req.query;




    try {

        var a = await providerRatingObj.aggregate([
            { $group: { _id: '$providerId', count: { $sum: 1 }, avg: { $avg: '$rating' } } },
            { $sort: { avg: -1 } },
            { $project: { _id: 1, count: 1, avg: { $round: ['$avg', 1] } } },
           
            
            {
                $lookup: {
                    from: "providers",
                    localField: "_id",
                    foreignField: "_id",
                    as: "providers"
                }
            },

        ]);



           
        // // prepare documents data
        const documents = [];
    
        console.log(a[0].providers[0]);
     


     for (const item of a) {  

        
    
      item.providers[0].distance = calcCrow(item.providers[0].lat,item.providers[0].lon,req.query.lat,req.query.lon);



      }

    
    a.sort((a1,b1)=> (  a1.providers[0].distance  - b1.providers[0].distance ||  b1.avg - a1.avg ));


        res.json({
            providers: paginate(a,limit,page),
             totalPages: Math.ceil(a.length / limit),

             currentPage:page,
        });
    } catch (err) {
        console.error(err.message);
    }




}

function paginate(array, page_size, page_number) {
    // human-readable page numbers usually start with 1, so we reduce 1 in the first argument
    return array.slice((page_number - 1) * page_size, page_number * page_size);
  }


function calcCrow(lat1, lon1, lat2, lon2) 
{
  var R = 6371; // km
  var dLat = toRad(lat2-lat1);
  var dLon = toRad(lon2-lon1);
  var lat1 = toRad(lat1);
  var lat2 = toRad(lat2);

  var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
  var d = R * c;
  return d;
}

// Converts numeric degrees to radians
function toRad(Value) 
{
    return Value * Math.PI / 180;
}




exports.myServices = async (req, res) => {


    const { page = 1, limit = 10 } = req.body;





    try {
        // execute query with page and limit values
        const providers = await providerObj.find({ "userId": mongoose.Types.ObjectId(req.body.userId) })
            .limit(limit * 1)
            .skip((page - 1) * limit)
            .exec();

        // get total documents in the Posts collection 
        const count = await providerObj.countDocuments();

        // return response with posts, total pages, and current page
        res.json({
            providers,
            totalPages: Math.ceil(count / limit),
            currentPage: page
        });
    } catch (err) {
        console.error(err.message);
    }





}