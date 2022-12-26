// 1.태그찾기
// 2.태그 만들기
function jQuery(selector) {
  if (selector.startsWith("<")){
    return document.createElement(selector.substring(1, selector.length -1));
  } else {
    return document.querySelectorAll(selector);
  }
}

var $ = jQuery;