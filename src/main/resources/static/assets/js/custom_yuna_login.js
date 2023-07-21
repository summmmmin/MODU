 //<![CDATA[
                                            // 사용할 앱의 JavaScript 키를 설정해 주세요.
                                            Kakao
                                                .init('4c82d79f2791bc4817a565753d77220b');
                                            // 카카오 로그인 버튼을 생성합니다.
                                            Kakao.Auth
                                                .createLoginButton({
                                                    container: '#kakao-login-btn',
                                                    success: function(
                                                        authObj) {
                                                        // 로그인 성공시, API를 호출합니다.
                                                        Kakao.API
                                                            .request({
                                                                url: '/v2/user/me',
                                                                success: function(
                                                                    res) {
                                                                  //  alert(JSON
                                                                    //    .stringify(res));
                                                                   // alert(JSON
                                                                     // .stringify(authObj));
                                                                   
                                                                   location.href = "kakaoUser.do"
                                                                    console
                                                                        .log(res.id);
                                                                    console
                                                                        .log(res.kakao_account);
                                                                    console
                                                                        .log(res.properties['nickname']);
                                                                    console
                                                                        .log(authObj.access_token);

                                                                    // AJAX 요청을 보내는 부분
                                                                    var email = res.kakao_account.email;
                                                                    var nickname = res.properties.nickname;

                                                                    console
                                                                        .log(email);
                                                                    console
                                                                        .log(nickname);

                                                                    // AJAX 코드 작성 및 서버로 데이터 전송
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr
                                                                        .open(
                                                                            'POST',
                                                                            'http://localhost:8081/MiddleProject/kakaoLogin.do',
                                                                            true);
                                                                    xhr
                                                                        .setRequestHeader(
                                                                            'Content-Type',
                                                                            'application/json');

                                                                    var data = {
                                                                        email: res.kakao_account.email,
                                                                        nickname: res.properties.nickname
                                                                    };

                                                                    xhr.onreadystatechange = function() {
                                                                        if (xhr.readyState === 4 &&
                                                                            xhr.status === 200) {
                                                                        }
                                                                    };

                                                                    xhr
                                                                        .send(JSON
                                                                            .stringify(data));
                                                                    console
                                                                },
                                                                fail: function(
                                                                    error) {
                                                                }
                                                            });
                                                    },
                                                    fail: function(err) {
                                                    }
                                                });

                                            //]]>

    