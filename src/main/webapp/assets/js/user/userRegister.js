

document.addEventListener("DOMContentLoaded", function (){
    
    // 우편번호  API  스크립트
    document.querySelector('span.btnFindZipcode').addEventListener('click', execDaumPostcode);
        /*
        카카오 우편번호 검색 가이드 페이지 :  https://postcode.map.daum.net/guide
        */
    function execDaumPostcode() {
        /* 상황에 맞춰서 변경해야 하는 부분 */
        const zipcode = document.getElementById('zipCode');
        const address01 = document.getElementById('address01');
        const address02 = document.getElementById('address02');
    
        /* 수정없이 사용 하는 부분 */
        new daum.Postcode({
        oncomplete: function (data) {
        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    
        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var fullAddr = ''; // 최종 주소 변수
        var extraAddr = ''; // 조합형 주소 변수
    
        // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        fullAddr = data.roadAddress;
    } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        fullAddr = data.jibunAddress;
    }
    
        // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
        if (data.userSelectedType === 'R') {
        //법정동명이 있을 경우 추가한다.
        if (data.bname !== '') {
        extraAddr += data.bname;
    }
        // 건물명이 있을 경우 추가한다.
        if (data.buildingName !== '') {
        extraAddr += extraAddr !== '' ? ', ' + data.buildingName : data.buildingName;
    }
        // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
        fullAddr += extraAddr !== '' ? ' (' + extraAddr + ')' : '';
    }
    
        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        zipcode.value = data.zonecode; //5자리 새우편번호 사용
        address01.value = fullAddr;
    
        // 커서를 상세주소 필드로 이동한다.
        address02.focus();
        },
        }).open();
    }


    // 등록 버튼 누르면 phone, birthDate 완성해서 보내기
    const submitBtn = document.getElementById("submitBtn");
    const birthYear = document.querySelector("input[name=birthyy]"); // 가입하기 버튼을 누르면
    const birthMonth = document.querySelector("select[name=birthmm]"); // 생년,월,일 합쳐서
    const birthDay = document.querySelector("input[name=birthdd]"); // birthDate에 밸류로 넣고
    const birthDate = document.getElementById("birthDate");
    const phone1 = document.querySelector("input[name=phone1]"); // phone1
    const phone2 = document.querySelector("input[name=phone2]"); // phone2
    const phone3 = document.querySelector("input[name=phone3]"); // phone3 합쳐서
    const phone = document.getElementById("phone");             // phone에 밸류로 넣고 전송
    const regFrm = document.forms['regFrm'];

    // 유효성 검사에 필요한 애들
    const memberId = document.querySelector("input[name=memberId]");
    const passwd = document.querySelector("input[name=passwd]");
    const passwdCheck = document.getElementById("passwdCheck");
    const name = document.querySelector("input[name=userName]");
    const zipCode = document.querySelector("input[name=zipCode]");
    const findAnswer = document.querySelector("input[name=findAnswer]");

    submitBtn.addEventListener("click", function (){
        if(memberId.value.length <= 0 || memberId.value.trim() === ""){
            alert("아이디를 입력해주세요");
            return;
        }
        else if(passwd.value.length <= 0 || passwd.value.trim() === ""){
            alert("패스워드를 입력해주세요");
            return;
        }
        else if(passwd.value !== passwdCheck.value.trim() === ""){
            alert("패스워드가 일치하지 않습니다");
            return;
        }
        else if(name.value.length <= 0 || name.value.trim() === ""){
            alert("이름을 입력해주세요");
            return;
        }
        else if( (birthYear.value.length <= 0 || birthYear.value.trim() === "") ||
            (birthMonth.value.length <= 0 || birthMonth.value.trim() === "") ||
            (birthDay.value.length <= 0 || birthDay.value.trim() === "")){
            alert("생년월일을 제대로 입력해주세요")
            return;
        }
        else if(zipCode.value.length <= 0 || zipCode.value.trim() === ""){
            alert("주소를 입력해주세요");
            return;
        }
        else if(phone1.value.length <= 0 || phone1.value.trim() === ""){
            alert("연락처를 입력해주세요");
            return;
        }
        else if(phone2.value.length <= 0 || phone2.value.trim() === ""){
            alert("연락처를 입력해주세요");
            return;
        }
        else if(phone3.value.length <= 0 || phone3.value.trim() === ""){
            alert("연락처를 입력해주세요");
            return;
        }
        else if(findAnswer.value.length <= 0 || findAnswer.value.trim() === ""){
            alert("비밀번호 찾기 답변을 입력해주세요");
            return;
        }

        let birthDateValue = birthYear.value+"-"+birthMonth.value+"-"+birthDay.value; // 생년월일 합치고
        let phoneValue = phone1.value+"-"+phone2.value+"-"+phone3.value;              // 폰번호 합쳐서
        birthDate.value = birthDateValue; // 밸류로
        phone.value = phoneValue;         // 넣어줌
        regFrm.submit();
    })

    // 일치하지 않으면 배경 빨간색 효과
    passwdCheck.addEventListener("keydown", function (){
        passwdCheck.addEventListener("keyup", function () {
            if(passwd.value === passwdCheck.value){
                passwdCheck.style.backgroundColor = "white";
                passwdCheck.style.border = "1px solid #ccc";
            }
            else {
                passwdCheck.style.backgroundColor = "rgba(183,111,111,0.4)";
                passwdCheck.style.border = "1px solid red";
            }
        })
    })

})