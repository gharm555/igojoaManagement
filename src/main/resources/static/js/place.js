document.addEventListener('DOMContentLoaded', function () {
    let currentExistingPage = 0;
    let currentNewPage = 0;


    document.querySelectorAll('.img-fluid').forEach(function (img) {
        img.addEventListener('click', function () {
            // 해당 이미지에 대응하는 파일 입력 요소를 찾음
            const inputId = this.id + '-input';
            const fileInput = document.getElementById(inputId);

            if (fileInput) {
                fileInput.click(); // 파일 선택 창 열기
            }
        });
    });

    // 파일 선택 후 이미지 미리 보기 업데이트
    document.querySelectorAll('input[type="file"]').forEach(function (input) {
        input.addEventListener('change', function (event) {
            const file = event.target.files[0];

            if (file) {
                const reader = new FileReader();

                reader.onload = function (e) {
                    // 선택된 파일의 데이터 URL을 사용해 이미지 src 변경
                    const imgId = input.id.replace('-input', '');
                    const imgElement = document.getElementById(imgId);
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
            .then(function (response) {
                const existingPlacesList = document.getElementById('existing-places-list');
                const existingPlacesPagination = document.getElementById('existing-places-pagination');
                if (existingPlacesList) {
                    existingPlacesList.innerHTML = ''; // 기존 내용 비우기
                    response.data.content.forEach(function (place) {
                        const listItem = `
                <tr>
                    <td><a href="#" class="show-detail-btn" data-place-name="${place.placeName}" data-place-type="existing">${place.placeName}</a></td>
                    <td>${place.largeAddress} ${place.midiumAddress} ${place.smallAddress}</td>
                    <td><button type="button" class="btn btn-danger btn-sm delete-btn" data-place-name="${place.placeName}" >삭제</button></td>
                </tr>`;
                        existingPlacesList.insertAdjacentHTML('beforeend', listItem);
                    });

                    // 상세 정보 버튼 클릭 이벤트 설정
                    document.querySelectorAll('.show-detail-btn').forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            event.preventDefault();
                            const placeName = this.getAttribute('data-place-name');
                            loadPlaceDetails(placeName); // 기존 명소 로드
                        });
                    });
                    document.querySelectorAll('.delete-btn').forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            event.preventDefault();
                            const placeName = this.getAttribute('data-place-name');
                            deletePlace(placeName); // 비동기 승인 처리
                        });
                    });

                    updatePagination(existingPlacesPagination, response.data.totalPages, loadExistingPlaces, currentExistingPage);
                } else {
                    console.error('existing-places-list element not found');
                }
            })
            .catch(function (error) {
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
            .then(function (response) {
                const newPlacesList = document.getElementById('new-places-list');
                const newPlacesPagination = document.getElementById('new-places-pagination');
                if (newPlacesList) {
                    newPlacesList.innerHTML = ''; // 기존 내용 비우기
                    response.data.content.forEach(function (place) {
                        const listItem = `
                <tr>
                    <td><a href="#" class="show-detail-btn" data-place-name="${place.placeName}" data-reporter-id="${place.reporterId}" data-place-type="new">${place.placeName}</a></td>
                    <td>${place.reporterId}</td>
                    <td>${place.largeAddress} ${place.mediumAddress} ${place.smallAddress}</td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm approve-btn" data-place-name="${place.placeName}" data-reporter-id="${place.reporterId}" >승인</button>
                        <button type="button" class="btn btn-danger btn-sm confirmDelete-btn" data-place-name="${place.placeName}" data-reporter-id="${place.reporterId}">삭제</button>
                    </td>
                </tr>`;
                        newPlacesList.insertAdjacentHTML('beforeend', listItem);
                    });
                    document.querySelectorAll('.approve-btn').forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            event.preventDefault();
                            const placeName = this.getAttribute('data-place-name');
                            const reporterId = this.getAttribute('data-reporter-id');
                            approvePlace(placeName, reporterId); // 비동기 승인 처리
                        });
                    });


                    document.querySelectorAll('.confirmDelete-btn').forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            event.preventDefault();
                            const placeName = this.getAttribute('data-place-name');
                            const reporterId = this.getAttribute('data-reporter-id');
                            deleteConfirm(placeName, reporterId); // 비동기 승인 처리
                        });
                    });

                    // 상세 정보 버튼 클릭 이벤트 설정
                    document.querySelectorAll('.show-detail-btn').forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            event.preventDefault();
                            const placeName = this.getAttribute('data-place-name');
                            const reporterId = this.getAttribute('data-reporter-id');

                            loadConfirmPlaceDetails(placeName, reporterId);
                        });
                    });

                    updatePagination(newPlacesPagination, response.data.totalPages, loadNewPlaces, currentNewPage);
                } else {
                    console.error('new-places-list element not found');
                }
            })
            .catch(function (error) {
                console.error('Error fetching new places:', error);
            });
    }

    function approvePlace(placeName, reporterId) {
        console.log('Approving place:', placeName, 'Reporter ID:', reporterId);
        axios.post('/api/approvePlace', {
            placeName: placeName,
            reporterId: reporterId
        })
            .then(function (response) {
                alert('승인되었습니다!');
                loadNewPlaces(currentNewPage); // 페이지를 새로고침하여 업데이트된 내용을 반영
            })
            .catch(function (error) {
                console.error('Error approving place:', error);
                if (error.response && error.response.data) {
                    if (error.response.data.includes('이미 승인된 장소입니다')) {
                        alert('이미 승인된 장소입니다. 기존 정보를 확인해주세요.');
                    } else {
                        alert(error.response.data);
                    }
                } else {
                    alert('승인에 실패했습니다. 다시 시도해 주세요.');
                }
            });
    }


    function loadPlaceDetails(placeName) {
        axios.get(`/api/placeDetails/${placeName}`)
            .then(function (response) {
                const place = response.data;
                const fullAddress = `${place.largeAddress} ${place.mediumAddress} ${place.smallAddress}`;
                document.getElementById('modal-placeName').value = place.placeName;
                document.getElementById('modal-largeAddress').value = fullAddress;
                document.getElementById('modal-placeDescription').value = place.placeDescription;
                document.getElementById('modal-operatingHours').value = place.operatingHours;
                document.getElementById('modal-placeLatitude').value = place.placeLatitude;
                document.getElementById('modal-placeLongitude').value = place.placeLongitude;
                document.getElementById('modal-radius').value = place.placeRadius;

                setImageWithName('modal-firstImage', place.firstUrl, place.firstImageName);
                setImageWithName('modal-secondImage', place.secondUrl, place.secondImageName);
                setImageWithName('modal-thirdImage', place.thirdUrl, place.thirdImageName);

                // 2024-09-05 서상원 모달 부분 버그 고쳐봄 backdrop / keyboard 설정함
                // const modal = new bootstrap.Modal(document.getElementById('detailModal'));
                const modal = new bootstrap.Modal(document.getElementById('detailModal'));
                const reporterIdElement = document.getElementById('modal-reporterId');
                const reporterIdContainer = reporterIdElement.closest('.mb-3');

                if (place.reporterId) {
                    reporterIdElement.value = place.reporterId;
                    reporterIdContainer.style.display = 'block'; // 보여줍니다.
                } else {
                    reporterIdContainer.style.display = 'none'; // 숨깁니다.
                }


                modal.show();
            })
            .catch(function (error) {
                console.error('Error loading place details:', error);
            });
    }

    function setImageWithName(elementId, imageUrl, imageName) {
        const imgElement = document.getElementById(elementId);
        const imgNameElement = document.getElementById(`${elementId}-name`);

        if (imageUrl) {
            imgElement.src = imageUrl;
            imgElement.style.display = 'block';

            if (imgNameElement) {
                imgNameElement.textContent = imageName || 'No name provided';
                imgNameElement.style.display = 'block';
            }

            imgElement.onload = function () {
                console.log(`Image loaded successfully: ${imageUrl}`);
            };
            imgElement.onerror = function () {
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

    //  신규명소 모달에 값을 가져오기
    function loadConfirmPlaceDetails(placeName, reporterId) {
        if (!reporterId) {
            return;
        }


        axios.get(`/api/confirmPlaceDetails`, {
            params: {
                placeName: placeName,
                reporterId: reporterId
            }
        })
            .then(function (response) {
                let place = response.data;
                let fullAddress = `${place.largeAddress} ${place.mediumAddress} ${place.smallAddress}`;
                let reporterIdElement = document.getElementById('modal-reporterId');
                let reporterIdContainer = reporterIdElement.closest('.mb-3');

                document.getElementById('modal-reporterId').value = place.reporterId;
                document.getElementById('modal-placeName').value = place.placeName;
                document.getElementById('modal-largeAddress').value = fullAddress;
                document.getElementById('modal-placeDescription').value = place.placeDescription;
                document.getElementById('modal-operatingHours').value = place.operatingHours;
                document.getElementById('modal-placeLatitude').value = place.placeLatitude;
                document.getElementById('modal-placeLongitude').value = place.placeLongitude;
                document.getElementById('modal-radius').value = place.radius;
                document.getElementById('modal-oldFirstImage').textContent = place.firstUrl;
                setImageWithName('modal-firstImage', place.firstUrl, place.firstImageName);
                setImageWithName('modal-secondImage', place.secondUrl, place.secondImageName);
                setImageWithName('modal-thirdImage', place.thirdUrl, place.thirdImageName);


                // Reporter ID가 존재할 경우 보여주고, 그렇지 않을 경우 숨깁니다.
                if (place.reporterId) {
                    reporterIdElement.value = place.reporterId;
                    reporterIdContainer.style.display = 'block'; // 보여줍니다.
                } else {
                    reporterIdContainer.style.display = 'none'; // 숨깁니다.
                }

                // 2024-09-05 서상원 모달 부분 버그 고쳐봄 backdrop / keyboard 설정함
                // let modal = new bootstrap.Modal(document.getElementById('detailModal'));
                let modal = new bootstrap.Modal(document.getElementById('detailModal'));
                modal.show();
            })
            .catch(function (error) {
                console.error('Error loading confirm place details:', error);
            });
    }

    function deletePlace(placeName) {
        axios.delete('/api/placeDelete', {
            data: {placeName: placeName} // delete 요청에서는 data 속성을 사용하여 본문 데이터를 보냅니다.
        })
            .then(function (response) {
                alert('장소가 삭제되었습니다!');

                loadExistingPlaces(currentExistingPage);
            })
            .catch(function (error) {
                console.error('Error deleting place:', error);
                alert('삭제에 실패했습니다. 다시 시도해 주세요.');
            });
    }

    function deleteConfirm(placeName, reporterId) {
        axios.delete(`/api/confirmDelete`, {
            data: {
                placeName: placeName,
                reporterId: reporterId
            }
        })
            .then(function (reponse) {
                alert('장소가 삭제되었습니다!');
                loadNewPlaces(currentNewPage);
            })
            .catch(function (error) {
                console.error('Error deleting place:', error);
                alert('삭제에 실패했습니다. 다시 시도해 주세요.');
            });

    }

    // 이미지 선택 함수
    const $placeImgInputs = document.querySelectorAll('[id^="PlaceImgInput"]');
    $placeImgInputs.forEach((input, index) => {
        input.addEventListener("change", function (event) {
            try {
                if (this.files.length > 0) {
                    let fileName = this.files[0].name;
                    // document.getElementById(`file-name${index + 1}`).textContent = `파일 ${index + 1}: ${fileName}`;
                } else {
                    document.getElementById(`file-name${index + 1}`).textContent = "";
                }
            } catch (error) {
                console.error(`Error in file selection handler for input ${index + 1}:`, error);
            }
        });
    });
    // ------- 수창 작업 -------------------------------

    //수정버튼
    const $form = document.getElementById('placeDetailsForm');
    const $modifyBtn = document.querySelector("#modifyBtn");

    $modifyBtn.addEventListener('click', function (event) {
        event.preventDefault();

        const formData = new FormData($form);

        // FormData에서 largeAddress 값을 가져와 쪼개기
        const fullAddress = formData.get('largeAddress').split(' ');
        const largeAddress = fullAddress[0] || '';
        const mediumAddress = fullAddress[1] || '';
        const smallAddress = fullAddress.slice(2).join(' ') || '';

        formData.delete('largeAddress');

        // 쪼갠 주소 추가
        formData.append('largeAddress', largeAddress);
        formData.append('mediumAddress', mediumAddress);
        formData.append('smallAddress', smallAddress);

        const oldFirstUrl = document.getElementById('modal-oldFirstImage').textContent;
        formData.append('oldFirstUrl', oldFirstUrl);


        // // FormData 내용 로깅
        // for (let [key, value] of formData.entries()) {
        //     console.log(key, value);
        // }


        // 기존의 placeImage1, placeImage2, placeImage3를 제거
        formData.delete('placeImage1');
        formData.delete('placeImage2');
        formData.delete('placeImage3');

        // 선택된 모든 파일을 placeImages[]로 추가
        $placeImgInputs.forEach((input) => {
            if (input.files.length > 0) {
                formData.append('placeImages', input.files[0]);
            }
        });

        // // 모달에서 값들을 가져옵니다.
        // let updatedPlace = {
        //     reporterId: document.getElementById('modal-reporterId').value,
        //     placeName: document.getElementById('modal-placeName').value,
        //     largeAddress: largeAddress,
        //     mediumAddress: mediumAddress,
        //     smallAddress: smallAddress,
        //     placeDescription: document.getElementById('modal-placeDescription').value,
        //     operatingHours: document.getElementById('modal-operatingHours').value,
        //     placeLatitude: parseFloat(document.getElementById('modal-placeLatitude').value) || null,
        //     placeLongitude: parseFloat(document.getElementById('modal-placeLongitude').value) || null,
        //     radius:200,
        //     oldFirstUrl:document.getElementById('modal-oldFirstImage').textContent
        // };
        //
        // console.log("Sending updatedPlace:", JSON.stringify(updatedPlace, null, 2));

        axios.put('/api/updateConfirmPlace', formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            }
        })
            .then(function (response) {
                alert('장소 정보가 성공적으로 업데이트되었습니다.');

            })
            .catch(function (error) {
                alert('장소 정보 업데이트에 실패했습니다. 자세한 내용은 콘솔을 확인해주세요.');
            });
    });

// 수창작업 끝 --------------------------------------------


    // 페이지네이션 업데이트 함수
    function updatePagination(paginationElement, totalPages, loadFunction, currentPage) {
        paginationElement.innerHTML = ''; // 기존 페이징 비우기

        // 이전 페이지 버튼
        const prevButton = `<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                          </li>`;
        paginationElement.insertAdjacentHTML('beforeend', prevButton);

        // 페이지 번호 버튼
        for (let i = 0; i < totalPages; i++) {
            const pageItem = `
                <li class="page-item ${currentPage === i ? 'active' : ''}">
                    <a class="page-link" href="#">${i + 1}</a>
                </li>`;
            paginationElement.insertAdjacentHTML('beforeend', pageItem);
        }

        // 다음 페이지 버튼
        const nextButton = `<li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                          </li>`;
        paginationElement.insertAdjacentHTML('beforeend', nextButton);

        // 페이지 버튼 클릭 이벤트 설정
        const pageLinks = paginationElement.querySelectorAll('.page-link');
        pageLinks.forEach(function (link, index) {
            link.addEventListener('click', function (event) {
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
    const tabs = document.querySelectorAll('a[data-bs-toggle="pill"]');
    tabs.forEach(function (tab) {
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