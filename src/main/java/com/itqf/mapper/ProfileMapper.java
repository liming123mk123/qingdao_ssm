package com.itqf.mapper;

import com.itqf.pojo.Profile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfileMapper {
    List<Profile> selectProfileAll();
    Profile selectId(int id);
    //int updateProfile(@Param("id")int id, String name, String birthday, String gender, String career, String address, String mobile, String picture);
    int updateProfile(Profile profile);
    int deleteProfile(int id);
    List<Profile>selectProfileList(int uid);
}
