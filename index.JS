var fs = require('fs');
var express=require('express');
const app=express();
var mysql = require('mysql');
var bodyparser=require('body-parser');

var con = mysql.createConnection({
    host:'localhost',
    user:'root',
    port:3306,
    password:'',
    database:'mydb'
});

var jsonParser = bodyparser.json()
var urlencodedParser = bodyparser.urlencoded({ extended: false })

app.post('/register/', urlencodedParser, function (req, res) {
    var post_data = req.body;
    var name = post_data.name;
    var email = post_data.email;
    var password = post_data.password;
    var phone = post_data.phone;

    con.query('SELECT * FROM user where email=?',[email],function(err,result,fields){
        con.on('error',function(err){
            console.log('[MySQL ERROR]',err);

        })
    

    if(result && result.length)
    res.json('User already exist');
    else{
        con.query("Insert into user (name,email,password,phone) VALUES ('"+name+"','"+email+"','"+password+"','"+phone+"')",function(err,result,fields){
  
            if (err) throw err;  
    
              res.json("User Successfully Registered!");
          
      })
    }
})
})



app.post('/login/',urlencodedParser, function (req, res) {


    var email=req.body.email;

    var password=req.body.password;
  
    con.query("Select * from user WHERE email = ?",[email],function(err,result,fields){
  
  
      if(result && result.length){
  
        if(password==result[0].password){
          res.json("Successfully Login!");
          var words = JSON.stringify(result,null,2);
            fs.writeFile('data.txt',words, finished);
            function finished(err){
            console.log('all set.');}
        }
        else{
          res.json("Wrong Login info data!");  
        }
      }
  })
  
  })

app.listen(3000,()=>{
    console.log('Connected on port 3000')
})
