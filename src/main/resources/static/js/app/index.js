var index = {
    init : function () {
        var _this = this;
        $('#btn-update').hide();
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-find').on('click', function () {
            _this.find();
        });
        $('#btn-to-update').on('click', function () {
            _this.toUpdate();
        })
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    toUpdate : function () {
        var id = $('#id').val();
        $('#title').removeAttr('readonly');
        $('#content').removeAttr('readonly');
        $('#btn-update').show();
       // window.location.href = '/posts/update/'+id;
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    find : function () {
        var keyword = $('#find_keyword').val();
        // var target = document.getElementById("category");
        // var category = target.options[target.selectedIndex].value;
        var category = $('#category option:selected').val();
        console.log("keyword : " + keyword);
        if (!keyword) {
            alert("검색어를 입력하세요");
        }
        else {

        // 1. 특정 데이터를 가져오고 => 데이터를 화면에 뿌린다
        // 2. 특정 데이터를 화면에 담아서 그 화면으로 바로 이동한다.
        $.ajax({
            type: 'GET',
            url: '/api/v1/posts/' + category + '/' + keyword,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (data) {
            console.log('글이 검색었습니다. : ', data);
            // 1. tbody 안에 잇는 애들을 날려버린다
            $('#tbody').empty();
            // 2. tbody 안에 적절한 모양으로 data 에 있는 애들을 for loop를 통해 한줄씩 잘 그린다.
            for (var i in data) {

                var rowData = '<tr></tr><td>' + data[i].id + '</td>'
                    + '<td><a href=/posts/content/' + data[i].id + '>' + data[i].title + '</a></td>'
                    + '<td>' + data[i].author + '</td>'
                    + '<td>' + data[i].modifiedDate + '</td></tr>'
                $('#tbody:last').append(rowData);

                console.log('rowData:' + rowData);

                console.log(data[i].id);
                console.log(data[i].title);
                console.log(data[i].author);
                console.log(data[i].modifiedDate);
            }
            // window.location.href = '/posts/search/' + category +'/' + keyword;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    }
};

index.init();
