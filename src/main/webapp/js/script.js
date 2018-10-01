var jsonArray = [0,1];


function startSlider() {
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 40000,
        values: [0, 40000],
        slide: function (event, ui) {
            $('#min').val($("#slider-range").slider("values", 0));
            $('#max').val($("#slider-range").slider("values", 1));
        }
    })
}


function goFilter() {
    var n   = $('#nameSelect option:selected').val();
    var mi=$('#min').val();
    var ma=$('#max').val();
    var cap=$('#capacitySelect option:selected').val();

    $.ajax({
        method: 'POST',
        url: '/doFilter',
        async:true,
        data:{name:n, min:mi,max:ma,capacity:cap},
        dataType: "text",
        success: function (data) {
            setItems(data);
        }
    });
}

function buy(item){
    var items=$(item).parent().children();
    var iStr=$('#'+$(items[0]).attr('id') + ' img').attr('src');
    var info=$(items[1]).children();

    var n=$(info[0]).html().replace(/Name: /,"");
    var c=$(info[1]).html().replace(/Capacity: /,"").replace(/ <span class="smaller">gb<\/span>/,"");
    var p=$(info[2]).html().replace(/Price: /,"").replace(/ <span class="smaller">uah<\/span>/  ,"");

    $.ajax({
        method: 'POST',
        url: '/buy',
        async:false,
        data:{name:n, capacity:c,price:p,imgStr:iStr},
        dataType: "text",
        success:function(data){
            document.write(data);
        }
    });

}

function prevpage() {
    $.ajax({
        method: 'POST',
        url: '/prev',
        async:true,
        dataType: "text",
        success: function (data) {
            setItems(data);
        }
    });
}

function nextpage() {
    $.ajax({
        method: 'POST',
        url: '/next',
        async:true,
        dataType: "text",
        success: function (data) {
            setItems(data);
        }
    });
}

function setItems(data) {
    jsonArray = JSON.parse(data);
    if(jsonArray[0]==null && jsonArray[1]==null)
    {
        alert("no pages");
        return;
    }
    initFirst(jsonArray[0]);
    initSecond(jsonArray[1]);
}

function initFirst(ob) {
    $("#item1").show();
    if(ob.capacity==0){
        $("#item1").hide();
    }

    $('#Item1Img').attr('src', ob.imgStr);
    $('#Item1Name').html("Name: "+ob.name);
    $('#Item1Capacity').html("Capacity: "+ob.capacity+" <span class=\"smaller\">gb</span>");
    $('#Item1Price').html("Price: "+ob.price+" <span class=\"smaller\">uah</span>");
}

function initSecond(ob) {
    $("#item2").show();
    if(ob.capacity==0){
        $("#item2").hide();
    }
    $('#Item2Img').attr('src', ob.imgStr);
    $('#Item2Name').html("Name: "+ob.name);
    $('#Item2Capacity').html("Capacity: "+ob.capacity+" <span class=\"smaller\">gb</span>");
    $('#Item2Price').html("Price: "+ob.price+" <span class=\"smaller\">uah</span>")
}

function closeLoginPopup(){
    document.querySelector(".login_popup").style.display="none";
}
function showLoginPopup(){
    document.querySelector(".login_popup").style.display="block";
}
function closeRegPopup(){
    document.querySelector(".reg_popup").style.display="none";
}
function showRegPopup(){
    document.querySelector(".reg_popup").style.display="block";
}



function deleteProduct(sender){
    var tr=sender.parentElement.parentElement;
    var n=tr.children[0].innerHTML;
    var c=tr.children[1].innerHTML;
    var p=tr.children[2].innerHTML;
    $.ajax({
        url: "/deletefromcart",
        data: {name: n, capacity: c,price:p},
        type: 'POST',
        async: false,
    });
    $(location).attr('href','/pages/cart.jsp');
}

function order() {
    $.post("/order",function(data){
        document.write(data);
    });
}