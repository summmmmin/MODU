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
    var code = $('input[name="verificationCode"]').val(); // 사용자가 입력한 인증번호
    console.log(code);

    var requestData = {
        'code': code
    };

    $.ajax({
        url: 'verifyCode', // 인증번호를 확인하는 서버의 URL
        type: 'POST',
        data: JSON.stringify(requestData),
        contentType: 'application/json',
        success: function (response) {
            console.log(response);
            if (response.valid) {
                alert("인증번호 확인에 성공했습니다.");
            } else {
                alert("인증번호가 틀렸습니다.");
            }
        },
    })
}