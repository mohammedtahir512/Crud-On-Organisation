package com.crudonorganisation.services;


import com.crudonorganisation.exceptions.ResourceNotFoundException;
import com.crudonorganisation.models.Employee;
import com.crudonorganisation.models.Team;
import com.crudonorganisation.repositories.EmployeeRepository;
import com.crudonorganisation.repositories.TeamRepository;
import com.crudonorganisation.models.Member;
import com.crudonorganisation.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private  final EmployeeRepository employeeRepository;
    private MemberRepository memberRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository,
                       EmployeeRepository employeeRepository,
                       MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.memberRepository=memberRepository;

    }
    public void initializeTeams(){
        Team A= new Team();
        A.setTeamId(1L);
        A.setTeamName("Development");
        Optional<Employee> manager1=employeeRepository.findById(1L);
        A.setManager(manager1.get());
        List<Member> memberList = null;
        memberList.add(memberRepository.findById(1L).get());
        memberList.add(memberRepository.findById(2L).get());
        memberList.add(memberRepository.findById(3L).get());
        A.setMembers(memberList);

        Team B= new Team();
        B.setTeamId(2L);
        B.setTeamName("QA");
        Optional<Employee> manager2=employeeRepository.findById(2L);
        B.setManager(manager2.get());
        List<Member> memberList1 = null;
        memberList1.add(memberRepository.findById(4L).get());
        memberList1.add(memberRepository.findById(5L).get());
        memberList1.add(memberRepository.findById(6L).get());
        B.setMembers(memberList1);

        Team C= new Team();
        C.setTeamId(3L);
        C.setTeamName("Sales");
        Optional<Employee> manager3=employeeRepository.findById(3L);
        C.setManager(manager3.get());
        List<Member> memberList2 = null;
        memberList2.add(memberRepository.findById(7L).get());
        memberList2.add(memberRepository.findById(8L).get());
        memberList2.add(memberRepository.findById(9L).get());
        C.setMembers(memberList2);

        Team D= new Team();
        D.setTeamId(4L);
        D.setTeamName("Human Resource");
        Optional<Employee> manager4=employeeRepository.findById(4L);
        D.setManager(manager4.get());
        List<Member> memberList3 = null;
        memberList3.add(memberRepository.findById(10L).get());
        memberList3.add(memberRepository.findById(11L).get());
        D.setMembers(memberList3);

        Team E= new Team();
        E.setTeamId(5L);
        E.setTeamName("Marketing");
        Optional<Employee> manager5=employeeRepository.findById(5L);
        E.setManager(manager5.get());
        List<Member> memberList4 = null;
        memberList4.add(memberRepository.findById(12L).get());
        memberList4.add(memberRepository.findById(13L).get());
        E.setMembers(memberList4);

        teamRepository.saveAll(List.of(A,B,C,D,E));

    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long teamId) {
        return teamRepository.findById(teamId);
    }


    public List<Member> getMembersByTeamId(Long teamId){
        return teamRepository.findById(teamId).get().getMembers();
    }
    public Employee getTeamManager(Long teamId){
        return teamRepository.findById(teamId).get().getManager();
    }

    public void deleteTeam(Long teamId) {
        if (teamRepository.existsById(teamId)) {
            teamRepository.deleteById(teamId);
        } else {
            throw new ResourceNotFoundException("Team not found with id: " + teamId);
        }
    }
    public double getTeamTotalEfforts(Long teamId){
        Optional<Team> team=teamRepository.findById(teamId);
        if(team.isPresent()){
            return team.get().getTotalTeamEffort();

        }
        else throw new ResourceNotFoundException("Team not found with id: " + teamId);
    }
}
