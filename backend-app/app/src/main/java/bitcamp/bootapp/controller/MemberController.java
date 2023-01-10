package bitcamp.bootapp.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500, http://localhost:5500"})
public class MemberController {

  MemberDao memberDao = new MemberDao();

  public MemberController() {
    Member m = new Member();
    m.setNo(1);
    m.setName("이름입니다.");
    m.setTel("전화번호입니다.");
    m.setPostNo("1111");
    m.setBasicAddress("서울시");
    m.setDetailAddress("방");
    //    m.setWorking(0);
    //    m.setGender(0);
    //    m.setLevel(1);
    m.setCreatedDate("2023-01-10");

    memberDao.insert(m);
  }


  @PostMapping("/members")
  public Object addmembers(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) String createdDate){

    Member m = new Member();
    m.setNo(0);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    //    m.setWorking(0);
    //    m.setGender(0);
    //    m.setLevel(1);
    m.setCreatedDate(createdDate);

    this.memberDao.insert(m);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/members")
  public Object getMembers(){

    Member[] members = this.memberDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", members);

    return contentMap;
  }

  @GetMapping("/members/{memberNo}")
  public Object getBoard(@PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);
    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();

    if(m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 회원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", m);
    }
    return contentMap;
  }

  @PutMapping("/members/{memberNo}")
  public Object updateBoard(
      @PathVariable int memberNo,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) String createdDate) {

    Map<String, Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(memberNo);
    if(old == null || !old.getTel().equals(tel)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
      return contentMap;
    }

    Member m = new Member();
    m.setNo(memberNo);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setCreatedDate(createdDate);

    this.memberDao.update(m);

    // 응답 결과를 담을 맵 객체 준비
    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/members/{memberNo}")
  public Object deleteBoard(
      @PathVariable int memberNo,
      @RequestParam String tel) {

    Member m = this.memberDao.findByNo(memberNo);

    Map<String, Object> contentMap = new HashMap<>();

    if(m == null || !m.getTel().equals(tel)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.memberDao.delete(m);
      contentMap.put("status", "success");
    }
    return contentMap;
  }
}

