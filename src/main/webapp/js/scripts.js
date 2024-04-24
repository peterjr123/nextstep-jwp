String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answerWrite input[type=submit]").click(addAnswer);
$(".link-delete-article").click(deleteAnswer);

function deleteAnswer(e) {
    e.preventDefault();
    var form = $(this).closest(".form-delete");
    var queryString = form.serialize();
    var url = form.prop("action");
    console.log(url);
    $.ajax({
        type : 'post',
        url : url,
        data : queryString,
        error : onError,
        success : (json, status) => {
            if(json.status == true) {
                $(this).closest("article").remove();
            }
        }
    })
}


function addAnswer(e) {
    e.preventDefault();
    var queryString = $("form[name=answer]").serialize();
    var url = $("form[name=answer]").prop("action");
    $.ajax({
        type : 'post',
        url : url,
        data : queryString,
        dataType : 'json',
        error : onError,
        success : onSuccess
    });
}
function onSuccess(json, status) {
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId);
    $(".qna-comment-slipp-articles").prepend(template);
}

function onError(json, statua) {
}

