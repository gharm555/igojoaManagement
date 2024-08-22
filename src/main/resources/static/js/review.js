function deleteReview(button) {
	const liElement = button.closest('li');
	if (!liElement) {
		console.error('리스트 항목을 찾을 수 없습니다.');
		return;
	}

	const reportedIdElement = liElement.querySelector('.reportedId');
	const rplaceNameElement = liElement.querySelector('.r-placeName');
	const userIdElement = liElement.querySelector('.userId');
	const iplaceNameElement = liElement.querySelector('.i-placeName');

	const reportedId = reportedIdElement ? reportedIdElement.textContent.trim() : null;
	const rplaceName = rplaceNameElement ? rplaceNameElement.textContent.trim() : null;
	const userId = userIdElement ? userIdElement.textContent.trim() : null;
	const iplaceName = iplaceNameElement ? iplaceNameElement.textContent.trim() : null;

	if(confirm('정말로 이 리뷰를 삭제하시겠습니까?')) {
		console.log("리뷰 삭제 요청: ", reportedId, rplaceName || userId, iplaceName);
		if (button.classList.contains('report-delete')) {
			axios.post('/review/delete', { data: { reportedId: reportedId, placeName: rplaceName } })
				.then((response) => {
					console.log(response.data);
					alert('리뷰가 삭제되었습니다.');
				});
		} else {
			axios.post('/review/delete', { data: { reportedId: userId, placeName: iplaceName } })
				.then((response) => {
					console.log(response.data);
					alert('리뷰가 삭제되었습니다.');
				});
		}
	}
}

function cancelReport(button) {
	const liElement = button.closest('li');
	if (!liElement) {
		console.error('리스트 항목을 찾을 수 없습니다.');
		return;
	}

	const reportedIdElement = liElement.querySelector('.reportedId');
	const rplaceNameElement = liElement.querySelector('.r-placeName');

	const reportedId = reportedIdElement ? reportedIdElement.textContent.trim() : null;
	const rplaceName = rplaceNameElement ? rplaceNameElement.textContent.trim() : null;

	if(confirm('정말로 이 신고를 취소하시겠습니까?')) {
		console.log("신고 취소 요청: " + reportedId, rplaceName);
		axios.post('/review/cancel', { data: { reportId: reportedId, placeName: rplaceName } })
			.then((response) => {
				console.log(response.data);
				alert('신고가 취소되었습니다.');
			});
	}
}
