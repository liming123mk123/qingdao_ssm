package com.itqf.service;

import com.itqf.mapper.ProfileMapper;
import com.itqf.pojo.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileMapper profileMapper;
    public List<Profile> selectProfileAll() {
        return profileMapper.selectProfileAll();
    }

    public Profile selectId(int id) {
        return profileMapper.selectId(id);
    }

    public int updateProfile(Profile profile) {
        return profileMapper.updateProfile(profile);
    }

    public int deleteProfile(int id) {
        return profileMapper.deleteProfile(id);
    }

    public List<Profile> selectProfileList(int uid) {
        return profileMapper.selectProfileList(uid);
    }
}
