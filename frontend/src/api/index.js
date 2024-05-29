import { APIEventEnum } from '@/Enum'
import axios from '../utils/request'
import path from './path'
import eventEmitter from '@/utils/eventEmitter'

const api = {
    'postLogin': (params) => axios.post(path.login, { // 登录
        params: {
            email: params.email,
            pwd: params.pwd,
        }
    }),
    'postRegister': (params) => axios.post(path.register, { // 注册
        params: {
            email: params.email,
            verifyCode: params.verifyCode,
            pwd: params.pwd,
        }
    }),
    'getVerifyCode': (params) => axios.get(path.verifyCode, { // 获取验证码
        params: {
            email: params.email,
        },
    }),
    'getClubsInfo': (params) => axios.get(path.clubsInfo, { // 获取社团信息

    }),
    'getclubLatestAct': (params) => axios.get(path.clubLatestAct, { // 获取社团最新活动

    }),
    'getUserInfo': (params) => axios.get(path.userInfo, { // 获取用户信息
        params: {
            studentId: params.studentId,
        }
    }),
    'postUserInfo': (params) => axios.post(path.userInfoUpdate, { // 更新用户信息
        params: {
            studentId: params.studentId,
            contact: params.contact,
            hobby: params.hobby, // 后端还没有
            specialty: params.specialty // 后端还没有
        }
    }),
    'postNewClubApply': (params) => axios.post(path.newClubApply, { // 申请创建社团
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
    }),
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
    'postAdminLogin': (params) => axios.post(path.postAdminLogin, { //管理员登录
        params: {
            adminId: params.adminId,
            password: params.password,
        }
    }),
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
    'postAdminInfo': (params) => axios.post(path.adminInfoUpdate, { // 更新管理员信息
        params: {
            adminId: params.adminId,
            clubIntroduction: params.clubIntroduction,
        }
    }),
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
    'postAgreeClubApply': (params) => axios.post(path.agreeClubApply, { // 同意社团申请
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
        }
    }),
    'postRejectClubApply': (params) => axios.post(path.rejectClubApply, { // 拒绝社团申请
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            rejectReason: params.rejectReason,
        }
    }),
    'postNewActivity': (params) => axios.post(path.newActivity, { // 创建活动
        params: {
            clubId: params.clubId,
            activityName: params.activityName,
            activityIntroduction: params.activityIntroduction,
            activityStartTime: params.activityStartTime,
            activityEndTime: params.activityEndTime,
            activityLocation: params.activityLocation,
            applicationFormAttachment: params.applicationFormAttachment,
            imageUrl: params.imageUrl,
        }
    }),
    'postBackBoneEvaluate': (params) => axios.post(path.backBoneEvaluate, { // 提交社团优秀骨干申请
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            tenurePeriod: params.tenurePeriod,
            achievements: params.achievements,
            trainingParticipation: params.trainingParticipation,
            awards: params.awards,
            clubWorkStatus: params.clubWorkStatus,
        }
    }),
    'getClubEvaluateInfo': (params) => axios.get(path.clubEvaluateInfo, { // 获取某个社团信息
        params: {
            clubId: params.clubId,
        }
    }),
    'getBackBoneEvaluations':(params)=> axios.get(path.backBoneEvaluations, {

    }),
    'uploadImage': (file) => {
        const formData = new FormData();
        formData.append('image', file);
        return axios.post(path.uploadImage, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    },
    'postDeleteClubMember': (params) => axios.post(path.deleteClubMember, { // 删除社团干部，即将干部变成普通成员
        params: {
            clubId: params.clubId,
            studentNumber: params.studentNumber,
        }
    }),
    'postUpdateClubMember': (params) => axios.post(path.updateClubMember, { // 更新社团干部信息
        params: {
            clubId: params.clubId,
            oldStudent: params.oldStudent,
            newStudent: params.newStudent,
        }
    }),
    'postAddClubMember': (params) => axios.post(path.addClubMember, { // 添加社团干部
        params: {
            clubId: params.clubId,
            studentId: params.studentId,
            stName: params.stName,
            position: params.position,
        }
    }),
    'postUpdateClubInfo': (params) => axios.post(path.updateClubInfo, { // 更新社团信息
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
    }),
    'getClubAnnuals':(params)=>axios.get(path.clubAnnuals, {

    }),
    'getClubEvaluations':(params)=>axios.get(path.clubEvaluations, {

    })
}

eventEmitter.on(APIEventEnum.request, 'request', async (method, params) => {
    await api[method](params)
})

export default api