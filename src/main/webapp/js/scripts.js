// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").on("click", addAnswer);
$(document).ready(function() {
  // 버튼을 빨간색으로 변경
  $(".delete-answer-btn").on("click", deleteAnswer);
});

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();
  var url = $("form[name=answer]").attr("action");

  console.log(queryString);

  $.ajax({
    type : 'post',
    url : url,
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);

  var countElement = $(".qna-comment-count").children().first();
  countElement.text(Number(countElement.text())+1);
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};



function deleteAnswer(e) {
  e.preventDefault();

  var queryString = $(e.target).closest("form").serialize();
  var url = $("form[name=delete-answer]").attr("action");
  var answerDomElement = $(e.target).closest("article");
  console.log(url);

  $.ajax({
    type : 'post',
    url : url,
    data : queryString,
    dataType : 'json',
    error: onDeleteError,
    success : function(json, status) {
      onDeleteSuccess(json, status, answerDomElement)
    },
  });
}

function onDeleteSuccess(json, status, element){
  element.remove();
  var countElement = $(".qna-comment-count").children().first();
  countElement.text(Number(countElement.text())-1);
}

function onDeleteError(xhr, status) {
  alert("error");
}