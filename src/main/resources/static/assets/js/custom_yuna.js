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

    // Check for empty fields first
    if (!email) {
        showError('#emailError', "이메일을 입력해주세요.");
        return;
    }

    if (!name) {
        showError('#nameError', "이름을 입력해주세요.");
        return;
    }

    if (!phoneNumber) {
        showError('#phoneError', "전화번호를 입력해주세요.");
        return;
    }

    if (!password) {
        showError('#passwordError', "비밀번호를 입력해주세요.");
        return;
    }

    if (!confirmPassword) {
        showError('#confirmPasswordError', "비밀번호를 다시 입력해주세요.");
        return;
    } 

    if (!passwordRegex.test(password)) {
        showError('#passwordError', "비밀번호는 영문자, 숫자, 특수 문자를 모두 포함한 8~16자 사이여야 합니다.");
        return;
    } 

    if (password !== confirmPassword) {
        showError('#confirmPasswordError', "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
    }

    // 아이디 중복 체크를 수행하여 가입 처리 진행
    $.ajax({
        url: 'idvaild',
        type: 'POST',
        data: email, // 객체 형태로 데이터 전송
        contentType: "text/plain",
        success: function(data) {
            if (data === "이미 존재하는 아이디입니다.") {
                showError('#emailError', data);
            } else {
                hideError('#emailError');
                // Now all fields are filled and verified, proceed with SMS verification
                verifySmsCode().then(isSmsVerified => {
                    if(isSmsVerified) {
                        $("form").submit();
                        alert("회원가입이 완료되었습니다.\n" + `환영합니다 ${name}님! \n` + "이메일을 확인하시고, 계정을 활성화 해주세요." );
                    }
                }).catch(() => {
                    showError('#verifyError', "휴대폰 인증을 확인해주세요.");
                });
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 에러:", error); // AJAX 요청 에러 확인용 로그
        }
    });
});

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

    $('input[name="verificationCode"]').on('input', function() {
        hideError('#verifyError');
    });
});