document.addEventListener("DOMContentLoaded", function (){

    // 상품에 마우스 갖다대면 줌인 줌아웃 되는 이벤트
    const productListWrap = document.querySelectorAll(".product-list-wrap");
    productListWrap.forEach( e => {
        e.addEventListener("mouseenter", zoomIn);
        e.addEventListener("mouseleave", zoomOut);
    })
    function zoomIn(event) {
        const img = event.target.firstElementChild.firstElementChild.firstElementChild;
        img.style.transform = "scale(1.2)"; //1.2배 확대
        img.style.zIndex = 1;
        img.style.transition = "all 0.5s";// 속도
    }
    function zoomOut(event) {
        const img = event.target.firstElementChild.firstElementChild.firstElementChild;
        img.style.transform = "scale(1)";
        img.style.zIndex = 0;
        img.style.transition = "all 0.5s";
    }




})
