$(document).ready(function () {

    function showError(selector, message) {
        $(selector).text(message).show();
        $(selector).prev().addClass('error-border');
    }

    function hideError(selector) {
        $(selector).hide().prev().removeClass('error-border');
    }

function checkPhoneNumberDuplicate(phoneNumber) {
    return $.ajax({
        url: 'phNoVaild',
        type: 'POST',
        data: phoneNumber,
        contentType: "text/plain"
    });
}

$('#sendVerificationCode').on('click', function () {
    var phoneNumber = $('input[name="phNo"]').val();
    console.log(phoneNumber);

    checkPhoneNumberDuplicate(phoneNumber).done(function (response) {
        if (response === "이미 존재하는 번호입니다.") {
            showError('#phoneError', response);
            return;
        }

        hideError('#phoneError');
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
                $('input[name="phNo"]').prop('readonly', true);
            },
            error: function (xhr, status, error) {
                alert("하이픈('-')없이 숫자만 입력해주세요.");
                console.error('에러 : ', error);
            }
        });

    }).fail(function (xhr, status, error) {
        console.error('에러 : ', error);
    });
});

    function verifySmsCode(showAlert = false) {
        return new Promise((resolve, reject) => {
            var code = $('input[name="verificationCode"]').val();

            if (!code) {
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

        // 아이디 중복 체크
        var idCheck = $.ajax({
            url: 'idvaild',
            type: 'POST',
            data: email,
            contentType: "text/plain"
        });
        
                // 휴대폰 번호 중복 체크
        var phoneNumberCheck = $.ajax({
            url: 'phNoVaild',
            type: 'POST',
            data: phoneNumber,
            contentType: "text/plain"
        });
        
        $.when(idCheck, phoneNumberCheck).done(function (idResponse, phoneResponse) {
            // idCheck와 phoneNumberCheck 모두 성공
            var idData = idResponse[0]; // idCheck 응답 데이터
            var phoneData = phoneResponse[0]; // phoneNumberCheck 응답 데이터

            if (idData === "이미 존재하는 아이디입니다.") {
                showError('#emailError', idData);
            } else if (phoneData === "이미 존재하는 번호입니다.") {
                showError('#phoneError', phoneData);
            } else {
                // 모든 검증이 완료되면
                hideError('#emailError');
                hideError('#phoneError');

                // 인증 코드 검사를 진행
                verifySmsCode().then(isSmsVerified => {
                    if (isSmsVerified) {
                        $("form").submit();
                        alert("회원가입이 완료되었습니다.\n" + `환영합니다 ${name}님! \n` + "이메일을 확인하시고, 계정을 활성화 해주세요.");
                    }
                }).catch(() => {
                    showError('#verifyError', "휴대폰 인증을 확인해주세요.");
                });
            }
        }).fail(function (idXhr, phoneXhr, idTextStatus, phoneTextStatus) {
            // idCheck나 phoneNumberCheck 중 하나라도 실패
            console.error("AJAX 요청 에러:", idTextStatus, phoneTextStatus);
        });
    });

    $('input[name="id"]').on('input', function () {
        hideError('#emailError');
    });

    $('input[name="nm"]').on('input', function () {
        hideError('#nameError');
    });

    $('input[name="phNo"]').on('input', function () {
        hideError('#phoneError');
    });

    $('input[name="pwd"]').on('input', function () {
        hideError('#passwordError');
    });

    $('input[name="confirmPwd"]').on('input', function () {
        hideError('#confirmPasswordError');
    });

    $('input[name="verificationCode"]').on('input', function () {
        hideError('#verifyError');
    });
});