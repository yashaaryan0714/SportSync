package org.example.rtms.service;

import org.example.rtms.Mapper.StadiumMapper;
import org.example.rtms.dto.StadiumDTO;
import org.example.rtms.exceptions.AlreadyExistException.StadiumAlreadyExist;
import org.example.rtms.exceptions.ApiException;
import org.example.rtms.exceptions.NotFoundExceptions.StadiumNotFoundException;
import org.example.rtms.model.entity.Stadium;
import org.example.rtms.repository.StadiumRepository;
import org.example.rtms.service.interfaces.IStadiumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumServiceImp implements IStadiumService {

    //Dependency inyection
    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;
    public StadiumServiceImp(StadiumRepository stadiumRepository, StadiumMapper stadiumMapper){
        this.stadiumRepository = stadiumRepository;
        this.stadiumMapper = stadiumMapper;
    }

    //GET
    @Override
    public List<StadiumDTO> getAllStadiums() {
        List<Stadium> stadiums = stadiumRepository.findAll();
        return stadiumMapper.stadiumListToStadiumDTOList(stadiums);
    }


    //GET
    @Override
    public StadiumDTO getStadiumById(Long id) throws ApiException {
        Stadium stadium = stadiumRepository.findById(id).orElseThrow(() -> new StadiumNotFoundException(id));
        return stadiumMapper.stadiumToStadiumDTO(stadium);
    }

    //POST
    @Override
    public StadiumDTO createStadium(StadiumDTO stadiumDTO) throws ApiException {
        // Converts StadiumDTO to Stadium
        Stadium stadium = stadiumMapper.stadiumDTOToStadium(stadiumDTO);

        Optional<Stadium> optionalStadium = stadiumRepository.findByName(stadium.getName());

        if(optionalStadium.isPresent()){
            throw new StadiumAlreadyExist(optionalStadium.get().getName());
        }

        // Asign stadiumDTO parameters
        stadium.setName(stadiumDTO.getName());
        stadium.setCapacity(stadiumDTO.getCapacity());

        // Saves stadium in DB
        stadiumRepository.save(stadium);
        return stadiumMapper.stadiumToStadiumDTO(stadium);
    }

    //PUT
    @Override
    public StadiumDTO updateStadiumById(Long id, StadiumDTO stadiumDTO) throws ApiException {
        Optional<Stadium> optionalStadium = stadiumRepository.findById(id);

        if (optionalStadium.isPresent()) {
            Stadium existingStadium = optionalStadium.get();

            if (stadiumDTO.getName() != null) {
                existingStadium.setName(stadiumDTO.getName());
            }
            if (stadiumDTO.getCapacity() > 0) {
                existingStadium.setCapacity(stadiumDTO.getCapacity());
            }
            if (stadiumDTO.getCapacity() > 0 || stadiumDTO.getName() != null) {
                stadiumRepository.save(existingStadium);
                return stadiumMapper.stadiumToStadiumDTO(existingStadium);
            }
        }
        throw new StadiumNotFoundException(id);
    }

    //DELETE
    @Override
    public StadiumDTO deleteStadiumById(Long id) throws ApiException {
        //Verifies if stadium exists, if not, throws StadiumNotFoundException
        Optional<Stadium> stadiumOptional = stadiumRepository.findById(id);
        if(stadiumOptional.isPresent()){
            //Deletes stadium from DB
            stadiumRepository.delete(stadiumOptional.get());
            return stadiumMapper.stadiumToStadiumDTO(stadiumOptional.get());
        }
        throw new StadiumNotFoundException(id);
    }

}
