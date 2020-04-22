var app = require('express')();
// var app = express();
var http = require('http').createServer(app);
var io = require('socket.io')(http);

app.get('/', (req, res)=>{
    // res.send('hi');
    res.sendFile(__dirname+'/index.html');
});

io.on('connection', (socket)=>{
    // console.log("here?");
    socket.emit('chat message', '김영호 관리자 : 건전한 채팅 지켜주세요.');
    socket.on('chat message', (msg)=>{
        // console.log('message: '+msg); 
        io.emit('chat message', msg);
    });

});

http.listen(3000, ()=>{
    console.log("connected!!");
});


