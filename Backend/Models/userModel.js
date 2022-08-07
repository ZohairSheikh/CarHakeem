'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var UserSchema = new Schema({

    mobileNo: {
        type: String,
        unique: true,
        required: 'Kindly enter the Mobile Number '
    },
    emailAddress: {
        type: String
        //  required: 'Kindly enter the Email Address '
    },
    name: {
        type: String
        //  required: 'Kindly enter the Email Addresss'
    },
    fcmToken: {
        type: String
    },
    password: {
        type: String
    },
    pictureUrl: {
        type: String
    },
    accessToken: {
        type: String
    },
    userType: {
        type: String,
        enum: ['customer', 'provider', 'admin']
    },
    firebaseVerificationCode: {
        type: String,
    },
    isDeleted: {
        type: Boolean,
        default: false
    },
    isActive: {
        type: Boolean,
        default: false
    }
}, { timestamps: true });

module.exports = mongoose.model('Users', UserSchema);


