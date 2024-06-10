import { APIEventEnum } from '@/Enum'
import axios from '../utils/request'
import path from './path'
import eventEmitter from '@/utils/eventEmitter'
const toFormData = (params) => {
    let formData = new FormData();
    for(let key in params.params){
        formData.set(key, params.params[key])
    }
    return formData;
};

const api = {
    'postLogin': (params) => axios.post(path.login, toFormData({ // 登录
        params: {
            email: params.email,
            pwd: params.pwd,
            imageVerifyCode: params.imageVerifyCode,
        }
    })),
    'postRegister': (params) => axios.post(path.register, toFormData({ // 注册
        params:{
            email:params.email,
            verifyCode:params.verifyCode,
            pwd:params.pwd
        }
    })),
    'getVerifyCode': (params) => axios.get(path.verifyCode, { // 获取验证码
        params: {
            email: params.email,
        },
    }),
    'getClubsInfo': (params) => axios.get(path.clubsInfo, { // 获取社团信息

    }),
    'getLatestActivities': (params) => axios.get(path.latestActivities, { // 获取社团最新活动
        params: {
            pageNumber: params.pNumber,
            pageSize: params.pSize,
        }
    }),
    'getUserInfo': (params) => axios.get(path.userInfo, { // 获取用户信息
        params: {
            studentId: params.studentId,
        }
    }),
    'postUserInfo': (params) => axios.post(path.userInfoUpdate, toFormData({ // 更新用户信息
        params: {
            studentId: params.studentId,
            politicalStatus: params.politicalStatus,
            contact: params.contact,
            hobby: params.hobby, // 后端还没有
            specialty: params.specialty // 后端还没有
        }
    })),
    'postNewClubApply': (params) => axios.post(path.newClubApply, toFormData({ // 申请创建社团
        params: {
            contactPersonId: params.studentId,
            clubName: params.clubName,
            clubDescription: params.clubDescription,
            clubCategory: params.clubCategory,
            mainCampus: params.mainCampus,
            administrativeGuideTeacherName: params.administrativeGuideTeacherName,
            businessGuideTeacherName: params.businessGuideTeacherName,
            advisorResumeAttachmentUrl: params.material,
        }
    })),
    'getClubActAndNtc': (params) => axios.get(path.clubActAndNtc, { // 获取社团活动通知
        params: {
            clubId: params.clubId,
        }
    }),
    'getClubIntroduction': (params) => axios.get(path.clubIntroduction, { // 获取社团简介
        params: {
            clubId: params.clubId,
        }
    }),
    'getClubMembers': (params) => axios.get(path.clubMembers, { // 获取社团成员
        params: {
            clubId: params.clubId,
        }
    }),
    'postAdminLogin': (params) => axios.post(path.postAdminLogin, toFormData({ //管理员登录
        params: {
            account: params.adminId,
            password: params.password,
        }
    })),
    'getActivityInfo': (params) => axios.get(path.activityInfo, { // 获取活动信息
        params: {
            activityId: params.activityId,
        }
    }),
    'getAdminInfo': (params) => axios.get(path.adminInfo, { //获取管理员信息
        params: {
            adminId: params.adminId,
        }
    }),
    'postAdminInfo': (params) => axios.post(path.adminInfoUpdate, toFormData({ // 更新管理员信息
        params: {
            adminId: params.adminId,
            clubIntroduction: params.clubIntroduction,
        }
    })),
    'getActivitiesInfo': (params) => axios.get(path.activitiesInfo, { // 获取活动信息

    }),
    'getClubActivityList': (params) => axios.get(path.clubActivityList, { // 获取社团活动列表
        params: {
            clubId: params.clubId,
            pageNumber: params.pNumber,
            pageSize: params.pSize,
        }
    }),
    'getClubApplyList': (params) => axios.get(path.clubApplyList, { // 获取社团申请列表
        params: {
            clubId: params.clubId,
        }
    }),
    'postAgreeClubApply': (params) => axios.post(path.agreeClubApply, toFormData({ // 同意社团申请
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
        }
    })),
    'postRejectClubApply': (params) => axios.post(path.rejectClubApply, toFormData({ // 拒绝社团申请
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            rejectReason: params.rejectReason,
        }
    })),
    'postNewActivity': (params) => axios.post(path.newActivity, toFormData({ // 创建活动
        params: {
            clubId: params.clubId,
            activityName: params.activityName,
            activityIntroduction: params.activityIntroduction,
            activityStartTime: Date(params.activityStartTime),
            activityEndTime: Date(params.activityEndTime),
            activityLocation: Date(params.activityLocation),
            applicationFormAttachment: params.applicationFormAttachment,
            imageUrl: params.imageUrl,
        }
    })),
    'postBackBoneEvaluate': (params) => axios.post(path.backBoneEvaluate, toFormData({ // 提交社团优秀骨干申请
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            tenurePeriod: params.tenurePeriod,
            achievements: JSON.stringify(params.achievements),
            trainingParticipation: JSON.stringify(params.trainingParticipation),
            awards: JSON.stringify(params.awards),
            clubWorkStatus: params.clubWorkStatus,
        }
    })),
    'getClubEvaluateInfo': (params) => axios.get(path.clubEvaluateInfo, { // 获取某个社团信息
        params: {
            clubId: params.clubId,
        }
    }),
    'getBackBoneEvaluations': (params) => axios.get(path.backBoneEvaluations, {

    }),
    'uploadImage': ({ file, flag = '' }) => {
        const formData = new FormData();
        formData.append('image', file);
        return axios.post(path.uploadImage, {}, {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            params: {
                formData,
                flag
            }
        })
    },
    'postDeleteClubMember': (params) => axios.post(path.deleteClubMember, toFormData({ // 删除社团干部，即将干部变成普通成员
        params: {
            clubId: params.clubId,
            studentNumber: params.studentNumber,
        }
    })),
    'postUpdateClubMember': (params) => axios.post(path.updateClubMember, toFormData({ // 更新社团干部信息
        params: {
            clubId: params.clubId,
            oldStudent: params.oldStudent,
            newStudent: params.newStudent,
        }
    })),
    'postAddClubMember': (params) => axios.post(path.addClubMember, toFormData({ // 添加社团干部
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            stName: params.stName,
            position: params.position,
        }
    })),
    'postUpdateClubInfo': (params) => axios.post(path.updateClubInfo, toFormData({ // 更新社团信息
        params: {
            clubName: params.clubName,
            establishmentDate: params.establishmentDate,
            clubCategory: params.clubCategory,
            responsibleDepartment: params.responsibleDepartment,
            administrativeGuideTeacherName: params.administrativeGuideTeacherName,
            businessGuideTeacherName: params.businessGuideTeacherName,
            mainCompus: params.mainCampus,
            isFinancialInformationPublic: params.isFinancialInformationPublic,
            imageUrl: params.imageUrl,
        }
    })),
    'getClubAnnuals': (params) => axios.get(path.clubAnnuals, {

    }),
    'getClubEvaluations': (params) => axios.get(path.clubEvaluations, {

    }),
    'postPersonalPerformance': (params) => axios.post(path.personalPerformance, toFormData({ // 添加个人成绩
        params: {
            clubId: params.clubId,
            activityId: params.activityId,
            personalEffectList: params.personalEffectList, // 这是一个对象数组，对象形式为{stName: '', studentNumber: '', personalEffect: ''}
        }
    })),
    'postActivityPerformance': (params) => axios.post(path.activityPerformance, toFormData({ // 添加活动成绩
        params: {
            clubId: params.clubId,
            activityId: params.activityId,
            activityEffect: params.activityEffect,
        },
    })),
    'getTopTenClubs': (params) => axios.get(path.topTenClubs, {

    }),
    'uploadFile': ({ file, flag = '' }) => {
        const formData = new FormData();
        formData.append('file', file);
        return axios.post(path.uploadFile, {}, {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            params: {
                formData,
                flag
            }
        })
    },
    'postClubAnnualAuditForm': (params) => axios.post(path.clubAnnualAuditForm, toFormData({ // 提交年审表
        params: {
            clubId: params.clubId,
            meetingActivityListAttachment: params.meetingActivityListAttachment,
            externalSponsorshipAttachment: params.externalSponsorshipAttachment,
            isFinancialInformationPublic: params.isFinancialInformationPublic,
            clubConstitutionAttachment: params.clubConstitutionAttachment,
            responsibleDepartment: params.responsibleDepartment,
            publicityManagementInfo: JSON.stringify(params.publicityManagementInfo),
        }
    })),
    'postClubEvaluationForm': (params) => axios.post(path.clubEvaluationForm, toFormData({ // 提交评优表
        params: {
            clubId: params.clubId,
            handoverMethod: params.handoverMethod,
            handoverParticipantsCount: params.handoverParticipantsCount,
            advisorParticipation: params.advisorParticipation,
            publicityManagementEffectiveness: params.publicityManagementEffectiveness,
            hostedSchoolLevelActivities: params.hostedSchoolLevelActivities,
            clubWorkIntroduction: params.clubWorkIntroduction,
            clubEducationCaseAttachment: params.clubEducationCaseAttachment,
            imageUrl: params.imageUrl,
        }
    })),
    'getClubNoticeList': (params) => axios.get(path.clubNoticeList, { // 获取社团通知列表
        params: {
            clubId: params.clubId,
        }
    }),
    'postNewNotice': (params) => axios.post(path.newNotice, toFormData({ // 添加社团通知
        params: {
            clubId: params.clubId,
            title: params.title,
            content: params.content,
            imageUrl: params.imageUrl,
        }
    })),
    'postNewMeeting': (params) => axios.post(path.newMeeting, toFormData({ // 添加社团会议
        params: {
            clubId: params.clubId,
            meetingTime: params.meetingTime,
            location: params.location,
            category: params.category,
            advisorName: params.advisorName,
        }
    })),
    'getImageVerifyCode': () => axios.get(path.getImageVerifyCode, { // 获取图片验证码
    }),
    'postUpdateClubDescription': (params) => axios.post(path.updateClubDescription, toFormData({ // 更新社团简介
        params: {
            clubId: params.clubId,
            clubDescription: JSON.stringify(params.clubDescription),
        }
    })),
    'getEnumList': () => axios.get(path.getEnumList, { // 获取枚举列表
    }),
    'getMyClubBackboneExamData': (params) => axios.get(path.myClubBackboneExamData, { // 获取特定社团的年审记录
        params: {
            clubId: params.clubId,
        }
    }),
    'getMyClubAnnualExamData': (params) => axios.get(path.myClubAnnualExamData, { // 获取特定社团的年审记录
        params: {
            clubId: params.clubId,
        }
    }),
    'getMyClubTeacherExamData': (params) => axios.get(path.myClubTeacherExamData, { // 获取特定社团的年审记录
        params: {
            clubId: params.clubId,
        }
    }),
    'getAssociationAwards': (params) => axios.get(path.associationAwards, { // 获取社团获奖情况
        params: {
            clubId: params.clubId,
        }
    }),
    'getMeetings': (params) => axios.get(path.meetings, { // 获取社团会议列表
        params: {
            clubId: params.clubId,
        }
    }),
    'postJoinClub': (params) => axios.post(path.joinClub, toFormData({ // 加入社团
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            reason: params.reason,
        }
    })),
    'postJoinActivity': (params) => axios.post(path.joinActivity, toFormData({ // 加入活动
        params: {
            activityId: params.activityId,
            studentNumber: params.studentNumber,
        }
    })),
    'getClubApproval':(params)=> axios.get(path.clubApplicationInfo, {// 获取建立社团申请详情
        params:{
            clubId: params,
        }
    }),
    'getClubAnnual':(params)=> axios.get(path.clubAnnual, {
        params:{
            declarationId: params,
        }
    }),
    'getThisYearClubAnnual':(params)=> axios.get(path.thisYearClubAnnual, {
        params:{
            clubId: params.clubId,
            declarationYear: params.declarationYear
        }
    }),
    'getClubAwardInfo':(params)=>axios.get(path.clubAward, {
        params:{
            recordId: params.recordId,
        }
    }),
    'getClubInfo':(params)=>axios.get(path.clubInfo, {
        params:{
            clubId: params.clubId,
        }
    }),
    'passClubApproval':(params)=>axios.post(path.passClubApproval, toFormData({
        params:{
            recordId: params.recordId,
            universityStudentUnionReviewOpinion: params.universityStudentUnionReviewOpinion
        }
    })),
    'unPassClubApproval':(params)=>axios.post(path.unPassClubApproval, toFormData({
        params:{
            recordId: params.recordId,
            universityStudentUnionReviewOpinion: params.universityStudentUnionReviewOpinion
        }
    })),
    'passActivityApproval':(params)=>axios.post(path.passActivityApproval, toFormData({
        params:{
            activityId: params.activityId,
            opinion: params.opinion
        }
    })),
    'unPassActivityApproval':(params)=>axios.post(path.unPassActivityApproval, toFormData({
        params:{
            activityId: params.activityId,
            opinion: params.opinion
        }
    })),
    'passBackboneAwardsReview':(params)=>axios.post(path.passBackboneAwardsReview, toFormData({
        params:{
            recordId: params.recordId
        }
    })),
    'unPassBackboneAwardsReview':(params)=>axios.post(path.unPassBackboneAwardsReview, toFormData({
        params:{
            recordId: params.recordId
        }
    })),
    'passClubAnnualReview':(params)=>axios.post(path.passClubAnnualReview, toFormData({
        params:{
            declarationId: params.declarationId,
            departmentOpinion: params.departmentOpinion
        }
    })),
    'unPassClubAnnualReview':(params)=>axios.post(path.unPassClubAnnualReview, toFormData({
        params:{
            declarationId: params.declarationId,
            departmentOpinion: params.departmentOpinion
        }
    })),
    'passClubAwardReview':(params)=>axios.post(path.passClubAwardReview, toFormData({
        params:{
            recordId: params.recordId
        }
    })),
    'unPassClubAwardReview':(params)=>axios.post(path.unPassClubAwardReview, toFormData({
        params:{
            recordId: params.recordId
        }
    })),
}

eventEmitter.on(APIEventEnum.request, 'request', async (method, params) => {
    await api[method](params)
})

export default api