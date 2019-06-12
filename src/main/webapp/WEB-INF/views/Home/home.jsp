<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 06.06.19
  Time: 00:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <input type="datetime-local" id="date">
        <button onclick="seansesRequest()">Find</button>
    </div>
    <div>
        <table id="tableMovies"/>
        <table id="tableParticularScreening"/>
    </div>
    <div id="reserveData" hidden>
        <input id="name" type="text" placeholder="Name"/>
        <input id="surname" type="text" placeholder="Surame"/>
        <input id="email" type="email" placeholder="Email"/>
        <button onclick="reserveseatsRequest()">Reserve</button>
    </div>
</body>
</html>
<script>
    function seansesRequest() {
        var xhr = new XMLHttpRequest();
        var method = 'GET';
        var url = "/seanses";
        var date = document.getElementById("date").value;
        var params = "?date="+date;
        xhr.open(method, url + params ,true);
        xhr.setRequestHeader("Accept", "application/json");
        console.log('>>request GET to: ' + url + params);
        xhr.send();
        xhr.onreadystatechange = function()
        {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = JSON.parse(xhr.responseText);
                console.log('<<response: ');
                console.log(data);
                CreateTableMovies(data);
            }
        };
    }


    function particularscreeningRequest(){
        var xhr = new XMLHttpRequest();
        var method = 'GET';
        var url = "/particularscreening";
        var params = "?"+this.id;
        window.sessionStorage.setItem("seansId", this.id);
        xhr.open(method, url + params ,true);
        xhr.setRequestHeader("Accept", "application/json");
        console.log('>>request GET to: ' + url + params);
        xhr.send();
        xhr.onreadystatechange = function()
        {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = JSON.parse(xhr.responseText);
                console.log('<<response: ');
                console.log(data);
                CreateTableParticularScreening(data);
            }
        };
    }

    function reserveseatsRequest(){
        var xhr = new XMLHttpRequest();
        var method = 'POST';
        var url = "/reserveseats";
        xhr.open(method, url, true);
        var data = prepareDataForReserveSeats();
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        console.log('>>request POST to: ' + url + "with request");
        console.log(data);
        xhr.send(data);
        xhr.onreadystatechange = function()
        {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resp = xhr.responseText;
                console.log('<<response: ' + resp);
                alert(resp);
            }
        };
    }

    function prepareDataForReserveSeats(){
        seats = document.getElementsByClassName("seatselect");
        var nameVar = document.getElementById("name").value;
        var surnameVar = document.getElementById("surname").value;
        var emailVar = document.getElementById("email").value;
        var seansIdVar = window.sessionStorage.getItem("seansId").substring(8);

        reserveSeat = {
            owner:{
                name:nameVar,
                surname:surnameVar,
                email:emailVar
            },
            seans:{
                id:seansIdVar
            },
            units:[],
        };

        for(var i = 0; i < seats.length; i++){
            var id = seats[i].id;
            var rowPlace = id.split("place");
            var ticketVar = seats[i].parentElement.children[1].value;
            var voucherVar = seats[i].parentElement.children[2].value;
            var unit = {
                row:rowPlace[0],
                place:rowPlace[1],
                ticket:ticketVar,
                voucher:voucherVar
            };
            reserveSeat.units.push(unit)
        }

        return JSON.stringify(reserveSeat)
    }

    function selectSeat(){
        document.getElementById('reserveData').hidden = false;
        if(this.className === "seathold"){}
        else if(this.className === "seatfree"){
            this.className = "seatselect";
            addDropdownTicketType(this.parentElement);
        }
        else {
            this.className = "seatfree";
            this.parentElement.removeChild(this.parentElement.lastChild);
            this.parentElement.removeChild(this.parentElement.lastChild);
        }
    }


    function addDropdownTicketType(element){
        frag = document.createDocumentFragment();
        select = document.createElement("select");

        select.options.add( new Option("ADULT","ADULT", true, true) );
        select.options.add( new Option("STUDENT","STUDENT") );
        select.options.add( new Option("CHILD","CHILD") );

        frag.appendChild(select);
        element.appendChild(frag);

        voucher = document.createElement('input');
        voucher.type = "text";
        voucher.placeholder = "Voucher";
        element.appendChild(voucher);
    }

    function CreateTableMovies(data) {

        var table = document.getElementById('tableMovies');
        table.removeChild(table.lastChild);
        var tbody = document.createElement('tbody');

        var arrValue = new Array();

        for (var h = 0; h < data.length; h++) {
            arrValue.push([data[h].movie.title, data[h].startDttm, data[h].id]);
        }

        var tr = tbody.insertRow(-1);
        var count = 0;
        for (var c = 0; c < arrValue.length; c++) {
            if(count === 3){
                tr = tbody.insertRow(-1);
                count = 0;
            }
            var td = document.createElement('td');
            td = tr.insertCell(-1);
            var wrapper = document.createElement('div');
            wrapper.className = "wrapper";
            var box = document.createElement('div');
            box.addEventListener("click", particularscreeningRequest);
            box.className = "box";
            var textTime = arrValue[c][1];
            textTime = textTime.replace(/T/, " ").substring(0, 16);
            box.innerHTML = "Title: " + arrValue[c][0] + "<br />" +
                            "Time: " + textTime;
            var seansId = arrValue[c][2];
            box.id = "seansId="+seansId;
            wrapper.appendChild(box);
            td.appendChild(wrapper);
            count++;
        }
        tbody.appendChild(tr);
        table.appendChild(tbody);
    }

    function CreateTableParticularScreening(data){

        var table = document.getElementById('tableParticularScreening');
        table.removeChild(table.lastChild);
        var tbody = document.createElement('tbody');

        var arrValue =  new Array();
        for (var h = 0; h < data.reserved.length; h++) {
            var arrValueInternal =  new Array();
            for(var j = 0; j < data.reserved[h].length; j++){
                arrValueInternal.push(data.reserved[h][j]);
            }
            arrValue.push(arrValueInternal);
        }

        var tr = tbody.insertRow(-1);

        for (var c = 0; c < arrValue.length; c++) {
            tr = tbody.insertRow(-1);
            for(var d = 0; d < arrValue[c].length; d++) {
                var td = document.createElement('td');
                td = tr.insertCell(-1);
                var wrapper = document.createElement('div');
                wrapper.className = "wrapper2";
                var box = document.createElement('div');
                box.addEventListener("click", selectSeat);
                if(arrValue[c][d]===true) {
                    box.className = "seathold";
                }
                else {
                    box.className = "seatfree";
                }
                box.id = c + "place" + d;
                wrapper.appendChild(box);
                td.appendChild(wrapper);
            }
        }
        tbody.appendChild(tr);
        table.appendChild(tbody);
    }

</script>
<style>
    body {
        margin: 40px;
    }

    .wrapper {
        display: grid;
        grid-gap: 10px;
        grid-template-columns: 300px 30px;
        background-color: #FFF;
        color: #9C08FF;
    }

    .wrapper2 {
        display: grid;
        grid-gap: 10px;
        grid-template-columns: 30px 75px 75px;
        background-color: #FFF;
        color: #9C08FF;
    }

    .seatfree {
        background-color: #00FF00;
        color: #FFF;
        border-radius: 5px;
        padding: 20px;
        font-size: 150%;
    }

    .seathold {
        background-color: #FF0000;
        color: #FFF;
        border-radius: 5px;
        padding: 20px;
        font-size: 150%;
    }

    .seatselect {
        background-color: #d2cf00;
        color: #FFF;
        border-radius: 5px;
        padding: 20px;
        font-size: 150%;
    }

    .box {
        background-color: #9C08FF;
        color: #FFF;
        border-radius: 5px;
        padding: 20px;
        font-size: 150%;

    }
</style>