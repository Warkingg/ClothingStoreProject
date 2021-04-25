function remove(id){
    id = id.slice(2)
    $.ajax({
        url: '/cart/remove/' + id,
        type: 'GET',
        success: function (data) {
            let ele = document.getElementById('rm' + id);
            ele.parentElement.parentElement.remove();
        }
    })
}