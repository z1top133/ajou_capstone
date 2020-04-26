var app = require('express')();
// var app = express();
var http = require('http').createServer(app);
var io = require('socket.io')(http);

app.get('/', (req, res)=>{
    // res.send('hi');
    res.sendFile(__dirname+'/index.html');
});

io.on('connection', (socket)=>{//서버와 클라이언트의 소켓이 연결되었을 때 실행됨.
    console.log(socket.id);
    io.emit('chat message', '김영호 관리자 : 건전한 채팅 지켜주세요.');
    socket.broadcast.emit('chat message', '되는가?');//메세지를 전송한 클라이언트를 제외한 모든 클라이언트에게 메세지 전송
    
    var ID = ''
    socket.on("login", (data)=>{
        console.log(data);
        socket.broadcast.emit("login", socket.id+"님이 접속하셨습니다.");
        ID = data.userid;
    });
    
    socket.on('chat message', (msg)=>{ //클라이언트->서버로 오는 요청을 처리할 이벤트 리스너
                                    //즉, chat message라는 이벤트가 오면 처리함 
       
         console.log(socket.id+" : "+msg);                           
        // console.log('message: '+msg); 
        io.emit('chat message', socket.id+" : "+msg); //다시 클라이언트로 데이터를 보낸다.
    });
    
    
});

http.listen(3000, ()=>{
    console.log("connected!!!");
});