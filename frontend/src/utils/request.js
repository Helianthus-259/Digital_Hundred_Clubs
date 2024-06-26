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

function successHandler(res) {
    console.log(res)
    if (res.status !== 200) {
        console.log("服务器出错!")
        return
    }
    let response = res.data.status ? res.data : res
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
            const clubDescription = response.data.clubDescription
            eventEmitter.emit(APIEventEnum.getClubIntroductionSuccess, clubDescription)
        } else if (response.data.code === 8) { // 获取社团成员成功
            const { members } = response.data
            eventEmitter.emit(APIEventEnum.getClubMembersSuccess, members)
        } else if (response.data.code === 9) { // 管理员登录成功
            const { token, adminId } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setAdminInit, { token, adminId })
            eventEmitter.emit(RouterEventEnum.push, "/adminFirstPage")//登录成功后应直接去往管理员首页
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'adminFirstPageTabs', value: 'admin' })
            eventEmitter.emit(APIEventEnum.postAdminLoginSuccess)
        } else if (response.data.code === 10) { // 获取管理员信息成功
            const { data } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, data)//这里的设计是，管理员信息也同样放在userinfo中，可以考虑改进
            eventEmitter.emit(APIEventEnum.getAdminInfoSuccess, data)
        } else if (response.data.code === 11) { // 管理员信息更新成功
            console.log('更新管理员/社团信息成功');
        } else if (response.data.code === 12) { // 获取活动信息成功
            const activity = response.data.data
            eventEmitter.emit(APIEventEnum.getActivityInfoSuccess, activity)
        } else if (response.data.code === 13) { // 获取所有活动信息成功
            eventEmitter.emit(APIEventEnum.getActivitiesInfoSuccess, response.data)
        } else if (response.data.code === 14) { // 获取社团活动列表成功
            const activityList = response.data.activityList
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
            const activities = response.data.data
            eventEmitter.emit(APIEventEnum.getLatestActivitiesSuccess, activities)
        } else if (response.data.code === 32) { // 获取十佳社团成功
            const clubs = response.data.data
            eventEmitter.emit(APIEventEnum.getTopTenClubsSuccess, clubs)
        } else if (response.data.code === 33) { // 上传文件成功
            const file = response.data
            eventEmitter.emit(APIEventEnum.uploadFileSuccess, file)
        } else if (response.data.code === 34) { // 提交社团年审表成功
            eventEmitter.emit(APIEventEnum.postClubAnnualAuditFormSuccess)
        } else if (response.data.code === 35) { // 提交社团评优表成功
            eventEmitter.emit(APIEventEnum.postClubEvaluationFormSuccess)
        } else if (response.data.code === 36) { // 获取社团通知列表成功
            const notices = response.data.data
            eventEmitter.emit(APIEventEnum.getClubNoticeListSuccess, notices)
        } else if (response.data.code === 37) { // 发布新通知成功
            eventEmitter.emit(APIEventEnum.postNewNoticeSuccess)
        } else if (response.data.code === 38) { // 发布新会议成功
            eventEmitter.emit(APIEventEnum.postNewMeetingSuccess)
        } else if (response.data.code === 39 || !response.data.code) { // 获取图片验证码(二进制数据拿不到code)
            const imageUrl = response.data
            eventEmitter.emit(APIEventEnum.getImageVerifyCodeSuccess, imageUrl)
        }
        else if (response.data.code === 40) { // 更新社团简介成功
            eventEmitter.emit(APIEventEnum.postUpdateClubDescriptionSuccess)
        } else if (response.data.code === 41) { // 获取枚举列表成功
            const { data } = response.data
            eventEmitter.emit(APIEventEnum.getEnumListSuccess, data)
            if (localStorage.getItem('enumList') !== JSON.stringify(data)) {
                localStorage.setItem('enumList', JSON.stringify(data))
            }
        } else if (response.data.code === 42) { // 获取特定社团骨干评优记录成功
            const backboneExamData = response.data.returnData;
            eventEmitter.emit(APIEventEnum.getMyClubBackboneExamDataSuccess, backboneExamData)
        } else if (response.data.code === 43) { // 获取特定社团年审记录成功
            const annualExamData = response.data.returnData;
            eventEmitter.emit(APIEventEnum.getMyClubAnnualExamDataSuccess, annualExamData)
        } else if (response.data.code === 44) { // 获取特定社团教师评优记录成功
            const teacherExamData = response.data.returnData;
            eventEmitter.emit(APIEventEnum.getMyClubTeacherExamDataSuccess, teacherExamData)
        } else if (response.data.code === 45) { // 获取社团获奖信息
            const associationAwards = response.data.data
            eventEmitter.emit(APIEventEnum.getAssociationAwardsSuccess, associationAwards)
        } else if (response.data.code === 46) { // 获取社团会议
            const meetings = response.data.data
            eventEmitter.emit(APIEventEnum.getMeetingsSuccess, meetings)
        } else if (response.data.code === 47) { // 加入社团申请成功
            eventEmitter.emit(APIEventEnum.postJoinClubSuccess)
        } else if (response.data.code === 48) { // 报名活动成功
            eventEmitter.emit(APIEventEnum.postJoinActivitySuccess)
        } else if (response.data.code === 49) {   // 获取建立社团申请详细信息成功
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getClubApprovalSuccess, data)
        } else if (response.data.code === 50) {   // 获取社团年审详细信息成功
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getClubAnnualSuccess, data)
        } else if (response.data.code === 51) {  // 获取社团详细评优信息成功
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getClubAwardInfoSuccess, data)
        } else if (response.data.code === 52) {  // 获取社团详细信息成功
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getClubInfoSuccess, data)
        } else if (response.data.code === 53) {  // 通过建立社团请求成功
            console.log("success")
            eventEmitter.emit(APIEventEnum.passClubApprovalSuccess)
        } else if (response.data.code === 54) {  // 驳回建立社团请求成功
            console.log("failed")
            eventEmitter.emit(APIEventEnum.unPassClubApprovalSuccess)
        } else if (response.data.code === 55) {  // 通过活动请求成功
            console.log("success")
            eventEmitter.emit(APIEventEnum.passActivityApprovalSuccess)
        } else if (response.data.code === 56) {  // 驳回活动请求成功
            console.log("failed")
            eventEmitter.emit(APIEventEnum.unPassActivityApprovalSuccess)
        } else if (response.data.code === 57) {  // 通过骨干评优审核成功
            console.log("success")
            eventEmitter.emit(APIEventEnum.passBackboneAwardsReviewSuccess)
        } else if (response.data.code === 58) {  // 驳回骨干评优审核成功
            console.log("failed")
            eventEmitter.emit(APIEventEnum.unPassBackboneAwardsReviewSuccess)
        } else if (response.data.code === 59) {  // 获取今年社团评优信息成功
            console.log(response.data)
            const data = response.data.data
            eventEmitter.emit(APIEventEnum.getThisYearClubAnnualSuccess, data)
        } else if (response.data.code === 60) {  // 通过社团年审成功
            console.log("success")
            eventEmitter.emit(APIEventEnum.passClubAnnualReviewSuccess)
        } else if (response.data.code === 61) {  // 驳回社团年审成功
            console.log("failed")
            eventEmitter.emit(APIEventEnum.unPassClubAnnualReviewSuccess)
        } else if (response.data.code === 62) {  // 通过社团评优成功
            console.log("success")
            eventEmitter.emit(APIEventEnum.passClubAwardReviewSuccess)
        } else if (response.data.code === 63) {  // 驳回社团评优成功
            console.log("failed")
            eventEmitter.emit(APIEventEnum.unPassClubAwardReviewSuccess)
        } else if (response.data.code === 64) {  // 学院同意社团建立成功
            console.log("success")
            eventEmitter.emit(APIEventEnum.passCollegeClubApprovalSuccess)
        } else if (response.data.code === 65) {   // 学院驳回社团建立成功
            console.log("failed")
            eventEmitter.emit(APIEventEnum.unPassCollegeClubApprovalSuccess)
        } else if (response.data.code === 66) { // 获取部门列表
            const { departmentList } = response.data
            eventEmitter.emit(APIEventEnum.getDepartmentListSuccess, departmentList)
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setDepartmentList, departmentList)
        } else if (response.data.code === 67){  // 注册管理员成功
            eventEmitter.emit(APIEventEnum.postAdminRegisterSuccess)
        } else if (response.data.code === 68){  // 邮箱验证码发送成功
            console.log(response.data)
            eventEmitter.emit(APIEventEnum.sendVerifyCodeSuccess, response.data.data)
        }
        // 错误处理
    } else if (response.status === 2001) {    // 用户名或密码错误
        const msg = response.data.msg
        eventEmitter.emit(APIEventEnum.incorrectInput, msg)
    } else if (response.status === 2004) {    // 图形验证码错误
        const msg = response.data.msg
        eventEmitter.emit(APIEventEnum.incorrectVerifyCode, msg)
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