
const fs = require('fs');
const path = require('path');
const csv = require('fast-csv');
var request = require('request');
var axios = require('axios');


var providerObj = require('../Models/index').allModels.ProviderModel;
var providerRatingObj = require('../Models/index').allModels.ProviderRatingModel;
var userObj = require('../Models/index').allModels.UserModel;
var utils = require('./Utils');

exports.genData1 = async function (count) {






    userObj.find({ userType: 'provider' }, function (userErr, userList) {


        var rn = utils.between(0, userList.length);


        providerObj.deleteMany({ isAuto: true }, function (err, removed) {

            providerRatingObj.deleteMany({}, function (err1, removed1) {

                

                fs.createReadStream(path.resolve(__dirname, '../Assets', 'data.csv'))
                    .pipe(csv.parse({ headers: true }))
                    .on('error', error => console.error(error))
                    .on('data', row => {


                        //    console.log(row)
                        let jsonData = require('../Assets/latlondata.json');


                        for (var i = 0; i < jsonData.data.length; i++) {

                            if (row['Title'] && row['Address'] && row['Category'] && row['Contact No'] && row['District'] && row['Area']) {

                                if (jsonData.data[i].district === row['District'] && jsonData.data[i].area === row['Area']) {
                                    var newData = new providerObj({
                                        providerName: row['Title'],
                                        address: row['Address'],
                                        category: row['Category'].trim(),
                                        contactNo: row['Contact No'],
                                        district: row['District'],
                                        area: row['Area'],
                                        userId: userList[rn]._id,
                                        lat: jsonData.data[i].lat,
                                        lon: jsonData.data[i].lon,
                                        rate: utils.between(200, 2001),
                                        isAuto: true
                                    });

                                    newData.save(function (err, task) {




                                        if (err) {
                                            console.log(" ************ err ");
                                            console.log(err);
                                            console.log(" ************ err end ");
                                        }
                                        else {

                                            for (let i = 0; i < 5; i++) {
                                                var newRatingData = new providerRatingObj({
                                                    providerId: task._id,
                                                    rating: utils.between(1, 6)
                                                });
                                                newRatingData.save();
                                            }


                                        }

                                    });

                                    break;
                                }
                            }

                        }
                    })
                    .on('end', rowCount => {

                        providerObj.find({}, function (err, taske) {

                            const max = taske.length;


                            for (var i = 0; i < count; i++) {

                                var newData = new providerObj({
                                    providerName: taske[getRandomInt(max)].providerName,
                                    address: taske[getRandomInt(max)].address,
                                    category: taske[getRandomInt(max)].category.trim(),//replace(/^\s+|\s+$/gm,''),
                                    contactNo: taske[getRandomInt(max)].contactNo,
                                    district: taske[getRandomInt(max)].district,
                                    area: taske[getRandomInt(max)].area,
                                    userId: userList[rn]._id,
                                    lat: taske[getRandomInt(max)].lat,
                                    lon: taske[getRandomInt(max)].lon,
                                    rate: utils.between(200, 2001),
                                    isAuto: true
                                });

                                newData.save(function (err1, task1) {

                                    if (err1) {
                                        console.log(" ************ err ");
                                        console.log(err1);
                                        console.log(" ************ err end ");
                                    }
                                    else {
                                        //  console.log(" saved .... random " + i);
                                        for (let i = 0; i < 5; i++) {
                                            var newRatingData = new providerRatingObj({
                                                providerId: task1._id,
                                                rating: utils.between(1, 6)
                                            });
                                            newRatingData.save();
                                        }
                                    }

                                });

                            }

                        
                        })

                        console.log(`Parsed ${rowCount} rows`)
                    });

            });
        });
        
    });



    var config = {
        /* Your settings here like Accept / Headers etc. */
    }

    
}


function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}



exports.updateProviderBinding = async function () {






    userObj.find({ userType: 'provider' }, function (userErr, userList) {


        providerObj.find({ isAuto: true }, function (providerErr, providerList) {

            if (providerList) {
              
              
                var count = 0;
                providerList.forEach(element => {
                    providerObj.findOneAndUpdate({_id : element._id}, {userId: userList[count]._id}, function(
                        err,
                        result
                      ) {
                        if (err) {
                          //  console.log(err);                        
                        } else {
                        //console.log(result);
                        }
                      });

                    count++;

                    if(count== userList.length)
                    {
                        count=0;
                    }
                });

            }

        })


    });







}