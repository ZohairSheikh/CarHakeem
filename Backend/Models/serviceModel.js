'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var ServiceSchema = new Schema({

    userId: {
        type: Schema.Types.ObjectId,
        

    },
    providerId: {
        type: Schema.Types.ObjectId,
      
    },
    providerUserId: {
        type: Schema.Types.ObjectId,
      
    },
    status: {
        type: String,
        enum: ['pending', 'ongoing', 'completed',"rated"]
    },
    adminStatus: {
        type: String,
        enum: ['pending', 'approved', 'rejected']
    },
    appointmentDate: {
        type: String,
        
    },
    
}, { timestamps: true });

module.exports = mongoose.model('Service', ServiceSchema);



