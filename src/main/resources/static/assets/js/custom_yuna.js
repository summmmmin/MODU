$(document).ready(function() {

    function showError(selector, message) {
        $(selector).text(message).show();
        $(selector).prev().addClass('error-border');
    }

    function hideError(selector) {
        $(selector).hide().prev().removeClass('error-border');
    }


    $('#sendVerificationCode').on('click', function () {
        var phoneNumber = $('input[name="phNo"]').val();
        var dataToSend = {
            to: phoneNumber
        };
        $.ajax({
            url: 'send',
            type: 'POST',
            data: JSON.stringify(dataToSend),
            contentType: 'application/json',
            success: function (response) {
                alert("인증번호가 발송되었습니다.");
            },
            error: function (xhr, status, error) {
                alert("하이픈('-')없이 숫자만 입력해주세요.");
                console.error('에러 : ', error);
            }
        });
    });

    function verifySmsCode(showAlert = false) {
        return new Promise((resolve, reject) => {
            var code = $('input[name="verificationCode"]').val();

            if(!code) {
                showError('#verifyError', "휴대폰 인증을 해주십시오.");
                reject(false);
            } else {
                hideError('#verifyError');
            }

            var requestData = {
                'code': code
            };

            $.ajax({
                url: 'verifyCode',
                type: 'POST',
                data: JSON.stringify(requestData),
                contentType: 'application/json',
                success: function (response) {
                    if (response.valid) {
                        if (showAlert) {
                            alert("인증번호 확인에 성공했습니다!");
                        }
                        resolve(true);
                    } else {
                        showError('#verifyError', "인증번호가 일치하지 않습니다.");
                        reject(false);
                    }
                },
                error: function (xhr, textStatus, errorThrown) {
                    showError('#verifyError', "인증 에러");
                    reject(false);
                }
            });
        });
    }

    $('#verifyCodeButton').on('click', function () {
        verifySmsCode(true);
    });

        $('#signupButton').on('click', function (event) {
        event.preventDefault();

        var email = $('input[name="id"]').val();
        var name = $('input[name="nm"]').val();
        var phoneNumber = $('input[name="phNo"]').val();
        var password = $('input[name="pwd"]').val();
        var confirmPassword = $('input[name="confirmPwd"]').val();

        var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

    // 휴대폰 번호 중복 체크
    var phoneNumberPromise = new Promise((resolve, reject) => {
        $.ajax({
            url: 'phNoVaild',
            type: 'POST',
            data: phoneNumber,
            contentType: "text/plain",
            success: function(data) {
                if (data === "이미 존재하는 번호입니다.") {
                    showError('#phoneError', data);
                    reject(false);
                } else {
                    hideError('#phoneError');
                    resolve(true);
                }
            },
            error: function(xhr, status, error) {
                console.error("휴대폰 번호 중복 체크 AJAX 요청 에러:", error);
                reject(false);
            }
        });
    });

    // 이메일 중복 체크
    var emailPromise = new Promise((resolve, reject) => {
        $.ajax({
            url: 'idvaild',
            type: 'POST',
            data: email,
            contentType: "text/plain",
            success: function(data) {
                if (data === "이미 존재하는 아이디입니다.") {
                    showError('#emailError', data);
                    reject(false);
                } else {
                    hideError('#emailError');
                    resolve(true);
                }
            },
            error: function(xhr, status, error) {
                console.error("이메일 중복 체크 AJAX 요청 에러:", error);
                reject(false);
            }
        });
    });

    Promise.all([phoneNumberPromise, emailPromise])
        .then(() => {
            // 폼 데이터 유효성 검사 및 회원가입 처리
            if (!name) {
                showError('#nameError', "이름을 입력해주세요.");
            } else {
                hideError('#nameError');
            }


    $('input[name="id"]').on('input', function() {
        hideError('#emailError');
    });

    $('input[name="nm"]').on('input', function() {
        hideError('#nameError');
    });

    $('input[name="phNo"]').on('input', function() {
        hideError('#phoneError');
    });

    $('input[name="pwd"]').on('input', function() {
        hideError('#passwordError');
    });

    $('input[name="confirmPwd"]').on('input', function() {
        hideError('#confirmPasswordError');
    });
    
     $('input[name="phNo"]').on('input', function() {
        hideError('#phoneError');
        hideError('#idDuplicateMessage');
    });

    $('input[name="id"]').on('input', function() {
        hideError('#emailError');
        hideError('#idDuplicateMessage');
    });

    $('input[name="verificationCode"]').on('input', function() {
        hideError('#verifyError');
    });
});