package uz.ilmnajot.college.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.ilmnajot.college.Entity.College;
import uz.ilmnajot.college.exception.CollegeNotFoundException;
import uz.ilmnajot.college.exception.StudentNotFoundException;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.CollegeRequest;
import uz.ilmnajot.college.model.response.CollegeResponse;
import uz.ilmnajot.college.repository.CollegeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollegeService implements BaseService<CollegeRequest, Long>{

    private final CollegeRepository collegeRepository;
    private final ModelMapper modelMapper;

    public CollegeService(CollegeRepository collegeRepository, ModelMapper modelMapper) {
        this.collegeRepository = collegeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse create(CollegeRequest collegeRequest) {
        Optional<College> optionalCollege = collegeRepository.findCollegeByName(collegeRequest.getName());
        if (optionalCollege.isPresent()){
            return new ApiResponse("college is already exists", false);
        }
        College college = saveCollege(collegeRequest);
        CollegeResponse collegeResponse = modelMapper.map(college, CollegeResponse.class);
        return new ApiResponse("successfully saved college called "+ collegeResponse.getName(), true, collegeResponse);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()){
            College college = optionalCollege.get();
            CollegeResponse collegeResponse = modelMapper.map(college, CollegeResponse.class);
            return new ApiResponse("success", true, collegeResponse);
        }
            throw new CollegeNotFoundException("college not found");
    }

    @Override
    public ApiResponse getAll() {
        List<College> collegeList = collegeRepository.findAll(Sort.by("id"));
        List<CollegeResponse> collegeResponseList = new ArrayList<>();
        for (College college : collegeList) {
            CollegeResponse collegeResponse = modelMapper.map(college, CollegeResponse.class);
            collegeResponseList.add(collegeResponse);
        }
        return new ApiResponse("success", true, collegeResponseList);
    }

    @Override
    public ApiResponse getAll(int page, int size) {
        return null;
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            college.setDeleted(true);
            college.setBlocked(true);
            College savedCollege = collegeRepository.save(college);
            CollegeResponse collegeResponse = modelMapper.map(savedCollege, CollegeResponse.class);

            return new ApiResponse("Success", true, collegeResponse);
        }
        throw new CollegeNotFoundException("Could not find");
    }

    @Override
    public ApiResponse update(CollegeRequest collegeRequest, Long id) {
        Optional<College> collegeByNameAndId = collegeRepository.findCollegeByNameAndId(collegeRequest.getName(), id);
        if (collegeByNameAndId.isPresent()) {
            College college = collegeByNameAndId.get();
            college.setId(id);
            college.setName(collegeRequest.getName());
            college.setNumberOfFaculty(collegeRequest.getNumberOfFaculty());
            college.setRegion(collegeRequest.getRegion());
            college.setAddress(collegeRequest.getAddress());
            College savedCollege = collegeRepository.save(college);
            CollegeResponse collegeResponse = modelMapper.map(savedCollege, CollegeResponse.class);
            return new ApiResponse("success", true, collegeResponse);
        }
        throw new CollegeNotFoundException("Could not find");
    }
    public  College saveCollege(CollegeRequest collegeRequest){
        Optional<College> collegeByNameAndId = collegeRepository.findCollegeByName(collegeRequest.getName());
        if (collegeByNameAndId.isPresent()) {
            throw new CollegeNotFoundException("college is already registered");
        }
        College college = new College();
            college.setName(collegeRequest.getName());
            college.setNumberOfFaculty(collegeRequest.getNumberOfFaculty());
            college.setRegion(collegeRequest.getRegion());
            college.setAddress(collegeRequest.getAddress());
            return collegeRepository.save(college);
    }
}
