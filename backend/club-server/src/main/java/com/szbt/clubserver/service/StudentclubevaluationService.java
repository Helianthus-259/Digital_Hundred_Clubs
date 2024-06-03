package com.szbt.clubserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Studentclubevaluation;

/**
* @author 小壳儿
* @description 针对表【studentclubevaluation】的数据库操作Service
* @createDate 2024-06-02 23:31:05
*/
public interface StudentclubevaluationService extends IService<Studentclubevaluation> {
    Object  clubEvaluationForm(Studentclubevaluation studentclubevaluation);
}
