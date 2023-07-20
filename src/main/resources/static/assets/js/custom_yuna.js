//휴대폰 인증번호 발송

$('#sendVerificationCode').on('click', function () {
    var phoneNumber = $('input[name="phNo"]').val(); 
    console.log(phoneNumber)

    var dataToSend = 
            {
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
    
//인증번호 매치

    