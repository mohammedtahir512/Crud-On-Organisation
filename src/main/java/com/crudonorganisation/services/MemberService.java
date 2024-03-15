package com.crudonorganisation.services;

import com.crudonorganisation.exceptions.ResourceNotFoundException;
import com.crudonorganisation.models.Employee;
import com.crudonorganisation.models.Member;
import com.crudonorganisation.models.Team;
import com.crudonorganisation.repositories.EmployeeRepository;
import com.crudonorganisation.repositories.MemberRepository;
import com.crudonorganisation.repositories.TeamRepository;
import com.leadrat.crudonorganisation.models.*;
import com.leadrat.crudonorganisation.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {
    private MemberRepository memberRepository;
  private TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository,TeamRepository teamRepository,
                         EmployeeRepository employeeRepository){
        this.memberRepository=memberRepository;
        this.teamRepository=teamRepository;
        this.employeeRepository = employeeRepository;
    }
    public void addMember(Member member) {
       Team team=member.getTeam();
       team.addMember(member);
       memberRepository.save(member);
       teamRepository.save(team);
    }
    public Optional<Member> getMemberById(Long id){
        Optional<Member> member=memberRepository.findById(id);
        if(member.isPresent()){
            return  memberRepository.findById(id);
        }
        else throw new ResourceNotFoundException("Member not found with id: "+id);
    }


    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
    public Employee changeRole(Long id){
        Optional<Member> member= memberRepository.findById(id);
        if(member.isPresent()) {
            Member temp=member.get();
            memberRepository.deleteById(id);
            Employee newEmployee= new Employee();
            newEmployee.setSalary(temp.getSalary()+20000);
            newEmployee.setAge(temp.getAge());
            newEmployee.setName(temp.getName());
            newEmployee.setJoiningDate(temp.getJoiningDate());
            return employeeRepository.save(newEmployee);

        }
        else throw new ResourceNotFoundException("Member not found with id: "+id);


    }
    public void reassignMemberToDiffTeam(Long newteamId,Long memberId){
        Optional<Member> member=memberRepository.findById(memberId);
        Optional<Team> newTeam = teamRepository.findById(newteamId);
        Team curTeam=member.get().getTeam();
        if(member.get().getTeam().getTeamId()==newteamId && member.get().getReportsTo()==newTeam.get().getManager()){
            System.out.println("the member is already in the same team");
        }
        else {
            Member temp = member.get();
            curTeam.removeMember(member.get());
            teamRepository.save(curTeam);

            temp.setReportsTo(newTeam.get().getManager());
            newTeam.get().addMember(temp);
            memberRepository.save(temp);

            teamRepository.save(newTeam.get());
        }

    }


    public void deleteMemberById(Long id){
        Optional<Member> member=memberRepository.findById(id);
        if(member.isPresent()){
            memberRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Member not found with id: "+id);
    }



    }





