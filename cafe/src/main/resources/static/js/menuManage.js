$(document).ready(function() {

    const domain = $('body').data('domain');

    $('#menuList').on('click', '> li > div > span', function() {
        const menuItem = $(this).closest('li');
        const menuName = menuItem.data('menuname');
        const menuNo = menuItem.data('menuno');
        const menuRef = menuItem.data('menuref');
        console.log(menuRef)
        setMenuName(menuName, menuNo, menuRef);
    });

    // 자식 메뉴 클릭
    $('#menuList').on('click', 'ul > li > span', function() {
        const menuItem = $(this).closest('li');
        const menuName = menuItem.data('menuname');
        const menuNo = menuItem.data('menuno');
        const menuRef = menuItem.data('menuref');
        console.log(menuRef)
        setMenuName(menuName, menuNo, menuRef);
    });

    // 삭제 버튼 (부모/자식 메뉴 모두)
    $('#menuList').on('click', '.delete-btn', function(e) {
        e.stopPropagation();
        const menuItem = $(this).closest('li');
        const menuNo = menuItem.data('menuno');

        if (confirm("이 메뉴를 삭제하시겠습니까?")) {
            $.ajax({
                url: `/${domain}/menu/delete`,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ no: menuNo })
            })
            .done(function(response) {
                if (response === 'success') {
                    alert('삭제 성공');
                    menuItem.remove();
                } else {
                    alert('삭제 실패');
                }
            })
            .fail(function(error) {
                console.error('Error:', error);
                alert('삭제 실패');
            });
        }
    });

    // 추가 버튼
    $('#add').on('click', function() {
        const menuName = $('#name').val();
        const menuRef = $('#ref').val();
        let menuDepth = 0;
        if (menuRef != 0) {
            menuDepth = 1;
        }
        if (!menuName) {
            alert('메뉴명을 입력해 주세요.');
            return;
        }
        const menuData = {
            name: menuName,
            ref: menuRef,
            depth: menuDepth
        };

        console.log(menuData);

        $.ajax({
            url: `/${domain}/menu/create`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(menuData)
        })
        .done(function(response) {
            alert('추가 성공');
            location.reload();
        })
        .fail(function(error) {
            console.error('Error:', error);
            alert('추가 실패');
        });
    });

    // 수정 버튼 클릭 시
    $('#edit').on('click', function() {
        const menuNo = $('#no').val();
        const menuName = $('#name').val();
        const menuRef = $('#ref').val();
        let menuDepth = 0;
        if (menuRef != 0) {
            menuDepth = 1;
        }
        if (!menuName) {
            alert('메뉴명을 입력해 주세요.');
            return;
        }
        const menuData = {
            no: menuNo,
            name: menuName,
            ref: menuRef
        };
        console.log(menuData);

        $.ajax({
            url: `/${domain}/menu/edit`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(menuData)
        })
        .done(function(response) {
            alert('수정 완료');
            location.reload();
        })
        .fail(function(error) {
            console.error('Error:', error);
            alert('수정 실패');
        });
    });

    // 취소 버튼
    $('#reset').on('click', function() {
        setToAddMode();
    });

    function setMenuName(menuName, menuNo, menuRef) {
        $('#name').val(menuName);
        $('#no').val(menuNo);
        $('#ref').val(menuRef);
        $('#add').hide();
        $('#edit').show();
    }

    function setToAddMode() {
        $('#name').val('');
        $('#no').val('');
        $('#ref').val(0);
        $('#add').show();
        $('#edit').hide();
    }
    
});
