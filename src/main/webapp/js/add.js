function addToCart(id){
    $.ajax({
        url: '/items?id=' + id,
        type: 'PUT',
        success: function(){
            alert('Item was successfully added!');
        }
    });
}
