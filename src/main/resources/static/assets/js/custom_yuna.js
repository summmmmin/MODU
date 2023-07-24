//휴대폰 인증번호 발송

$('#sendVerificationCode').on('click', function () {
    var phoneNumber = $('input[name="phNo"]').val();
    console.log(phoneNumber)

    var dataToSend = {
        to: phoneNumber
    };
    $.ajax({
        url: 'send',
        type: 'POST',
        data: JSON.stringify(dataToSend),
        contentType: 'application/json',
        success: function (response) {

            console.log('SMS sent successfully:', response);
        },
        error: function (xhr, status, error) {

            console.error('Error sending SMS:', error);
        }
    });
});

// 인증번호 확인

function verifySmsCode() {
    return new Promise((resolve, reject) => {
        var code = $('input[name="verificationCode"]').val();
        console.log(code);

        var requestData = {
            'code': code
        };

        $.ajax({
            url: 'verifyCode',
            type: 'POST',
            data: JSON.stringify(requestData),
            contentType: 'application/json',
            success: function (response) {
                console.log(response);
                if (response.valid) {
                    alert("인증번호 확인에 성공했습니다.");
                    resolve(true);
                } else {
                    alert("인증번호가 틀렸습니다.");
                    resolve(false);
                }
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log(xhr.responseText);
                alert("인증 에러");
                reject(false);
            }
        });
    });
}

$('#signupButton').on('click', function (event) {
    event.preventDefault(); // 기본 제출 동작을 방지

    var email = $('input[name="id"]').val();
    var name = $('input[name="nm"]').val();
    var phoneNumber = $('input[name="phNo"]').val();
    var password = $('input[name="pwd"]').val();
    var confirmPassword = $('input[name="confirmPwd"]').val();

    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

    if (!email || !name || !phoneNumber || !password || !confirmPassword) {
        alert("빈칸을 모두 채워주세요.");
        return false;
    } else if (password !== confirmPassword) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    } else if (!passwordRegex.test(password)) {
        alert("비밀번호는 영문자, 숫자, 특수 문자를 모두 포함한 8~16자 사이여야 합니다.");
        return false;
    } else {
        verifySmsCode().then(isSmsVerified => {
            if(isSmsVerified) {
                $("form").submit(); // 모든 조건이 충족되면 폼 제출가능
                alert("회원가입이 완료되었습니다.\n" + `환영합니다 ${name}님!`);
            }
        })
    }
});
