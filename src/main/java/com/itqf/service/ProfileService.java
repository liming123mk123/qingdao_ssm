package com.itqf.service;

import com.itqf.pojo.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> selectProfileAll();
    Profile selectId(int id);
    int updateProfile(Profile profile);
    int deleteProfile(int id);
    List<Profile>selectProfileList(int uid);
}
