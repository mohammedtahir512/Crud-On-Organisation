package com.crudonorganisation.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponseDTO {
    private Long teamId;
    private String teamName;
    private Long managerId;
    private String managerName;

    // Constructors, getters, and setters

    public TeamResponseDTO(Long teamId, String teamName, Long managerId, String managerName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.managerId = managerId;
        this.managerName = managerName;
    }



    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
