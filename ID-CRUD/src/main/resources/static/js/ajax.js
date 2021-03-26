$(document).ready(function () {

    // alert("hello");

    loadData();

    // initEvens();


})

function loadData() {
    // 1. Bước 1: gọi service lấy dữ liệu: 
    // debugger;
    $.ajax({
        url: 'http://localhost:2015/idcards',
        method: 'GET',
    }).done(function (response) {
        console.log(response);
        // debugger;
        // 2. Bước 2: xử lý dữ liệu

        // 3. Bước 3: Build html và append lên UI:
        $('#tbData tbody').empty();
        for (var i = 0; i < response.length; i++) {
            console.log(response[i]);
            var DOB = formatDate(response[i].birth);
            // cho nay phai la dau `` 
            var trHtml = `<tr class="el-table__row first">
                        <td> 
                            <div class="cell" >${response[i].id}</div>
                        </td>
                        <td>
                            <div class="cell">${response[i].name}</div>
                        </td>
                        <td>
                            <div class="cell">${response[i].idNumber}</div>
                        </td>
                        <td>
                            <div class="cell">${response[i].address}</div>
                        </td>
                        <td>
                            <div class="cell">${DOB}</div>
                        </td>
                        <td>
                            <button class="btn btn-primary" onclick="articleDelete(${response[i].id})">Delete</button>
                            <button class="btn btn-primary" onclick="showUpdate(${response[i].id})"  data-toggle="modal" data-target="#exampleModal">update</button>
                        
                            </td>
                    </tr>`;
            // $('#tbData >tbody:last-child').append(trHtml);
            $('#tbData').append(trHtml);


        }

    }).fail(function (response) {

    })
}


// input YYYY-MM-DD
// out DD/MM/YYYY
function formatDate(date) {
    var date = new Date(date);
    // lấy ngày:
    var day = date.getDate();

    // lấy tháng:
    var month = date.getMonth() + 1;

    // lấy năm:
    var year = date.getFullYear();
    return day + '/' + month + '/' + year;
}

function reverseDate(date) {

    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}


function articleDelete(id) {

    // console.log("da an nut ");
    $("tr").on("click", function () {
        var tr = $(this);
        // selector.children la tim cac con cua no 
        // console.log(tr.text());
        // k hieu sao k get dc thang con ?
        // var articleId = tr.children("id-row").text();
        console.log("an del " + id);
        var url = "http://localhost:2015/idcard/" + id;
        // console.log(url);
        $.ajax({
            url: url,
            type: "DELETE",
            success: function (result) {
                console.log("ket qua tra ve" + result); // server k tra ve j 
                if (result) tr.remove();
                tr.remove();
            }
        });

    });

}

function showUpdate(id) {

    console.log("call update")
    console.log(id);

    $.ajax({
        url: 'http://localhost:2015/idcard/' + id,
        method: 'GET',
    }).done(function (response) {
        console.log(response);
        $('#txtName').val(response.name);
        $('#txtIdCard').val(response.idNumber);
        $('#txtAddress').val(response.address);
        $('#txtBirth').val(formatDate(response.birth));
    });

    $("#save-update").on("click", function () {
        console.log("an vao nut update roi nha");
        var data = {
            name: $('#txtName').val(),
            idNumber: $('#txtIdCard').val(),
            address: $('#txtAddress').val(),
            birth: reverseDate($('#txtBirth').val())
        };
        console.log("data=" + JSON.stringify(data));

        $.ajax({
            url: "http://localhost:2015/idcard/" + id,
            dataType: "json",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data1) {
                // console.log(data1);
                // addArticleResult(data1);

                if (!alert('ok nha')) {
                    window.location.reload();
                }

            },
            error: function (data) {
                if (!alert('loi gi do roi ban oi ')) {
                    window.location.reload();
                }
            },
        });

        $("#exampleModal").hide();


    })







}






function articleAdd() {
    // $('#title').text("Ok");

    var data = {
        name: $("#valueNewArticleName").val(),
        idNumber: $("#valueNewArticleIdNumber").val(),
        address: $("#valueNewArticleAddress").val(),
        birth: $("#valueNewArticleDOB").val()
    };

    $.ajax("http://localhost:2015/idcard", {
        dataType: "json",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        // data: "aloaloa",  // loi parse tren server 400
        // data :JSON.stringify("alallaoa") ,
        // success: addArticleResult  
        success: function (data1) { // cai data nay la cua server cha ve. lol :))
            // console.log(data1);
            addArticleResult(data1);
        },
        // error :   function (data){      // cai data nay deo lien quan j toi cai data JSON gui di tren 
        //     console.log(data);
        // },
    });
}

function addArticleResult(data) {

    console.log("call add function");
    console.log(data);
    var forecastData = data;
    var DOB = formatDate(forecastData.birth);
    // cho nay phai la dau `` 
    var trHtml = `<tr class="el-table__row first">
                <td> 
                    <div class="cell">${forecastData.id}</div>
                </td>
                <td>
                    <div class="cell">${forecastData.name}</div>
                </td>
                <td>
                    <div class="cell">${forecastData.idNumber}</div>
                </td>
                <td>
                    <div class="cell">${forecastData.address}</div>
                </td>
                <td>
                    <div class="cell">${DOB}</div>
                </td>
                <td>
                    <button class="btn btn-primary" onclick="articleDelete()">Delete</button>
                    <button class="btn btn-primary" onclick="articleUpdate(${forecastData.id})">update</button>
                </td>
            </tr>`;

    // $('#tbData').append(trHtml);
    $("#ajaxList").append(trHtml);
}