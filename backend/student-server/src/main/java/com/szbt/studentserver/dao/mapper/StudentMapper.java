package com.szbt.studentserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Student;
/**
* @author 小壳儿
* @description 针对表【student】的数据库操作Mapper
* @createDate 2024-04-23 14:12:37
* @Entity generator.domain.Student
*/
@Mapper
public interface StudentMapper extends MPJBaseMapper<Student> {

}




