const searchButton = document.getElementById('search-btn');

if (searchButton) {
    searchButton.addEventListener('click', event => {
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
    //토큰
    const token = searchParam('token')

    if (token) {
        localStorage.setItem("access_token", token)
    }

    function searchParam(key) {
        return new URLSearchParams(location.search).get(key);
    }

    document.getElementById('logout').addEventListener('click', function(event) {
        event.preventDefault(); // 기본 동작인 링크 이동을 막음
        localStorage.removeItem("access_token");
        document.cookie = "refresh_token=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
        window.location.href = '/';
    });