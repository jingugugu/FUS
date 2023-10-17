<!-- 게시글 수정 삭제 버튼 스크립트 -->
document.addEventListener('DOMContentLoaded', function (){
    const btnModify = document.querySelector('.btn-modify');
    const btnRemove = document.querySelector('.btn-remove');
    const frmView = document.querySelector('form[name=frmView]');
    const boardNum = document.querySelector("input[name=num]");

    btnModify.addEventListener('click', function (){
        frmView.action = '/board/modify?boardNum='+boardNum.value;
        console.log('modify hi');
        frmView.submit();
    });

    btnRemove.addEventListener('click', function (){
        frmView.action = '/board/remove?boardNum='+boardNum.value;
        console.log('remove hi');
        frmView.submit();
    });
});
