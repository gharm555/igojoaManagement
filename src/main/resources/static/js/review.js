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

    const userId = userIdElement ? userIdElement.textContent.trim() : null;
    const iplaceName = iplaceNameElement ? iplaceNameElement.textContent.trim() : null;

    if (confirm('정말로 이 리뷰를 삭제하시겠습니까?')) {
        if (button.classList.contains('report-delete')) {
            const logId = liElement.querySelector('#logId').textContent.trim();
            axios.delete('/review/delete', {data: {logId: logId}})
                .then((response) => {
                    console.log(response.data);
                    alert('리뷰가 삭제되었습니다.');
                    loadReportReviews();
                });
        } else {
            console.log("리뷰 삭제 요청: " + userId, iplaceName);
            axios.delete('/review/delete', {data: {reportedId: userId, placeName: iplaceName}})
                .then((response) => {
                    console.log(response.data);
                    alert('리뷰가 삭제되었습니다.');
                    loadInappropriateReviews();
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
    const logId = liElement.querySelector('#logId').textContent.trim();
    if (confirm('정말로 이 신고를 취소하시겠습니까?')) {
        console.log("신고 취소 요청: " + reportedId, rplaceName);
        axios.delete('/review/cancel', {data: {logId : logId}})
            .then((response) => {
                console.log(response.data);
                alert('신고가 취소되었습니다.');
            });
    }
}
const reviewMgr = document.querySelector('#reviewMgr');
const reportTab = document.querySelector('#v-pills-report-tab');
const inappropriateTab = document.querySelector('#v-pills-inappropriate-tab');
reviewMgr.addEventListener('click', (e) => {
    loadReportReviews();
});
reportTab.addEventListener('click', (e) => {
    loadReportReviews();
});
inappropriateTab.addEventListener('click', (e) => {
    loadInappropriateReviews();
});
// 신고 리뷰 로드 함수
function loadReportReviews(page = 0) {
    axios.get(`/review/reportReview?page=${page}`)
        .then((response) => {
            const reportList = document.getElementById('report-list');
            const paginationContainer = document.getElementById('report-pagination');
            const pagination = paginationContainer ? paginationContainer.querySelector('ul') : null;

            if (!pagination) {
                console.error('Pagination container not found');
                return;
            }

            reportList.innerHTML = ''; // 기존 리스트 초기화
            pagination.innerHTML = ''; // 기존 페이지네이션 초기화
            if (response.data.message === "신고된 리뷰가 없습니다.") {
                reportList.innerHTML = `<li class="list-group-item">${response.data.message}</li>`;
                return;
            }

            const reportLogList = response.data.reportLogList.content;
            const currentPage = response.data.reportLogList.number;
            const totalPages = response.data.reportLogList.totalPages;


                reportLogList.forEach((review) => {
                    const li = document.createElement('li');
                    li.className = 'list-group-item d-flex justify-content-between align-items-center';
                    li.innerHTML = `
                        <div>
                            <span class="d-none" id="logId" name="logId">${review.logId}</span>
                            <strong class="reportedId">${review.reporterId != null ? review.reporterId : 'Unknown User'}</strong>
                            <span> (at ${review.reportTime}): </span>
                            <span>${review.reportReason}</span>
                            <br>
                            <strong>${review.reportedNickname != null ? review.reportedNickname : 'Unknown Place'}</strong><span>: </span>
                            <span>${review.review.length > 25 ? review.review.substring(0, 25) + '...' : review.review}</span>
                            <br>
                            <small>장소: <span class="r-placeName">${review.placeName != null ? review.placeName : '알 수 없음'}</span></small>
                        </div>
                        <div>
                            <button class="btn btn-warning btn-sm report-cancel" onclick="cancelReport(this)">신고 취소</button>
                            <button class="btn btn-danger btn-sm report-delete" onclick="deleteReview(this)">삭제</button>
                        </div>`;
                    reportList.appendChild(li);

                });

                // 페이지네이션 버튼 생성
                for (let i = 0; i < totalPages; i++) {
                    const li = document.createElement('li');
                    li.className = `page-item ${i === currentPage ? 'active' : ''}`;
                    li.innerHTML = `<a class="page-link" href="#" onclick="loadReportReviews(${i})">${i + 1}</a>`;
                    pagination.appendChild(li);
                }
            }
        )
        .catch((error) => {
            console.error('신고 리뷰 로드 중 오류 발생:', error);
            alert('신고 리뷰 로드 중 문제가 발생했습니다.');
        });
}


function loadInappropriateReviews(page = 0) {
    axios.get(`/review/inappropriateReview?page=${page}`)
        .then((response) => {
            const inappropriateList = document.getElementById('inappropriate-list');
            const paginationContainer = document.getElementById('inappropriate-pagination');
            const pagination = paginationContainer ? paginationContainer.querySelector('ul') : null;

            inappropriateList.innerHTML = ''; // 기존 리스트 초기화
            if (pagination) {
                pagination.innerHTML = ''; // 기존 페이지네이션 초기화
            } else {
                console.error('Pagination container not found for inappropriate reviews');
            }
            if (response.data.message === "부적절한 리뷰가 없습니다.") {
                inappropriateList.innerHTML = '<li class="list-group-item">부적절한 리뷰가 없습니다.</li>';
                return;
            }
            const reviewList = response.data.reviewList.content;
            const currentPage = response.data.reviewList.number;
            const totalPages = response.data.reviewList.totalPages;


                reviewList.forEach((review) => {
                    const li = document.createElement('li');
                    li.className = 'list-group-item d-flex justify-content-between align-items-center';
                    li.innerHTML = `
                        <div>
                            <strong class="userId">${review.reviewId.userId != null ? review.reviewId.userId : 'Unknown User'}</strong>
                            :
                            <span>${review.reviewContent}</span>
                            <br>
                            <small>장소: <span class="i-placeName">${review.reviewId.placeName != null ? review.reviewId.placeName : '알 수 없음'}</span></small>
                        </div>
                        <div>
                            <button class="btn btn-danger btn-sm inappropriate-delete" onclick="deleteReview(this)">삭제</button>
                        </div>`;
                    inappropriateList.appendChild(li);
                });

                // 페이지네이션 버튼 생성
                if (pagination) {
                    for (let i = 0; i < totalPages; i++) {
                        const li = document.createElement('li');
                        li.className = `page-item ${i === currentPage ? 'active' : ''}`;
                        li.innerHTML = `<a class="page-link" href="#" onclick="loadInappropriateReviews(${i})">${i + 1}</a>`;
                        pagination.appendChild(li);
                    }
                }

        })
        .catch((error) => {
            console.error('부적절한 리뷰 로드 중 오류 발생:', error);
            alert('부적절한 리뷰 로드 중 문제가 발생했습니다.');
        });
}


