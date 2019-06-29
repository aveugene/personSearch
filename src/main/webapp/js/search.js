$(document).ready(function () {
    function collectParams() {
        var params = {};
        $(".searchparam").each(function () {
            if ($(this).val() !== "") {
                params[$(this).attr("id")] = $(this).val();
            }
        });
        return params;
    }

    function writeDiv(result) {
        $result = $('#result');
        $result.html('');
        $result.append(result);
    }

    function drawTable(json) {
        var table = "";

        if (json.length === 0) {
            table = 'Человек не найден.';
            writeDiv(table);
            return;
        }

        table += '' +
            '<table border="1" cellpadding="8" cellspacing="0">' +
            '            <tr>' +
            '                <th>Имя</th>' +
            '                <th>Фамилия</th>' +
            '                <th>Отчество</th>' +
            '                <th>Город</th>' +
            '                <th>Автомобиль</th>' +
            '            </tr>';

        $.each(json, function (personIndex, person) {
            table += '' +
                '                <tr>' +
                '                    <td>' + person["firstName"] + '</td>' +
                '                    <td>' + person["lastName"] + '</td>' +
                '                    <td>' + person["patronymic"] + '</td>' +
                '                    <td>' + person["city"]["name"] + '</td>' +
                '                    <td>' +
                '                        <table>';
            if (person["cars"][0]["model"] === undefined) {
                table += '                                    <td>Нет автомобиля</td>';

            } else {
                $.each(person["cars"], function (carIndex, car) {
                    table += '' +
                        '                                <tr>' +
                        '                                    <td>' + car["model"] + '</td>' +
                        '                                    <td>' + car["license"] + '</td>' +
                        '                                </tr>';
                });
            }
            table += '' +
                '                        </table>' +
                '                    </td>' +
                '                </tr>';
        });

        table += '' +
            '        </table>';
        writeDiv(table);
    }

    $("#search").click(function () {
        var params = collectParams();
        if (Object.keys(params).length !== 0) {
            var urlSearchParams = "";
            $.each(params, function (param, value) {
                urlSearchParams += '&' + param + '=' + value;
            });

            $.ajax({
                url: '/search?action=search' + urlSearchParams,
                success: function (json) {
                    drawTable(json);
                }
            });
        } else {
            writeDiv('<p style="color:red;">Введите один из параметров поиска</font> </p>')
        }
    });
    $("#showall").click(function () {
        $.ajax({
            url: '/search?action=all',
            success: function (json) {
                drawTable(json);
            }
        });
    });
});