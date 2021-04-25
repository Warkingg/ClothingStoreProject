function increase (id) {
    id = id.slice(3)
    $.ajax({
        url: '/cart/update/' + id,
        type: 'GET',
        success: function (data) {
            let ele = document.getElementById('qtn' + id);
            ele.innerHTML = data.quantity;
            let ele2 = document.getElementById('st' + id);
            ele2.innerHTML = data.subTotal;
        }
    })
}
function increase2 (id) {
    id = id.slice(3)
    $.ajax({
        url: '/cart/update2/' + id,
        type: 'GET',
        success: function (data) {
            let ele = document.getElementById('qtn' + id);
            ele.innerHTML = data.quantity;
            let ele2 = document.getElementById('st' + id);
            ele2.innerHTML = data.subTotal;
        }
    })
}