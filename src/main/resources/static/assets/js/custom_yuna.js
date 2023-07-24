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
                $('#signupButton').prop('disabled', false);
            } else {
                alert("인증번호가 틀렸습니다.");
                $('#signupButton').prop('disabled', true);
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.responseText);
            alert("Error occurred during the verification process. Please try again later.");
            $('#signupButton').prop('disabled', true);
        }
    });
}
// function verifySmsCode() {
//     var code = $('input[name="verificationCode"]').val();
//     console.log(code);

//     var requestData = {
//         'code': code
//     };

//     $.ajax({
//         url: 'verifyCode',
//         type: 'POST',
//         data: JSON.stringify(requestData),
//         contentType: 'application/json',
//         success: function (response) {
//             console.log(response);
//             if (response.valid) {
//                 alert("인증번호 확인에 성공했습니다.");
//                 $('#signupButton').prop('disabled', false);
//             } else {
//                 alert("인증번호가 틀렸습니다.");
//             }
//         },
//         error: function (xhr, textStatus, errorThrown) {
//             console.log(xhr.responseText);
//             alert("Error occurred during the verification process. Please try again later.");
//         }
//     });
// }

$(document).ready(function () {
    $('#signupButton').prop('disabled', true);

    $('input[name="pwd"], input[name="pwdConfirm"]').on('input', function () {
        var password = $('input[name="pwd"]').val();
        var confirmPassword = $('input[name="pwdConfirm"]').val();

        var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;

        if (password === confirmPassword && password.match(passwordRegex)) {
            $('#pwdError').text('');
            $('#pwdConfirmError').text('');
            $('#signupButton').prop('disabled', false); // Enable signup button when passwords match
        } else {
            $('#pwdError').text('비밀번호는 알파벳과 특수문자를 포함해서 최소 8자, 최대 16자로 작성해야 합니다.');
            $('#pwdConfirmError').text('비밀번호가 일치하지 않습니다.');
            $('#signupButton').prop('disabled', true); // Disable signup button when passwords don't match
        }
    });

    $('form').on('submit', function (event) {
        var password = $('input[name="pwd"]').val();
        var confirmPassword = $('input[name="pwdConfirm"]').val();
        var verificationCode = $('input[name="verificationCode"]').val();

        if (password === '' || confirmPassword === '' || verificationCode === '') {
            event.preventDefault();
            alert('빈칸을 모두 채워주세요.');
        }
    });
});