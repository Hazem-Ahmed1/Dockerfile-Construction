const express = require('express')


const port = 5000
const app =  express();


app.get('/',(req,res)=>res.send('<h1>Hello Hazem Hi Hi</h1>'))
app.listen(port,()=>console.log(`app is running on port ${port}`))
