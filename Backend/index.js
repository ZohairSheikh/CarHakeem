const express = require('express')


const app = express()
var apiRoutes = express.Router();



app.use(express.json());
app.use(apiRoutes);

require('./Helpers/mongoose');





require('./Routes/index')(apiRoutes);
app.listen(process.env.PORT || 3000, () => {
    console.log("server runing on port 3000");

   
})

