// script.js 파일 내용
//토큰
const token = searchParam('token')

if (token) {
    localStorage.setItem("access_token", token)
}

function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
}

//찾기
const searchButton = document.getElementById('search-btn');

document.getElementById('logout').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작인 링크 이동을 막음
    localStorage.removeItem("access_token");
    document.cookie = "refresh_token=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    window.location.href = '/';
});

if (searchButton) {
    searchButton.addEventListener('click', event => {
        event.preventDefault(); // 이벤트의 기본 동작을 막음

        var searchCategory = document.getElementById('searchCategory').value;
        var searchText = document.getElementById('searchText').value;

        fetch(`/books?${searchCategory}=${searchText}`, {
            method: 'GET'
        })
        .then(() => {
            location.replace(`/books?${searchCategory}=${searchText}`);
        });
    });
}
