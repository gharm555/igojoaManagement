document.addEventListener('DOMContentLoaded', function () {
    const allUsersBtn = document.getElementById('allUsers');
    const reportedUsersBtn = document.getElementById('reportedUsers');
    const blacklistBtn = document.getElementById('blacklist');
    const contentDiv = document.getElementById('userContent');
    const paginationDiv = document.getElementById('pagination');
    const style = document.createElement('style');
    style.textContent = `
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .user-link {
        color: #007bff;
        text-decoration: none;
    }
    .user-link:hover {
        text-decoration: underline;
    }
`;
    document.head.appendChild(style);

    let currentUrl = '/admin/user/user-management';  // 초기값

    allUsersBtn.addEventListener('click', () => {
        currentUrl = '/admin/user/user-management';
        loadUsers(currentUrl);
    });
    reportedUsersBtn.addEventListener('click', () => {
        currentUrl = '/admin/user/reported-users';
        loadUsers(currentUrl);
    });
    // blacklistBtn.addEventListener('click', () => {
    //     currentUrl = '/admin/user/blacklist';
    //     loadUsers(currentUrl);
    // }); // 아직 구현 안됨

    function loadUsers(url, page = 0) {
        axios.get(`${url}?page=${page}`)
            .then(response => {
                const data = response.data;
                renderUsers(data.content);
                renderPagination(data);
            })
            .catch(error => {
                console.error('Error:', error);
                contentDiv.innerHTML = '<p>데이터를 불러오는 중 오류가 발생했습니다.</p>';
            });
    }

    function renderUsers(users) {
        let html = '<table><thead><tr><th>유저 아이디</th>';

        if (currentUrl === '/admin/user/reported-users') {
            html += '<th>현재 닉네임</th><th>신고된 닉네임</th>';
        }

        html += '</tr></thead><tbody>';

        users.forEach(user => {
            const userId = user.userId || user.reportedId;
            html += '<tr>';
            html += `<td><a href="#" class="user-link" data-user-id="${userId}">${userId}</a></td>`;

            if (currentUrl === '/admin/user/reported-users') {
                const currentNickname = user.currentNickname || '-';
                const reportedNickname = user.reportedNickname || '-';
                html += `<td>${currentNickname}</td><td>${reportedNickname}</td>`;
            }

            html += '</tr>';
        });

        html += '</tbody></table>';
        contentDiv.innerHTML = html;

        // 유저 링크에 이벤트 리스너 추가
        document.querySelectorAll('.user-link').forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                const userId = e.target.getAttribute('data-user-id');
                loadUserDetails(userId);
            });
        });
    }

    function loadUserDetails(userId) {
        axios.get(`/admin/user/user-details/${userId}`)
            .then(response => {
                const user = response.data;
                showUserModal(user);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('유저 정보를 불러오는데 실패했습니다.');
            });
    }

    function showUserModal(user) {
        let modalContent;
        if (user.email) { // ROLE_회원_팀장인 경우
            modalContent = `
            <p>이메일: ${user.email}</p>
            <p>전화번호: ${user.phoneNumber}</p>
            <p>닉네임: <input type="text" id="nickNameInput" value="${user.nickName}"></p>
        `;
        } else { // ROLE_회원_팀원인 경우
            modalContent = `
            <p>닉네임: <input type="text" id="nickNameInput" value="${user.nickName}"></p>
        `;
        }

        const modalHtml = `
        <div class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">유저 정보</h5>
                    </div>
                    <div class="modal-body">
                        ${modalContent}
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="submitBtn">전송</button>
                        <button type="button" class="btn btn-secondary" id="cancelBtn">취소</button>
                    </div>
                </div>
            </div>
        </div>
    `;

        document.body.insertAdjacentHTML('beforeend', modalHtml);
        const modal = document.querySelector('.modal');
        modal.style.display = 'block';

        function closeModal() {
            if (modal) {
                modal.remove();
            }
        }

        // 취소 버튼에 이벤트 리스너 추가
        modal.querySelector('#cancelBtn').addEventListener('click', closeModal);

        // ESC 키로 모달 닫기
        document.addEventListener('keydown', function (event) {
            if (event.key === "Escape") {
                closeModal();
            }
        });

        // 모달 바깥 영역 클릭으로 닫기
        modal.addEventListener('click', function (event) {
            if (event.target === modal) {
                closeModal();
            }
        });

        // 전송 버튼
        modal.querySelector('#submitBtn').addEventListener('click', () => {
            const userId = user.userId;
            const newNickName = document.querySelector('#nickNameInput').value;

            axios.post('/admin/user/change-nickname', {
                userId: userId,
                nickName: newNickName
            })
                .then(response => {
                    if (response.data === '변경 성공') {
                        alert('변경 성공');
                        closeModal();
                    } else {
                        alert('처리 실패');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('처리 실패');
                });
        });
    }

    function renderPagination(data) {
        const totalPages = data.totalPages;
        const currentPage = data.number;
        let html = '<nav><ul class="pagination">';

        // 이전 버튼
        html += `<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-page="${currentPage - 1}" ${currentPage === 0 ? 'tabindex="-1" aria-disabled="true"' : ''}>이전</a>
             </li>`;

        let startPage = Math.max(0, Math.min(currentPage - 2, totalPages - 5));
        let endPage = Math.min(startPage + 4, totalPages - 1);

        if (endPage - startPage < 4) {
            startPage = Math.max(0, endPage - 4);
        }

        // 페이지 번호
        for (let i = startPage; i <= endPage; i++) {
            html += `<li class="page-item ${i === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" data-page="${i}">${i + 1}</a>
                 </li>`;
        }

        // 다음 버튼
        html += `<li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-page="${currentPage + 1}" ${currentPage === totalPages - 1 ? 'tabindex="-1" aria-disabled="true"' : ''}>다음</a>
             </li>`;

        html += '</ul></nav>';
        paginationDiv.innerHTML = html;

        // 페이지네이션 링크에 이벤트 리스너 추가
        document.querySelectorAll('.pagination a').forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                if (!link.parentElement.classList.contains('disabled')) {
                    const page = link.getAttribute('data-page');
                    loadUsers(currentUrl, page);
                }
            });
        });
    }

    // 초기 로드
    loadUsers(currentUrl);
});