package com.userlogin.mapper;

import com.userlogin.entity.User;
import com.userlogin.model.UserModel;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel modelFromEntity(User e);
    User entityFromModel(UserModel m);

    List<User> entityListOlustur(List<UserModel> modelList);

    @InheritConfiguration(name = "entityFromModel")
    @Mapping(ignore = true, target = "id")
    void entityGuncelle(UserModel model, @MappingTarget User entity);


    List<UserModel> modelListOlustur(List<User> entityList);


    List<UserModel> modelListOlustur(Iterable<User> entityList);
}
