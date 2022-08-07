'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var ProviderSchema = new Schema({

    providerName: {
        type: String,
    },
    address: {
        type: String,
    },
    category: {
        type: String,
    },
    userId: {
        type: Schema.Types.ObjectId,
    }, contactNo: {
        type: String,
    },
    district: {
        type: String,
    },
    area: {
        type: String,
    },
    lat: {
        type: Number,
    },
    rate: {
        type: Number,
    },
    lon: {
        type: Number,
    },
    isActive: {
        type: Boolean,
        default: false
    },
    isAuto: {
        type: Boolean,
      
    }
}, { timestamps: true });

module.exports = mongoose.model('Provider', ProviderSchema);



