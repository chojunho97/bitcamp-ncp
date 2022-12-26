// 1.태그찾기
// 2.태그 만들기
// 3.append()
// 4.리팩토링
// 5.html()
//6. on()
function jQuery(selector) {
  let el= [];
  if (selector.startsWith("<")){
    el[0] = document.createElement(selector.substring(1, selector.length -1));
    
  } else {
    let nodelist = document.querySelectorAll(selector);
    for(let e of nodelist) {
     el.push(e);
    }

  }
  el.append = function(childBox) {
    // 자식 태그를 복제해서 각 부모 태그에 붙인다.
    for(let parent of el) {
      for(let child of childBox) {
        parent.appendChild(child.cloneNode(true));
      }
    }

    // 자식 태그는 제거한다.
    for(let child of childBox) {
      if(child.parentElement != null || child.parentElement != undefined) {
        child.parentElement.removeChild(child);
      }
    }
  };

  el.html = function(content) {
    for(let e of el) {
      e.innerHTML = content; 
    }
  };
  el.on = function(eventName, listener) {
    for(let e of el) {
      e.addEventListener(eventName, listener);
    }
  };
  return el;
}

var $ = jQuery;