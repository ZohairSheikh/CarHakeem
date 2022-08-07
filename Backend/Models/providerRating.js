'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var ProviderRatingSchema = new Schema({

    userId: {
        type: Schema.Types.ObjectId,
        

    },
    providerId: {
        type: Schema.Types.ObjectId,
      
    },
    rating: {
        type: Number ,
     
    }
}, { timestamps: true });

module.exports = mongoose.model('ProviderRating', ProviderRatingSchema);



