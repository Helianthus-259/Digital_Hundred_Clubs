package com.szbt.studentserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Backboneevaluation;

/**
* @author 小壳儿
* @description 针对表【backboneevaluation】的数据库操作Service
* @createDate 2024-05-31 18:36:07
*/
public interface BackboneevaluationService extends IService<Backboneevaluation> {
    Object addBackBoneEvaluate(Backboneevaluation backboneevaluation);
}
