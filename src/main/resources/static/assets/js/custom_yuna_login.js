
// 페이지 로드 시 쿠키 값 가져오기
window.onload = function () {
    var savedUsername = getCookie("savedUsername");
    if (savedUsername) {
        document.getElementsByName("username")[0].value = savedUsername;
        document.getElementById("flexCheckDefault1").checked = true;
    }
};
// 쿠키 가져오기 함수
function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length === 2) {
        return parts.pop().split(";").shift();
    }
}


//비밀번호 찾기 팝업창
function openPopup() {
    // 팝업 창을 열 때 사용할 URL
    var popupURL = "/modu/pwdSearch"; // 팝업창에 표시할 페이지 주소로 실제로 사용할 주소

    // 팝업 창 옵션 설정 (크기, 위치, 스크롤 등)
    var popupOptions = "width=500,height=300,top=100,left=100,scrollbars=1";

    // 팝업 창 열기
    window.open(popupURL, "PasswordResetPopup", popupOptions);
}


//로그인할 때 비밀번호 입력 틀렸을 시
    $(document).ready(function() {
        $("form").on("submit", function(e) {
            e.preventDefault();

            var formData = $(this).serialize();

            $.ajax({
                type: "POST",
                url: "/modu/login"
                data: formData,
                success: function(response) {
                    window.location.href = "/modu/prjList";
                },
                error: function(xhr, status, error) {
                    $("#passwordError").show();
                }
            });
        });
    });