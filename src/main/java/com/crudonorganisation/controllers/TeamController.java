package com.crudonorganisation.controllers;

import com.crudonorganisation.models.Member;
import com.crudonorganisation.models.Team;
import com.crudonorganisation.repositories.MemberRepository;
import com.crudonorganisation.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final MemberRepository memberRepository;

    @Autowired
    public TeamController(TeamService teamService,
                          MemberRepository memberRepository) {
        this.teamService = teamService;
        this.memberRepository = memberRepository;
    }
    @PostMapping("/initialize")
    public void intialize(){
        teamService.initializeTeams();
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);

    }

    @GetMapping("/members/{teamId}")
    public ResponseEntity<?> getMembersByTeamId(@PathVariable Long teamId){
       List<Member> members=teamService.getTeamById(teamId).get().getMembers();
       return new ResponseEntity<>(members,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
        Optional<Team> team = teamService.getTeamById(teamId);

        return team.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/manager/{teamId}")
    public ResponseEntity<?> getManagerByTeamId(@PathVariable Long teamId){
        Optional<Team>  team=teamService.getTeamById(Long.valueOf(teamId));
        if(team.isPresent()) return new ResponseEntity<>(team.get().getManager(),HttpStatus.OK);
        else{
            String error="no team with id:  "+teamId;
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/totalEfforts/{id}")
    public ResponseEntity<?>  getTeamTotalEfforts(@PathVariable Long id){
        double ans= teamService.getTeamTotalEfforts(id);
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }



    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
