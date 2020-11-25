const DynamoDB = require('aws-sdk/clients/dynamodb');
const dynamodb = new DynamoDB();

exports.handler = async (event) => {
    // TODO implement
    return await new Promise(resolve => {
        var params = {
            Key: {
                "username": {
                    S: JSON.stringify(event.username)
                },
            },
            TableName: "utd_employee_db"
        };
        dynamodb.getItem(params, function(err,data) {
            if (err) {
                resolve({
                    statusCode: 400,
                    error: "Failed",
                    msg: JSON.stringify(err)
                })
            } else {
                resolve({
                    statusCode: 200,
                    data: data.Item.password.S === event.password
                })
            }
        })
    });

};

