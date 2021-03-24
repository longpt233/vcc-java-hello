$(document).ready(function () {

    // alert("hello");

    loadData();

    // initEvens();

  
})

function loadData(){
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
                            <div class="cell">${response[i].id}</div>
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
                            <button class="btn btn-primary" onclick="articleDelete()">Delete</button>
                        </td>
                    </tr>`;
            // $('#tbData >tbody:last-child').append(trHtml);
            $('#tbData').append(trHtml);

            
        }

    }).fail(function (response) {

    })
}

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

function articleDelete() {
    $(function () {
        $("tr").on("click", function () {
            var tr = $(this);
            var articleId = tr.children(".ajaxListItemId").text();
            $.ajax("/api/removeArticle", {
                dataType: "json", 
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(articleId), 
                success: function (result) {
                    if (result) tr.remove();
                }
            });

        });
    });
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
        success: addArticleResult  
    });
}

function addArticleResult(forecastData) {

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
                </td>
            </tr>`;
    
    // $('#tbData').append(trHtml);
    $("#ajaxList").append(trHtml);
}