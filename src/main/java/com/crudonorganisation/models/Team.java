package com.crudonorganisation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    private String teamName;
    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Member> members;
    @ManyToOne

    private  Employee manager;
    public Team() {
        this.members = new ArrayList<>();
    }
    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }
    public void removeMember(Member member){
        members.remove(member);
        member.setTeam(this);
    }
    public double getTotalTeamEffort() {
        int totalWorkingHours = 0;

        for (Member member : members) {
            totalWorkingHours += member.getTotalHoursWorked();
        }

        // Prevent division by zero
        int numberOfMembers = members.size();
        double averageWorkingHours = (numberOfMembers > 0) ? totalWorkingHours / (double) numberOfMembers : 0.0;

        return averageWorkingHours;
    }


}