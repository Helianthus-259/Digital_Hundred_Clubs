import { APIEventEnum } from '@/Enum'
import axios from '../utils/request'
import path from './path'
import eventEmitter from '@/utils/eventEmitter'

const api = {
    'postLogin': (params) => axios.post(path.login, { // 登录
        params: {
            email: params.email,
            pwd: params.pwd,
            imageVerifyCode: params.imageVerifyCode,
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
    'postUserInfo': (params) => axios.post(path.userInfoUpdate, { // 更新用户信息
        params: {
            studentId: params.studentId,
            politicalStatus: params.politicalStatus,
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
    'getClubAnnuals': (params) => axios.get(path.clubAnnuals, {

    }),
    'getClubEvaluations': (params) => axios.get(path.clubEvaluations, {

    }),
    'postPersonalPerformance': (params) => axios.post(path.personalPerformance, { // 添加个人成绩
        params: {
            clubId: params.clubId,
            activityId: params.activityId,
            personalEffectList: params.personalEffectList, // 这是一个对象数组，对象形式为{stName: '', studentNumber: '', personalEffect: ''}
        }
    }),
    'postActivityPerformance': (params) => axios.post(path.activityPerformance, { // 添加活动成绩
        params: {
            clubId: params.clubId,
            activityId: params.activityId,
            activityEffect: params.activityEffect,
        },
    }),
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
    'postClubAnnualAuditForm': (params) => axios.post(path.clubAnnualAuditForm, { // 提交年审表
        params: {
            clubId: params.clubId,
            meetingActivityListAttachment: params.meetingActivityListAttachment,
            externalSponsorshipAttachment: params.externalSponsorshipAttachment,
            isFinancialInformationPublic: params.isFinancialInformationPublic,
            clubConstitutionAttachment: params.clubConstitutionAttachment,
            responsibleDepartment: params.responsibleDepartment,
            publicityManagementInfo: params.publicityManagementInfo,
        }
    }),
    'postClubEvaluationForm': (params) => axios.post(path.clubEvaluationForm, { // 提交评优表
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
    }),
    'getClubNoticeList': (params) => axios.get(path.clubNoticeList, { // 获取社团通知列表
        params: {
            clubId: params.clubId,
        }
    }),
    'postNewNotice': (params) => axios.post(path.newNotice, { // 添加社团通知
        params: {
            clubId: params.clubId,
            title: params.title,
            content: params.content,
            imageUrl: params.imageUrl,
        }
    }),
    'postNewMeeting': (params) => axios.post(path.newMeeting, { // 添加社团会议
        params: {
            clubId: params.clubId,
            meetingTime: params.meetingTime,
            location: params.location,
            category: params.category,
            advisorName: params.advisorName,
        }
    }),
    'getImageVerifyCode': () => axios.get(path.getImageVerifyCode, { // 获取图片验证码
    }),
    'postUpdateClubDescription': (params) => axios.post(path.updateClubDescription, { // 更新社团简介
        params: {
            clubId: params.clubId,
            clubDescription: params.clubDescription,
        }
    }),
    'getEnumList': () => axios.get(path.getEnumList, { // 获取枚举列表
    }),
    'getMyClubBackboneExamData':(params) => axios.get(path.myClubBackboneExamData, { // 获取特定社团的年审记录
        params: {
            clubId: params.clubId,
        }
    }),
    'getMyClubAnnualExamData':(params) => axios.get(path.myClubAnnualExamData, { // 获取特定社团的年审记录
        params: {
            clubId: params.clubId,
        }
    }),
    'getMyClubTeacherExamData':(params) => axios.get(path.myClubTeacherExamData, { // 获取特定社团的年审记录
        params: {
            clubId: params.clubId,
        }
    }),
}

eventEmitter.on(APIEventEnum.request, 'request', async (method, params) => {
    console.log('request', method, params);
    await api[method](params)
})

export default api