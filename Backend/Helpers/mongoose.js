const mongoose = require('mongoose');

//const conn = mongoose.createConnection('mongodb+srv://CarHakeem:CarHakeem@carhakeem.nt5hm.mongodb.net/myFirstDatabase?retryWrites=true&w=majority');

// here enter the db connection string you get on creating your mongodb database, below is the db connection we used.
//const dbURI = 'mongodb+srv://CarHakeem:CarHakeem@carhakeem.nt5hm.mongodb.net/CarHakeem?retryWrites=true&w=majority';
const dbURI = 'your connection string';



// Mongoose connecting event
mongoose.connection.on('connecting', function () {
    console.info('Mongoose connecting');
});

// Mongoose conneccted event
mongoose.connection.on('connected', function () {
    console.info('Mongoose connected ');

});

// Mongoose open event
mongoose.connection.once('open', function () {
    console.info('Mongoose connection opened ');


});

// Mongoose reconnected event
mongoose.connection.on('reconnected', function () {
    console.info('Mongoose reconnected ');
});

// Mongoose disconnected event
mongoose.connection.on('disconnected', function () {
    console.info('Mongoose disconnected');
});

// Mongoose error event
mongoose.connection.on('error', function (error) {
    console.error('Mongoose: ' + error);
    mongoose.disconnect();
});

// Mongoose SIGINT event
process.on('SIGINT', function () {
    mongoose.connection.close(function () {
        console.info('Mongoose disconnected through app termination');
        process.exit(0);
    });
});



mongoose.connect(
    dbURI,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true
    }
);



module.exports.mongoClient = mongoose;
