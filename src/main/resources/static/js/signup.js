$(document).ready(function () {
    $('.verification-id-btn').on('click', function () {
        const loginId = $('#signup-id').val().trim();
        $.ajax({
            url: '/member/id-check',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ loginId: loginId }),
            success: function (response) {
                if (response.idConfirm) {
                    console.log("OK");
                    $('.valid-id-result').text(response.idConfirmText)
                                         .css('color', 'blue')
                                         .show();
                } else {
                    $('.valid-id-result').text(response.idConfirmText)
                                         .css('color', 'red')
                                         .show();
                }
            },
            error: function () {
                $('.valid-id-result').text('서버 오류가 발생했습니다. 다시 시도해주세요.')
                                     .css('color', 'red')
                                     .show();
            },
        });
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const passwordField =  $('#signup-pw').val().trim();
    const confirmPasswordField = $('#signup-pw-confirm').val().trim();
    const passwordError = document.getElementById("passwordError");

    function checkPasswordsMatch() {
        const password = passwordField.value;
        const confirmPassword = confirmPasswordField.value;

        if (password && confirmPassword && password !== confirmPassword) {
            // 비밀번호가 일치하지 않으면 에러 메시지 표시
            passwordError.style.display = "block";
        } else {
            // 비밀번호가 일치하면 에러 메시지 숨김
            passwordError.style.display = "none";
        }
    }

    // 비밀번호와 확인 필드에 입력 이벤트 추가
    passwordField.addEventListener("input", checkPasswordsMatch);
    confirmPasswordField.addEventListener("input", checkPasswordsMatch);

    // 폼 제출 이벤트 방지 (테스트용)
    document.getElementById("signupForm").addEventListener("submit", function (e) {
        if (passwordError.style.display === "block") {
            e.preventDefault(); // 폼 제출 막기
            alert("Passwords must match before submitting.");
        }
    });
});