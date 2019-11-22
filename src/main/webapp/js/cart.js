function removeFromCart(id){
    $.ajax({
        url: '/cart?id=' + id,
        type: 'DELETE',
        success: function(result){
            $('#'+id).remove();
            $('#totalPrice').text(result);
        }
    });
}

function removeAll(){
    $.ajax({
        url: '/cart?id=removeAll',
        type: 'DELETE',
        success: function(){
            alert("Cart is clear.");
            window.location.href = '/items';
        }
    });
}

function quantity(id, price){
var quantity = $('#quantity'+id).val();
    $.ajax({
        url: '/cart?id=' + id + '&quantity=' + quantity,
        type: 'PUT',
        success: function(result){
            $('#totalPrice').text(result);
            $('#totalItem'+id).text(price*quantity)
        }
    });
}
