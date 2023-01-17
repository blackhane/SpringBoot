/**
 * 
 */
$(document).ready(function() {

	//리스트
	$('.list1').click(function() {
		$.ajax({
			url: '/Ch09/user1',
			method: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});

	//리스트
	$('.list2').click(function() {
		$.ajax({
			url: '/Ch09/user1/a10',
			method: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});

	//등록
	$('.register').click(function() {
		let jsonData = {
			'uid': 'a10',
			'name': '길동',
			'hp': '000-0000-0000',
			'age': '20',
		}
		$.ajax({
			url: '/Ch09/user1',
			method: 'post',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});

	//수정
	$('.modify').click(function() {
		let jsonData = {
			'uid': 'a10',
			'name': '둘리',
			'hp': '000-0000-0000',
			'age': '21',
		}
		$.ajax({
			url: '/Ch09/user1',
			method: 'put',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});

	//삭제
	$('.delete').click(function() {
		$.ajax({
			url: '/Ch09/user1/a10',
			method: 'delete',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});
});	