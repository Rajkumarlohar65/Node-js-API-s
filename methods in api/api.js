// Create the Server and Client
const express = require('express');
const app = express();
const client = require('./db_connection.js');

// This is used to handle conversion to and from json.
app.use(express.json());

const port = 4000;
app.listen(port, () => {
    console.log(`App listening on port ${port}`);
});

// ***GET METHOD*** :-> requesting information from the Server
// GET method usually use to get response data
// Get All Users
app.get('/', (request, response)=>{
    client.query(`SELECT * FROM student`, (err, result)=>{
        if(!err){
            response.send(result.rows);
        }
    });
    client.end;
})

// Get User By Id
app.get('/student/:id', (request, response)=>{
    client.query(`SELECT * FROM student WHERE student_id = ${request.params.id}`, (err, result)=>{
        if(!err){
            response.send(result.rows);
        }
    });
    client.end;
})

// ***POST METHOD*** :-> POST method used to send data
// POST methods are stored in the request body
// Add New User
app.post('/student', (request, response) => {
    const rb = request.body;
    let insertQuery = `INSERT INTO student(student_id, first_name, last_name, mobile_number, email_id)
                        VALUES(${rb.student_id}, '${rb.first_name}', '${rb.last_name}', ${rb.mobile_number}, '${rb.email_id}');`

    client.query(insertQuery, (err, result) => {
        if(!err){
            response.send('Insertion was successful');
        }
        else{
            console.log(err.message);
        }

    })
    client.end;
})

// ***PUT METHOD*** 
// Update User Details
app.put('/student/:id', (request, response) => {

    const rb = request.body;
    const updateQuery = `UPDATE student
                        SET student_id = ${rb.student_id},
                        first_name = '${rb.first_name}',
                        last_name = '${rb.last_name}',
                        mobile_number = ${rb.mobile_number},
                        email_id = '${rb.email_id}'
                        where student_id = ${request.params.id}`

    client.query(updateQuery, (err, result) => {
        if(!err){
            response.send('Update was successfull');
        }
        else{
            console.log(err.message);
        }
    })
    client.end;
})


// ***DELETE METHOD*** 
// Delete a User
app.delete('/student/:id', (request, response) => {
    const deleteQuery = `DELETE FROM student WHERE student_id = ${request.params.id}`

    client.query(deleteQuery, (err, result) => {
        if(!err){
            response.send('Deletion was successfull');
        }
        else{
            console.log(err.message);
        }
    })
    client.end;
})
