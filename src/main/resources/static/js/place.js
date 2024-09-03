document.addEventListener('DOMContentLoaded', function() {
    var currentExistingPage = 0;
    var currentNewPage = 0;


    document.querySelectorAll('.img-fluid').forEach(function(img) {
        img.addEventListener('click', function() {
            // 해당 이미지에 대응하는 파일 입력 요소를 찾음
            var inputId = this.id + '-input';
            var fileInput = document.getElementById(inputId);

            if (fileInput) {
                fileInput.click(); // 파일 선택 창 열기
            }
        });
    });

    // 파일 선택 후 이미지 미리 보기 업데이트
    document.querySelectorAll('input[type="file"]').forEach(function(input) {
        input.addEventListener('change', function(event) {
            var file = event.target.files[0];

            if (file) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    // 선택된 파일의 데이터 URL을 사용해 이미지 src 변경
                    var imgId = input.id.replace('-input', '');
                    var imgElement = document.getElementById(imgId);
                    imgElement.src = e.target.result;
                };

                reader.readAsDataURL(file);
            }
        });
    });




    function loadExistingPlaces(page) {
        currentExistingPage = page;
        axios.get('/api/detailPlacesList', {
            params: {
                page: page
            }
        })
            .then(function(response) {
                var existingPlacesList = document.getElementById('existing-places-list');
                var existingPlacesPagination = document.getElementById('existing-places-pagination');
                if (existingPlacesList) {
                    existingPlacesList.innerHTML = ''; // 기존 내용 비우기
                    response.data.content.forEach(function(place) {
                        var listItem = `
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <a href="#" class="show-detail-btn" data-place-name="${place.placeName}" data-place-type="existing">
                            <span>${place.placeName}</span>
                        </a>
                        <div>
                            <button type="button" class="btn btn-secondary btn-sm">수정</button>
                            <button type="button" class="btn btn-danger btn-sm">삭제</button>
                        </div>
                    </li>`;
                        existingPlacesList.insertAdjacentHTML('beforeend', listItem);
                    });

                    // 상세 정보 버튼 클릭 이벤트 설정
                    document.querySelectorAll('.show-detail-btn').forEach(function(button) {
                        button.addEventListener('click', function(event) {
                            event.preventDefault();
                            var placeName = this.getAttribute('data-place-name');
                            var placeType = this.getAttribute('data-place-type');
                            if (placeType === 'existing') {
                                loadPlaceDetails(placeName); // 기존 명소 로드
                            } else {
                                var reporterId = this.getAttribute('data-reporter-id'); // 신규 명소인 경우 리포터 ID도 가져옴
                                loadConfirmPlaceDetails(placeName, reporterId);
                            }
                        });
                    });

                    updatePagination(existingPlacesPagination, response.data.totalPages, loadExistingPlaces, currentExistingPage);
                } else {
                    console.error('existing-places-list element not found');
                }
            })
            .catch(function(error) {
                console.error('Error fetching existing places:', error);
            });
    }

    function loadNewPlaces(page) {
        currentNewPage = page;
        axios.get('/api/detailConfirmList', {
            params: {
                page: page
            }
        })
            .then(function(response) {
                var newPlacesList = document.getElementById('new-places-list');
                var newPlacesPagination = document.getElementById('new-places-pagination');
                if (newPlacesList) {
                    newPlacesList.innerHTML = ''; // 기존 내용 비우기
                    response.data.content.forEach(function(place) {
                        var listItem = `
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <a href="#" class="show-detail-btn" data-place-name="${place.placeName}" data-reporter-id="${place.reporterId}" data-place-type="new">
                            <span>${place.placeName}</span>
                        </a>
                        <div>
                            <button type="button" class="btn btn-primary btn-sm">승인</button>
                            <button type="button" class="btn btn-secondary btn-sm">수정</button>
                            <button type="button" class="btn btn-danger btn-sm">삭제</button>
                        </div>
                    </li>`;
                        newPlacesList.insertAdjacentHTML('beforeend', listItem);
                    });

                    // 상세 정보 버튼 클릭 이벤트 설정
                    document.querySelectorAll('.show-detail-btn').forEach(function(button) {
                        button.addEventListener('click', function(event) {
                            event.preventDefault();
                            var placeName = this.getAttribute('data-place-name');
                            var reporterId = this.getAttribute('data-reporter-id');
                            loadConfirmPlaceDetails(placeName, reporterId);
                        });
                    });

                    updatePagination(newPlacesPagination, response.data.totalPages, loadNewPlaces, currentNewPage);
                } else {
                    console.error('new-places-list element not found');
                }
            })
            .catch(function(error) {
                console.error('Error fetching new places:', error);
            });
    }

    function loadPlaceDetails(placeName) {
        axios.get(`/api/placeDetails/${placeName}`)
            .then(function(response) {
                var place = response.data;
                var fullAddress = `${place.largeAddress} ${place.mediumAddress} ${place.smallAddress}`;
                document.getElementById('modal-placeName').value = place.placeName;
                document.getElementById('modal-largeAddress').value = fullAddress;
                document.getElementById('modal-placeDescription').value = place.placeDescription;
                document.getElementById('modal-operatingHours').value = place.operatingHours;
                document.getElementById('modal-placeLatitude').value = place.placeLatitude;
                document.getElementById('modal-placeLongitude').value = place.placeLongitude;
                setImageWithName('modal-firstImage', place.firstUrl, place.firstImageName);
                setImageWithName('modal-secondImage', place.secondUrl, place.secondImageName);
                setImageWithName('modal-thirdImage', place.thirdUrl, place.thirdImageName);

                var modal = new bootstrap.Modal(document.getElementById('detailModal'));
                var reporterIdElement = document.getElementById('modal-reporterId');
                var reporterIdContainer = reporterIdElement.closest('.mb-3');

                if (place.reporterId) {
                    reporterIdElement.value = place.reporterId;
                    reporterIdContainer.style.display = 'block'; // 보여줍니다.
                } else {
                    reporterIdContainer.style.display = 'none'; // 숨깁니다.
                }

                modal.show();
            })
            .catch(function(error) {
                console.error('Error loading place details:', error);
            });
    }

    function setImageWithName(elementId, imageUrl, imageName) {
        const imgElement = document.getElementById(elementId);
        const imgNameElement = document.getElementById(`${elementId}-name`);

        if (imageUrl) {
            console.log(`Setting ${elementId} src to: ${imageUrl}`);
            imgElement.src = imageUrl;
            imgElement.style.display = 'block';

            if (imgNameElement) {
                imgNameElement.textContent = imageName || 'No name provided';
                imgNameElement.style.display = 'block';
            }

            imgElement.onload = function() {
                console.log(`Image loaded successfully: ${imageUrl}`);
            };
            imgElement.onerror = function() {
                console.error(`Failed to load image: ${imageUrl}`);
                imgElement.style.display = 'none';
                if (imgNameElement) imgNameElement.style.display = 'none';
            };
        } else {
            console.log(`No image URL for ${elementId}, hiding the element`);
            imgElement.style.display = 'none';
            if (imgNameElement) imgNameElement.style.display = 'none';
        }
    }

    function loadConfirmPlaceDetails(placeName, reporterId) {
        if (!reporterId) {
            console.error('Reporter ID is null or undefined');
            return;
        }
        axios.get(`/api/confirmPlaceDetails`, {
            params: {
                placeName: placeName,
                reporterId: reporterId
            }
        })
            .then(function(response) {
                var place = response.data;
                var fullAddress = `${place.largeAddress} ${place.mediumAddress} ${place.smallAddress}`;
                document.getElementById('modal-reporterId').value = place.reporterId;
                document.getElementById('modal-placeName').value = place.placeName;
                document.getElementById('modal-largeAddress').value = fullAddress;
                document.getElementById('modal-placeDescription').value = place.placeDescription;
                document.getElementById('modal-operatingHours').value = place.operatingHours;
                document.getElementById('modal-placeLatitude').value = place.placeLatitude;
                document.getElementById('modal-placeLongitude').value = place.placeLongitude;
                setImageWithName('modal-firstImage', place.firstUrl, place.firstImageName);
                setImageWithName('modal-secondImage', place.secondUrl, place.secondImageName);
                setImageWithName('modal-thirdImage', place.thirdUrl, place.thirdImageName);

                var reporterIdElement = document.getElementById('modal-reporterId');
                var reporterIdContainer = reporterIdElement.closest('.mb-3');

                // Reporter ID가 존재할 경우 보여주고, 그렇지 않을 경우 숨깁니다.
                if (place.reporterId) {
                    reporterIdElement.value = place.reporterId;
                    reporterIdContainer.style.display = 'block'; // 보이게 설정
                } else {
                    reporterIdContainer.style.display = 'none'; // 숨김
                }

                var modal = new bootstrap.Modal(document.getElementById('detailModal'));
                modal.show();
            })
            .catch(function(error) {
                console.error('Error loading confirm place details:', error);
            });
    }


    // 페이지네이션 업데이트 함수
    function updatePagination(paginationElement, totalPages, loadFunction, currentPage) {
        paginationElement.innerHTML = ''; // 기존 페이징 비우기

        // 이전 페이지 버튼
        var prevButton = `<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                          </li>`;
        paginationElement.insertAdjacentHTML('beforeend', prevButton);

        // 페이지 번호 버튼
        for (var i = 0; i < totalPages; i++) {
            var pageItem = `
                <li class="page-item ${currentPage === i ? 'active' : ''}">
                    <a class="page-link" href="#">${i + 1}</a>
                </li>`;
            paginationElement.insertAdjacentHTML('beforeend', pageItem);
        }

        // 다음 페이지 버튼
        var nextButton = `<li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                          </li>`;
        paginationElement.insertAdjacentHTML('beforeend', nextButton);

        // 페이지 버튼 클릭 이벤트 설정
        var pageLinks = paginationElement.querySelectorAll('.page-link');
        pageLinks.forEach(function(link, index) {
            link.addEventListener('click', function(event) {
                event.preventDefault();
                if (index === 0 && currentPage > 0) {
                    loadFunction(currentPage - 1); // 이전 페이지 로드
                } else if (index === pageLinks.length - 1 && currentPage < totalPages - 1) {
                    loadFunction(currentPage + 1); // 다음 페이지 로드
                } else if (index !== 0 && index !== pageLinks.length - 1) {
                    loadFunction(index - 1); // 특정 페이지 로드
                }
            });
        });
    }

    // 탭 전환 이벤트 리스너 추가
    var tabs = document.querySelectorAll('a[data-bs-toggle="pill"]');
    tabs.forEach(function(tab) {
        tab.addEventListener('shown.bs.tab', function (event) {
            if (event.target.id === 'v-pills-existing-tab') {
                loadExistingPlaces(currentExistingPage);
            } else if (event.target.id === 'v-pills-new-tab') {
                loadNewPlaces(currentNewPage);
            }
        });
    });

    // 처음 로딩 시 첫 페이지 데이터를 불러옴
    loadExistingPlaces(currentExistingPage);
});
