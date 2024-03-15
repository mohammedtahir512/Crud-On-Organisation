package com.crudonorganisation.controllers;
import com.crudonorganisation.models.Member;
import com.crudonorganisation.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        memberService.addMember(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id){
        Optional<Member> member=memberService.getMemberById(id);
        return new ResponseEntity<>(member,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        List<Member> members=memberService.getAllMembers();
        return new ResponseEntity<>(members,HttpStatus.OK);
    }

    @PostMapping("/changeRole/{id}")
    public ResponseEntity<Member> changeRoleOfMemberById(@PathVariable Long id){
        memberService.changeRole(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("reassign/{newTeamId}/{memberId}")
    public ResponseEntity<?> reassignMemberToDiffTeam(@PathVariable Long newTeamId,@PathVariable Long memberId){
        memberService.reassignMemberToDiffTeam(newTeamId,memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMemberById(@PathVariable Long id){
        memberService.deleteMemberById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
