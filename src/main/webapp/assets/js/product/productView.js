document.addEventListener("DOMContentLoaded", function () {


    // 상품 이미지 마우스 갖다대면 돋보기 이벤트
    const productImg = document.querySelector("#product-image");

    const imgWrap = document.querySelector(".img-scale-wrap");


    const zoom = 1.5; // 2배 확대
    const magnifierClone = document.createElement("div")
    magnifierClone.className = "magnifier";
    imgWrap.appendChild(magnifierClone); // 확대 이미지를 실시간으로 저장하는 div 생성 후 원래 상품 이미지와 겹치는 자리에 저장
    const magnifier = document.querySelector(".magnifier");
    // 그리고 생성된 photo div에 백그라운드이미지로 상품 이미지 저장
    magnifier.style.background = "url(" + productImg.getAttribute("src") + ") no-repeat";

    magnifier.style.backgroundSize = productImg.clientWidth * zoom + "px " + productImg.clientHeight * zoom + "px";

    imgWrap.addEventListener("mousemove", magnify);

    function magnify(e) {
        // 실시간 마우스 좌표값
        let mouseX = e.pageX - this.offsetLeft;
        let mouseY = e.pageY - this.offsetTop;

        // 컨테이너 안에 마우스가 있으면 magnifier를 드러나게 하고, 벗어나면 감춤
        if(mouseX < this.clientWidth && mouseY < this.clientHeight && mouseX > 0 && mouseY > 0){
            // fadeIn(magnifier);
            magnifier.style.opacity = "1.1";
            magnifier.style.display = "block";
        } else {
            // fadeOut(magnifier);
            magnifier.style.opacity = "0";
            magnifier.style.display = "none";
        }

        // 만약 magnifier가 보이는 상태라면
        if(magnifier.style.opacity > 0) {

            // 이미지가 위치한 마우스 위치를 기준으로 본래 이미지 크기에 대한 마우스 좌표를 얻음
            let rx = -(mouseX * zoom - magnifier.clientWidth / 2);
            let ry = -(mouseY * zoom - magnifier.clientHeight / 2);

            // div.magnifier를 마우스 가운데 위치시키기 위해 width, height 절반을 차감
            let px = mouseX - magnifier.clientWidth / 2;
            let py = mouseY - magnifier.clientHeight / 2;

            // 적용
            magnifier.style.left = px;
            magnifier.style.top = py;
            magnifier.style.backgroundPosition = rx +"px " + ry + "px";

        }

    }

    // productImg.addEventListener("mouseenter", zoomIn);
    // productImg.addEventListener("mouseleave", zoomOut);
    //
    // function zoomIn(event) {
    //     event.target.style.transform = "scale(1.2)"; //1.2배 확대
    //     event.target.style.zIndex = 1;
    //     event.target.style.transition = "all 0.5s";// 속도
    // }
    //
    // function zoomOut(event) {
    //     event.target.style.transform = "scale(1)";
    //     event.target.style.zIndex = 0;
    //     event.target.style.transition = "all 0.5s";
    // }
})

