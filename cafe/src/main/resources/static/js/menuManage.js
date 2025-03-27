$(document).ready(function() {

    let domain = "/" + $("body").attr("data-domain") + "/menu";

    // 부모 메뉴 클릭
    $('#menuList').on('click', '> li > div > span', function() {
        const menuItem = $(this).closest('li');
        const menuName = menuItem.data('menuname');
        const menuNo = menuItem.data('menuno');
        const menuRef = menuItem.data('menuref');
        const menuDepth = menuItem.data('menudepth');
        if (menuDepth != 0) {
            setMenuName(menuName, menuNo, menuRef)
        } else {
            setMenuGroup(menuName, menuNo);
        }
    });

    // 자식 메뉴 클릭
    $('#menuList').on('click', 'ul > li > span', function() {
        const menuItem = $(this).closest('li');
        const menuName = menuItem.data('menuname');
        const menuNo = menuItem.data('menuno');
        const menuRef = menuItem.data('menuref');
        setMenuName(menuName, menuNo, menuRef);
    });

    // 삭제 버튼 (부모/자식 메뉴 모두)
    $('#menuList').on('click', '.delete-btn', function(e) {
        e.stopPropagation();
        const menuItem = $(this).closest('li');
        const menuNo = menuItem.data('menuno');

        if (confirm("메뉴를 삭제하시겠습니까?")) {
            axios({
                url: domain + '/' + `${menuNo}`,
                method: 'DELETE'
            })
            .then(res => {
                if(res.data === 'success') {
                    alert('삭제 성공');
                    menuItem.remove();
                }
            }).catch(err => {
                console.log(err)
                alert('삭제 실패');
            });
        }
    });

    // 추가 버튼
    $('#add').on('click', function() {
        let menuName = $('#name').val()
        if (!menuName) {
            alert('메뉴명을 입력해 주세요.');
            return;
        }
        const menuData = {
            name: menuName,
            ref: $('#ref').val(),
            depth: 1
        };
        if (confirm("메뉴를 추가하시겠습니까?")) {
            axios({
                url: domain,
                method: 'PUT',
                data: menuData
            })
            .then(res => {
                if(res.data === 'success') {
                alert('추가 성공');
                location.reload();
            }
            }).catch(err => {
                console.log(err),
                alert('추가 실패');
            });
        }
    });

    // 그룹 추가 버튼
    $('#groupadd').on('click', function() {
        const menuName = $('#name').val();
        if (!menuName) {
            alert('그룹명을 입력해 주세요.');
            return;
        }
        const menuData = {
            name: menuName,
            ref: 0,
            depth: 0
        };
        if (confirm("그룹을 추가하시겠습니까?")) {
            axios ({
                url: domain,
                method: 'PUT',
                data: menuData
            }).then(res => {
                if(res.data === 'success') {
                    alert('추가 성공');
                    location.reload();
                }
            }).catch(err => {
                console.error('Error:', err);
                alert('추가 실패');
            });
        }
    });

    // 수정 버튼 클릭 시
    $('#edit').on('click', function() {
        const menuName = $('#name').val();
        const menuRef = $('#ref').val();
        if (menuRef != 0) {
            menuDepth = 1;
        }
        if (!menuName) {
            alert('메뉴명을 입력해 주세요.');
            return;
        }
        const menuData = {
            no: $('#no').val(),
            name: menuName,
            ref: menuRef
        };

        if (confirm("메뉴를 수정하시겠습니까?")) {
            axios({
                url: domain,
                method: 'Patch',
                data: menuData
            }).then(res => {
                if(res.data === 'success') {
                    alert('수정 성공');
                    location.reload();
                }
            }).catch(err => {
                console.error('Error:', err);
                alert('수정 실패');
            });
        }
    });

    // 그룹 만들기 버튼
    $('#group').on('click', function() {
        $('#name').val('');
        $('#no').val('');
        $('#ref').val(0);
        $('#ref').hide();
        $('#reflabel').hide();
        $('#add').hide();
        $('#groupadd').show();
        $('#edit').hide();
        $('#name').attr('placeholder', '그룹명을 입력하세요');
    });

    // 취소 버튼
    $("#reset").on("click", () => {
        document.location.href = domain.replace('/menu', '');
    });

    function setMenuName(menuName, menuNo, menuRef) {
        $("label[for='name']").text('메뉴명 :');
        $('#name').val(menuName);
        $('#no').val(menuNo);
        $('#ref').val(menuRef);
        $('#reflabel').show();
        $('#ref').show();
        $('#groupadd').hide();
        $('#add').hide();
        $('#edit').show();
    }

    function setMenuGroup(menuName, menuNo) {
        $("label[for='name']").text('그룹명 :');
        $('#name').val(menuName);
        $('#no').val(menuNo);
        $('#reflabel').hide();
        $('#ref').hide();
        $('#groupadd').hide();
        $('#add').hide();
        $('#edit').show();
    }
});