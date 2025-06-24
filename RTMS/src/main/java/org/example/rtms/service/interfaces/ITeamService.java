package org.example.rtms.service.interfaces;


import org.example.rtms.dto.TeamDTO;
import org.example.rtms.exceptions.ApiException;

import java.util.List;

public interface ITeamService {

    //Get
    TeamDTO getTeamById(Long id)throws ApiException;
    List<TeamDTO> getAllTeams();
    List<TeamDTO> getTeamsByCityId(Long id)throws ApiException;

    //Post
    TeamDTO createTeam(TeamDTO teamDTO) throws ApiException;

    //Put
   TeamDTO updateTeamById(Long id, TeamDTO teamDTO) throws ApiException;

    //Delete
    TeamDTO deleteTeamById(Long id) throws ApiException;
}
