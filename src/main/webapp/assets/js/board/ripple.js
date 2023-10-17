document.addEventListener('DOMContentLoaded', function ()
{
    getRipples(); // 리플 목록 가져오기
});

const getRipples = function () {
    const xhr = new XMLHttpRequest(); //ajax 작업을 위한 객체 생성
    /*리플 목록 가져옴*/
    const boardNum = document.querySelector('form[name=frmRippleView] input[name=boardNum]').value;
    console.log(boardNum);
    xhr.open('GET', '/ripple/get?boardNum=' + boardNum);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;

        if (xhr.status === 200) { //서버에 문서가 존재한다.
            console.log(xhr.response);
            const json = JSON.parse(xhr.response);
            for (let data of json) {
                console.log(data);
            }
            oddRippleTag(json)
        } else { //서버에 문서가 존재하지 않을 때
            console.error('Error', xhr.status, xhr.statusText);
        }
    }
}

<!--리플 등록-->
document.addEventListener('DOMContentLoaded', function (){
    const xhr = new XMLHttpRequest(); //ajax 작업을 위한 객체 생성
    const btnRippleSubmit = document.querySelector('#goRippleSubmit'); //리플 등록 버튼
    const frmRipple = document.querySelector('form[name=frmRipple]');

    btnRippleSubmit.addEventListener('click', function (){ /*등록버튼 클릭시*/
        /*form 안에 input 태그가 있지만 form을 submit하는 것이 아니라 ajax로 값을 넘겨야 되어서 값을 추출 함*/
        const boardNum = frmRipple.boardNum.value;
        const name = frmRipple.name.value;
        const content = frmRipple.content.value;
        console.log(boardNum, name, content);

        xhr.open('POST', '/ripple/add?boardNum=' + boardNum + '&name=' + name + '&content=' + content);
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE) { return; }
            if (xhr.status === 200){
                console.log(xhr.response);
                const json = JSON.parse(xhr.response); //response에 있는 JSON 문자열을 json 객체로 변환
                if(json.result === 'true'){
                    frmRipple.content.value = ''; //등록이 성공했으니 내용에 입력한 내용을 지움

                } else {
                    alert('등록에 실패하였습니다.');
                }
            } else {
                console.error(xhr.status, xhr.statusText);
            }
        }
        getRipples();
    })
});

// 리플 목록을 가져와서 리스트를 다시 만드는 자바스크립트
const oddRippleTag = function (items){
    /*리플 목록을 출력, 처음 로딩시 새로운 글 등록시, 삭제시 사용*/
    const tagUl = document.querySelector('.user-ripple-list ul');
    console.log("삭제 전 : " + tagUl);
    tagUl.innerHTML = ''; //안에 있는 태그를 다 지워줌
    console.log("삭제 후 : " + tagUl);
    for(const item of items){
        const tagLi = document.createElement('li'); //li 태그 생성
        tagLi.innerHTML = "<div class='ripple-name'>"+item.name+"</div>  <div class='ripple-content'>" + item.content + "</div>  <div class='ripple-date'>" + item.insertDate + "</div>";
        if(item.isLogin === true){ //작성자와 로그인한 사용자가 같으면 삭제 버튼 출력
            tagLi.innerHTML += '<span class="removeRippleBtn" onclick="goRippleDelete(\'' + item.rippleId + '\')"><i class="fa-solid fa-x fa-fade" style="color: #ff0000;"></i></span>' ;
        }
        tagLi.setAttribute('class', 'list-group-item'); //li태그에 class 속성 부여
        tagUl.append(tagLi);
    }
}

const goRippleDelete = function (rippleId){
    const xhr = new XMLHttpRequest(); //ajax 작업을 위한 객체 생성
    if(confirm("삭제하시겠습니까?")){
        xhr.open('POST', '/ripple/remove?rippleId=' + rippleId);
        xhr.send();
        xhr.onreadystatechange = () => {
            if(xhr.readyState !== XMLHttpRequest.DONE) return;

            if(xhr.status === 200){
                console.log(xhr.response);
                const json = JSON.parse(xhr.response);
                if(json.result === 'true'){

                }
                else {
                    alert("삭제에 실패했습니다.")
                }
            }
            else {
                console.error('Error', xhr.status, xhr.statusText);
            }
        }
    }
    getRipples();
}

