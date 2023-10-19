document.addEventListener("DOMContentLoaded", function (){
    const productName = document.querySelector("input[name=productName]");
    const description = document.querySelector("input[name=description]");
    const price = document.querySelector("input[name=price]");
    const unitsInStock = document.querySelector("input[name=unitsInStock]");
    const file = document.querySelector("input[name=file]");
    const addProductFrm = document.forms['frmProduct'];

    const addBtn = document.querySelector("#addBtn");

    addBtn.addEventListener("click", function (){

        if(productName.value.length <= 0 || productName.value.trim() === ""){
            alert("상품명을 입력해주세요");
            return;

            console.log(description.value);
            console.log(price.value);
            console.log(unitsInStock.value);
        }
        else if(description.value.length <= 0 || description.value.trim() === ""){
            alert("설명을 적어주세요");
            return;
        }
        else if(price.value <= 0 || price.value.trim() === ""){
            alert("가격을 입력해주세요");
            return;
        }
        else if(unitsInStock.value <= 0 || unitsInStock.value.trim() === ""){
            alert("재고를 입력해주세요");
            return;
        }
        else if(file.value.length <= 0 || file.value.trim() === ""){
            alert("사진을 선택해주세요");
            return;
        }

        addProductFrm.submit();
    })
});