/**
 * 
 */
$(function() {
	//엔터키 막기
	$('input[type="text"]').keydown(function() {
		if (event.keyCode === 13) {
			event.preventDefault();
		};
	});

	let regUid = /^[a-z0-9_-]{2,10}$/;
	let regPass = /^(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$/;
	let regName = /^[가-힣]{2,4}$/;
	let regNick = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/;
	let regHp = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;


	let uidOk = false;
	let passOk = false;
	let pass1Ok = false;
	let nameOk = false;
	let nickOk = false;
	let emailOk = false;
	let hpOk = false;

	//아이디 입력시
	$('input[name=uid]').keydown(function() {
		uidOk = false;
	});

	//비밀번호 입력시
	$('input[name=pass1]').keydown(function() {
		passOk = false;
		pass1Ok = false;
	});
	$('input[name=pass2]').keydown(function() {
		passOk = false;
	});

	//이름 입력시
	$('input[name=name]').keydown(function() {
		nameOk = false;
	});

	//별명 입력시
	$('input[name=nick]').keydown(function() {
		nickOk = false;
	});

	//이메일 입력시
	$('input[name=email]').keydown(function() {
		emailOk = false;
	});

	//휴대폰 입력시
	$('input[name=hp]').keydown(function() {
		hpOk = false;
	});

	//아이디 중복체크
	$('.btnUidCheck').click(function() {
		let uid = $('input[name=uid]').val();

		//유효성검사
		if (!uid.match(regUid)) {
			alert('유효하지 않는 아이디입니다.');
			setTimeout(function() {
				input.focus();
			}, 10)
			uidOk = false;
			return;
		}

		//유효성 검사 통과시
		$.ajax({
			url: '/Sboard/user/checkUid',
			method: 'get',
			data: { 'uid': uid },
			dateType: 'json',
			success: function(data) {
				if (data.result == 0) {
					alert('사용가능한 아이디입니다.');
					setTimeout(function() {
						input.focus();
					}, 10)
					uidOk = true;
				}
				else {
					alert('이미 사용중인 아이디입니다.');
					setTimeout(function() {
						input.focus();
					}, 10)
					uidOk = false;
				}
			}
		});
	});

	//비밀번호1, 비밀번호2 확인
	$('input[name=pass1]').focusout(function() {
		let pass1 = $(this).val();

		if (pass1Ok)
			return;

		if (pass1 == "") {
			pass1Ok = false
			return;
		}

		if (pass1.match(regPass)) {
			alert("유효한 비밀번호입니다.")
			setTimeout(function() {
				input.focus();
			}, 10)
			pass1Ok = true
		} else {
			alert("유효하지 않은 비밀번호입니다.")
			setTimeout(function() {
				input.focus();
			}, 10)
			pass1Ok = false
		}
	});

	$('input[name=pass2]').focusout(function() {
		let pass1 = $('input[name=pass1]').val();
		let pass2 = $(this).val();

		if (pass1Ok && passOk)
			return;

		if (pass2 == "") {
			passOk = false
			return;
		}

		if (pass1 == pass2) {
			alert('비밀번호가 일치합니다.');
			setTimeout(function() {
				input.focus();
			}, 10)
			passOk = true;
		} else {
			alert('비밀번호가 일치하지 않습니다.');
			setTimeout(function() {
				input.focus();
			}, 10)
			passOk = false;
		}
	});

	//이름 유효성검사
	$('input[name=name]').focusout(function() {
		let name = $(this).val();

		if (nameOk)
			return;

		if (name == "") {
			nameOk = false;
			return;
		}

		if (name.match(regName)) {
			alert("유효한 이름입니다.")
			setTimeout(function() {
				input.focus();
			}, 10)
			nameOk = true;
		} else {
			alert('유효하지 않는 이름입니다.');
			setTimeout(function() {
				input.focus();
			}, 10)
			nameOk = false;
		}
	});

	//별명 중복체크
	$('.btnNickCheck').click(function() {
		let nick = $('input[name=nick]').val();

		//유효성검사
		if (!nick.match(regNick)) {
			alert('유효하지 않는 별명입니다.');
			setTimeout(function() {
				input.focus();
			}, 10)
			nickOk = false;
			return;
		}

		//유효성 검사 통과시
		$.ajax({
			url: '/Sboard/user/checkNick',
			method: 'get',
			data: { 'nick': nick },
			dateType: 'json',
			success: function(data) {
				if (data.result == 0) {
					alert('사용가능한 별명입니다.');
					nickOk = true;
				}
				else {
					nickOk = false;
					alert('이미 사용중인 별명입니다..');
				}
			}
		});
	});

	//휴대폰 유효성검사
	$('input[name=hp]').focusout(function() {
		let hp = $(this).val();

		if (hpOk)
			return;

		if (hp == "") {
			hpOk = false;
			return;
		}

		if (hp.match(regHp)) {
			alert("유효한 휴대폰번호입니다.")
			setTimeout(function() {
				input.focus();
			}, 10)
			hpOk = true;
		} else {
			alert('유효하지 않는 휴대폰번호입니다.')
			hpOk = false;
		}
	});


	//검사
	$('form').submit(function() {
		if (!uidOk) {
			alert('아이디를 확인하세요.')
			$('input[name=uid]').focus();
			return false;
		}
		if (!passOk) {
			alert('비밀번호를 확인하세요.')
			$('input[name=pass2]').focus();
			return false;
		}
		if (!pass1Ok) {
			alert('비밀번호를 확인하세요.')
			$('input[name=pass1]').focus();
			return false;
		}
		if (!nameOk) {
			alert('이름를 확인하세요.')
			$('input[name=name]').focus();
			return false;
		}
		if (!nickOk) {
			alert('별명을 확인하세요.')
			$('input[name=nick]').focus();
			return false;
		}
		//이메일 체크
		if ($('input[name=email]' == "")) {
			alert('이메일을 확인하세요.')
			$('input[name=email]').focus();
			return false;
		}
		if (!hpOk) {
			alert('휴대폰을 확인하세요.')
			$('input[name=hp]').focus();
			return false;
		}
		return true;
	});

});