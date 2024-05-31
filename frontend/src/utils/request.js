import axios from "axios";
import eventEmitter from "./eventEmitter";
import { APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum } from "@/Enum";

const instance = axios.create({
    timeout: 5000
})

instance.interceptors.request.use(
    config => {
        // 设置请求头
        const token = sessionStorage.getItem('token')
        if (token) {
            config.headers.Authorization = token
        }
        return config // 返回配置
    },
    error => {
        return Promise.reject(error) // 返回错误信息
    }
)

const errorHandler = error => {
    if (error.response.status === 1000) {
        eventEmitter.emit(APIEventEnum.incorrectInput, error.response.data.msg)
    } else if (error.response.status === 1001) {
        eventEmitter.emit(APIEventEnum.registerError, error.response.data.msg)
    }
}

function successHandler(response) {
    if (response.status === 200) {
        if (response.data.code === 1) { // 登录成功
            const { token, studentId } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setInit, { token, studentId })
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'firstPageTabs', value: 'home' })
            eventEmitter.emit(APIEventEnum.loginSuccess)
        } else if (response.data.code === 2) { // 获取社团信息成功
            const { data } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubsData, data)
            eventEmitter.emit(APIEventEnum.getClubsInfoSuccess, data)
        } else if (response.data.code === 3) { // 获取用户信息成功
            const { data } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, data)
            eventEmitter.emit(APIEventEnum.getUserInfoSuccess, data)
        } else if (response.data.code === 4) { // 更新用户信息成功
            eventEmitter.emit(APIEventEnum.postUserInfoSuccess)
        } else if (response.data.code === 5) { // 新建社团申请成功
            eventEmitter.emit(APIEventEnum.postNewClubApplySuccess)
        } else if (response.data.code === 6) { // 获取社团活动通知成功
            const { clubId, activities, notices } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setclubsActAndNtc, { clubId, activities, notices })
            eventEmitter.emit(APIEventEnum.getClubActAndNtcSuccess, { activities, notices })
        } else if (response.data.code === 7) { // 获取社团简介成功
            const { clubDescription } = response.data
            eventEmitter.emit(APIEventEnum.getClubIntroductionSuccess, clubDescription)
        } else if (response.data.code === 8) { // 获取社团成员成功
            const { members } = response.data
            eventEmitter.emit(APIEventEnum.getClubMembersSuccess, members)
        } else if (response.data.code === 9) { // 管理员登录成功
            //console.log("管理员登录：request部分成功")
            const { token, adminId } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setInit, { token, adminId })
            eventEmitter.emit(RouterEventEnum.push, "/adminFirstPage")//登录成功后应直接去往管理员首页
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'adminFirstPageTabs', value: 'admin' })
        } else if (response.data.code === 10) { // 获取管理员信息成功
            const { data } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, data)//这里的设计是，管理员信息也同样放在userinfo中，可以考虑改进
            eventEmitter.emit(APIEventEnum.getAdminInfoSuccess, data)
        } else if (response.data.code === 11) { // 管理员信息更新成功
            console.log('更新管理员/社团信息成功');
        } else if (response.data.code === 12) { // 获取活动信息成功
            const { activity } = response.data
            eventEmitter.emit(APIEventEnum.getActivityInfoSuccess, activity)
        } else if (response.data.code === 13) { // 获取所有活动信息成功
            eventEmitter.emit(APIEventEnum.getActivitiesInfoSuccess, response.data)
        } else if (response.data.code === 14) { // 获取社团活动列表成功
            const { activityList } = response.data
            eventEmitter.emit(APIEventEnum.getClubActivityListSuccess, activityList)
        } else if (response.data.code === 15) { // 获取社团申请列表成功
            const { applyList } = response.data
            eventEmitter.emit(APIEventEnum.getClubApplyListSuccess, applyList)
        } else if (response.data.code === 16) { // 同意社团申请成功
            const { studentId } = response.data
            eventEmitter.emit(APIEventEnum.postAgreeClubApplySuccess, studentId)
        } else if (response.data.code === 17) { // 拒绝社团申请成功
            const { studentId } = response.data
            eventEmitter.emit(APIEventEnum.postRejectClubApplySuccess, studentId)
        } else if (response.data.code === 18) { // 发布新活动成功
            eventEmitter.emit(APIEventEnum.postNewActivitySuccess)
        } else if (response.data.code === 19) { // 社团骨干评优成功
            eventEmitter.emit(APIEventEnum.postBackBoneEvaluateSuccess)
        } else if (response.data.code === 20) { // 获取社团评优所需信息成功
            eventEmitter.emit(APIEventEnum.getClubEvaluateInfoSuccess, response.data)
        } else if (response.data.code === 21) { // 代表上传图片成功
            const { image } = response.data
            eventEmitter.emit(APIEventEnum.uploadImageSuccess, image)
        } else if (response.data.code === 22) { // 删除社团干部成功
            eventEmitter.emit(APIEventEnum.postDeleteClubMemberSuccess)
        } else if (response.data.code === 23) { // 更新社团干部成功
            eventEmitter.emit(APIEventEnum.postUpdateClubMemberSuccess)
        } else if (response.data.code === 24) { // 添加社团干部成功
            eventEmitter.emit(APIEventEnum.postAddClubMemberSuccess)
        } else if (response.data.code === 25) { // 更新社团信息成功
            eventEmitter.emit(APIEventEnum.postUpdateClubInfoSuccess)
        } else if (response.data.code === 26) { // 获取骨干评估申请
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getBackBoneEvaluationsSuccess, data)
        } else if (response.data.code === 27) {  // 获取社团年审
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getClubAnnualsSuccess, data)
        } else if (response.data.code === 28) {  // 获取社团评优
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getClubEvaluationsSuccess, data)
        } else if (response.data.code === 29) {  // 提交个人绩效成功
            eventEmitter.emit(APIEventEnum.postPersonalPerformanceSuccess)
        } else if (response.data.code === 30) {  // 提交活动绩效成功
            eventEmitter.emit(APIEventEnum.postActivityPerformanceSuccess)
        } else if (response.data.code === 31) { // 获取最新活动成功
            const { activities } = response.data
            eventEmitter.emit(APIEventEnum.getLatestActivitiesSuccess, activities)
        } else if (response.data.code === 32) { // 获取十佳社团成功
            const { clubs } = response.data
            eventEmitter.emit(APIEventEnum.getTopTenClubsSuccess, clubs)
        } else if (response.data.code === 33) { // 上传文件成功
            const { file } = response.data
            eventEmitter.emit(APIEventEnum.uploadFileSuccess, file)
        } else if (response.data.code === 34) { // 提交社团年审表成功
            eventEmitter.emit(APIEventEnum.postClubAnnualAuditFormSuccess)
        } else if (response.data.code === 35) { // 提交社团评优表成功
            eventEmitter.emit(APIEventEnum.postClubEvaluationFormSuccess)
        }
    }
}

instance.interceptors.response.use(
    response => {
        successHandler(response)
        return response
    },
    error => {
        errorHandler(error)
    }
)
export default instance;